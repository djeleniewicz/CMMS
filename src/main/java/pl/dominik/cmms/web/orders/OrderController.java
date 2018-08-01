package pl.dominik.cmms.web.orders;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dominik.cmms.entity.orders.Name;
import pl.dominik.cmms.entity.orders.Order;
import pl.dominik.cmms.repository.order.NameRepository;
import pl.dominik.cmms.repository.order.OrderRepository;

import java.sql.Date;
import java.util.List;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final NameRepository nameRepository;

    public OrderController(OrderRepository orderRepository, NameRepository nameRepository) {
        this.orderRepository = orderRepository;
        this.nameRepository = nameRepository;
    }

    @ModelAttribute("name")
    public List<Name> getStatus() {
        return nameRepository.findAll();
    }

    @RequestMapping("/order")
    public String homeOrder(Model model) {
        List<Order> order = orderRepository.findAll();
        model.addAttribute("order", order);
        return "order/order";
    }

    @RequestMapping("/add-order")
    public String addOrder(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "order/form";
    }

    @RequestMapping(value = "/add-order", method = RequestMethod.POST)
    public String saveOrder(@ModelAttribute Order order) {
        order.setCreated(new Date(System.currentTimeMillis()));
        orderRepository.save(order);
        return "redirect:/order";
    }

    @RequestMapping("/update-order/{id}")
    public String editOrder(@PathVariable int id, Model model) {
        Order order = orderRepository.findOne(id);
        model.addAttribute("order", order);
        return "/order/form";
    }

    @RequestMapping(value = "/update-order/{id}", method = RequestMethod.POST)
    public String editOrder(@PathVariable int id, @ModelAttribute Order order) {
        order.setId(id);
        orderRepository.save(order);
        return "redirect:/order";
    }


    @RequestMapping("/delete-order/{id}")
    public String delEquip(@PathVariable int id) {
        Order order = orderRepository.findOne(id);
        orderRepository.delete(order);
        return "redirect:/order";
    }

    @RequestMapping("/showOrders")
    public String orders(Model model) {
        List<Order> order = orderRepository.findAllByName_IdOrName_Id(1,2);
        model.addAttribute("order", order);
        return "order/mech";
    }
}
