package com.example.crud.controller;

import com.example.crud.model.Order;
import com.example.crud.model.OrderItem;
import com.example.crud.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> body,
                                         Authentication authentication) {
        String username = authentication.getName();

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> itemsData = (List<Map<String, Object>>) body.get("items");
        if (itemsData == null || itemsData.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "订单商品不能为空"));
        }

        Order order = new Order();
        order.setUsername(username);
        order.setStatus("待付款");

        double total = 0;
        for (Map<String, Object> itemData : itemsData) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(toLong(itemData.get("productId")));
            item.setProductName((String) itemData.get("productName"));
            item.setProductImage((String) itemData.get("productImage"));
            item.setPrice(toDouble(itemData.get("price")));
            item.setQuantity(toInt(itemData.get("quantity")));
            order.getItems().add(item);
            total += item.getPrice() * item.getQuantity();
        }
        order.setTotalAmount(total);

        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Order> getMyOrders(Authentication authentication,
                                   @RequestParam(required = false) String status) {
        String username = authentication.getName();
        if (status != null && !status.isBlank()) {
            List<Order> all = orderRepository.findByUsernameOrderByCreatedAtDesc(username);
            return all.stream().filter(o -> o.getStatus().equals(status.trim())).toList();
        }
        return orderRepository.findByUsernameOrderByCreatedAtDesc(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id,
                                              Authentication authentication) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + id));
        if (!order.getUsername().equals(authentication.getName())) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                   @RequestBody Map<String, String> body,
                                                   Authentication authentication) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + id));
        if (!order.getUsername().equals(authentication.getName())) {
            return ResponseEntity.status(403).build();
        }

        String newStatus = body.get("status");
        if (newStatus == null || newStatus.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        order.setStatus(newStatus.trim());
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id,
                                            Authentication authentication) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + id));
        if (!order.getUsername().equals(authentication.getName())) {
            return ResponseEntity.status(403).build();
        }
        order.setStatus("已取消");
        orderRepository.save(order);
        return ResponseEntity.noContent().build();
    }

    private Long toLong(Object val) {
        if (val instanceof Number) return ((Number) val).longValue();
        return val != null ? Long.parseLong(val.toString()) : null;
    }

    private Double toDouble(Object val) {
        if (val instanceof Number) return ((Number) val).doubleValue();
        return val != null ? Double.parseDouble(val.toString()) : 0.0;
    }

    private Integer toInt(Object val) {
        if (val instanceof Number) return ((Number) val).intValue();
        return val != null ? Integer.parseInt(val.toString()) : 0;
    }
}
