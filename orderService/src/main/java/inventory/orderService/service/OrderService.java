
package inventory.orderService.service;

import java.util.UUID;

import inventory.orderService.dto.OrderRequestDTO;
import inventory.orderService.dto.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO request);

    // Useful for listener to update status
    void markOrderFailed(UUID orderId, String reason);
}
