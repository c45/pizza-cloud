package pizza.data;

import org.springframework.data.repository.CrudRepository;
import pizza.Ingredient;

import java.util.Optional;

public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
}
