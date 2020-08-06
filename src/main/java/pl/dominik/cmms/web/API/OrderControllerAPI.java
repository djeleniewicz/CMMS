package pl.dominik.cmms.web.API;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.requests.Order;
import pl.dominik.cmms.repository.requests.OrderRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1")
public class OrderControllerAPI {

    private final OrderRepository orderRepository;

    public OrderControllerAPI(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId) {
        Order order = orderRepository.findById(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/add-order")
    public Order createOrder(@Valid @RequestBody Order order) {
//        equipment.setCreated(new Timestamp(now()));
        return orderRepository.save(order);
    }

    @PutMapping("/update-order/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable(value = "id") Long orderId, @Valid @RequestBody Order orderDetails) {
        Order order = orderRepository.findById(orderId);
        order.setNote(orderDetails.getNote());
        final Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @PostMapping("/end-order/{id}")
    public ResponseEntity<Order> endOrder(
            @PathVariable(value = "id") Long orderId, @Valid @RequestBody Order orderDetails) {
        Order order = orderRepository.findById(orderId);
        order.setEndNote(orderDetails.getNote());
        final Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

}
