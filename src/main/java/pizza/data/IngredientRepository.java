package pizza.data;

import org.springframework.data.repository.CrudRepository;
import pizza.Ingredient;

public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
}
