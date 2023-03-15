package pizza;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pizza.Ingredient.Type;
import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("THCR",
                new Ingredient("THCR", "Thick Crust", Type.DOUGH));
        ingredientMap.put("THIN",
                new Ingredient("THIN", "Thin", Type.DOUGH));
        ingredientMap.put("TMTS",
                new Ingredient("TMTS", "Tomatoes", Type.VEGETABLES));
        ingredientMap.put("MHRM",
                new Ingredient("MHRM", "Mushroom", Type.VEGETABLES));
        ingredientMap.put("BBQS",
                new Ingredient("BBQS", "BBQ sauce", Type.SAUCES));
        ingredientMap.put("GRLS",
                new Ingredient("GRLS", "Garlic sauce", Type.SAUCES));
        ingredientMap.put("MZRL",
                new Ingredient("MZRL", "Mozarella", Type.CHEESE));
        ingredientMap.put("CHDR",
                new Ingredient("CHDR", "Cheddar", Type.CHEESE));
        ingredientMap.put("PEPR",
                new Ingredient("PEPR", "Peperoni", Type.MEAT));
        ingredientMap.put("HAMS",
                new Ingredient("HAMS", "Ham", Type.MEAT));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
