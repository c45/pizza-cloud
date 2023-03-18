package pizza.data;

import pizza.PizzaOrder;

public interface OrderRepository {

    PizzaOrder save(PizzaOrder order);

}
