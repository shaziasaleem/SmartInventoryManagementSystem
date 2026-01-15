
package inventory.orderService.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inventory.orderService.dto.OrderRequestDTO;
import inventory.orderService.dto.OrderResponseDTO;
import inventory.orderService.model.OrderEntity;
import inventory.orderService.model.OrderStatus;
import inventory.orderService.repository.OrderRepository;
import inventory.orderService.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Place order synchronously: create order record and return response.
     * Inventory update is delegated to an async method so API latency is minimized.
     */
    @Override
    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO request) {
        OrderEntity order = new OrderEntity();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setUserId(request.getUserId());
        order.setOrderStatus(OrderStatus.CREATED);

        OrderEntity saved = orderRepository.save(order);

        // Fire-and-forget async inventory update
        updateInventoryAsync(saved.getId(), saved.getProductId(), saved.getQuantity());

        OrderResponseDTO resp = new OrderResponseDTO();
        resp.setOrderId(saved.getId());
        resp.setProductId(saved.getProductId());
        resp.setQuantity(saved.getQuantity());
        resp.setUserId(saved.getUserId());
        resp.setStatus(saved.getOrderStatus().name());
        resp.setCreatedAt(saved.getCreatedAt());
        return resp;
    }

    @Override
    @Transactional
    public void markOrderFailed(UUID orderId, String reason) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setOrderStatus(OrderStatus.FAILED);
            orderRepository.save(order);
            log.warn("Order {} marked FAILED: {}", orderId, reason);
        });
    }

    /**
     * Async inventory update. Implemented here as @Async — in production you may prefer
     * an event-driven approach (Kafka/RabbitMQ) for reliability and retries.
     */
    @Async
    public void updateInventoryAsync(UUID orderId, UUID productId, Integer quantity) {
        try {
            // Placeholder: call Inventory service REST endpoint to deduct quantity.
            // Use a RestTemplate/WebClient call here. For now we simulate success.
            log.info("Async inventory update started for order={} product={} qty={}", orderId, productId, quantity);

            // Simulate call and assume success. If call fails, mark order as FAILED via markOrderFailed().

            log.info("Async inventory update completed for order={}", orderId);
        } catch (Exception ex) {
            log.error("Failed to update inventory for order={}", orderId, ex);
            markOrderFailed(orderId, ex.getMessage());
        }
    }
}
