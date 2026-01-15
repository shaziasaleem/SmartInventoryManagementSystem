
package inventory.orderService.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.orderService.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

}
