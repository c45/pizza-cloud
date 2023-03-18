package pizza.data;

import org.springframework.data.repository.CrudRepository;
import pizza.PizzaOrder;

public interface OrderRepository
        extends CrudRepository<PizzaOrder, Long> {
}
