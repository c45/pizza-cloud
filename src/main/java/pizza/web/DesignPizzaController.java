package pizza.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pizza.Ingredient;
import pizza.Ingredient.Type;
import pizza.Pizza;
import pizza.PizzaOrder;
import pizza.User;
import pizza.data.IngredientRepository;
import pizza.data.PizzaRepository;
import pizza.data.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {

    private final IngredientRepository ingredientRepo;

    private PizzaRepository pizzaRepo;
    private UserRepository userRepo;

    @Autowired
    public DesignPizzaController(IngredientRepository ingredientRepo, PizzaRepository pizzaRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.pizzaRepo = pizzaRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @ModelAttribute(name = "order")
    public PizzaOrder order() {
        return new PizzaOrder();
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        return user;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processPizza(
            @Valid Pizza pizza, Errors errors,
            @ModelAttribute PizzaOrder pizzaOrder) {

        if (errors.hasErrors()) {
            return "design";
        }
        Pizza saved = pizzaRepo.save(pizza);
        pizzaOrder.addPizza(saved);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}

