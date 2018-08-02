package pl.dominik.cmms.web.orders;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.orders.Name;
import pl.dominik.cmms.entity.orders.Order;
import pl.dominik.cmms.repository.equipment.EquipmentRepository;
import pl.dominik.cmms.repository.order.NameRepository;
import pl.dominik.cmms.repository.order.OrderRepository;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final NameRepository nameRepository;
    private final EquipmentRepository equipmentRepository;

    public OrderController(OrderRepository orderRepository, NameRepository nameRepository, EquipmentRepository equipmentRepository) {
        this.orderRepository = orderRepository;
        this.nameRepository = nameRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @ModelAttribute("name")
    public List<Name> getStatus() {
        return nameRepository.findAll();
    }

    @ModelAttribute("namesforuser")
    public List<Name> userStatus() {
        return nameRepository.findAllByNameOrName("DAMAGE","INSPECTION");
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
    public String saveOrder(@Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "order/form";
        }
        order.setCreated(new Date(System.currentTimeMillis()));
        orderRepository.save(order);
        return "redirect:/order";
    }

    @RequestMapping("/update-order/{id}")
    public String editOrder(@PathVariable int id, Model model) {
        Order order = orderRepository.findOne(id);
        model.addAttribute("order", order);
        return "/order/formup";
    }

    @RequestMapping(value = "/update-order", method = RequestMethod.POST)
    public String editOrder(@RequestParam int id, @Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "order/formup";
        }
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

    @RequestMapping("/ordersHistory")
    public String history(Model model) {
        List<Order> order = orderRepository.findAllByName_Id(3);
        model.addAttribute("order", order);
        return "order/mech";
    }



    @RequestMapping("/order-user")
    public String userAdd(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "order/formUser";
    }

    @RequestMapping(value = "/order-user", method = RequestMethod.POST)
    public String save(@Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "order/formUser";
        }
        order.setCreated(new Date(System.currentTimeMillis()));
        orderRepository.save(order);
        return "redirect:/order";
    }

    @RequestMapping("/equipment-user")
    public String equipment(Model model) {
        List<Equipment> list = equipmentRepository.findAll();
        model.addAttribute("equipment", list);
        return "order/equipment";
    }


}
