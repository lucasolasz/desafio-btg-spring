package desafio.btgpactual.orderms.repository;

import desafio.btgpactual.orderms.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
}
