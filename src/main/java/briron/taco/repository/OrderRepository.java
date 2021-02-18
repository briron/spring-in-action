package briron.taco.repository;

import briron.taco.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
