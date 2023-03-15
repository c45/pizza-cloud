package pizza.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pizza.Ingredient;
import pizza.Ingredient.Type;
import pizza.Pizza;
import pizza.PizzaOrder;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("THCR", "Thick Crust", Type.DOUGH),
                new Ingredient("THIN", "Thin", Type.DOUGH),
                new Ingredient("TMTS", "Tomatoes", Type.VEGETABLES),
                new Ingredient("MHRM", "Mushroom", Type.VEGETABLES),
                new Ingredient("BBQS", "BBQ sauce", Type.SAUCES),
                new Ingredient("GRLS", "Garlic sauce", Type.SAUCES),
                new Ingredient("MZRL", "Mozarella", Type.CHEESE),
                new Ingredient("CHDR", "Cheddar", Type.CHEESE),
                new Ingredient("PEPR", "Peperoni", Type.MEAT),
                new Ingredient("HAMS", "Ham", Type.MEAT)
        );

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

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
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

        pizzaOrder.addPizza(pizza);

        log.info("Processing pizza: {}", pizza);

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

