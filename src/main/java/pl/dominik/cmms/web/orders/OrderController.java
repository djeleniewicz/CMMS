package pl.dominik.cmms.web.orders;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.equipment.Status;
import pl.dominik.cmms.entity.mechanics.Mechanic;
import pl.dominik.cmms.entity.orders.Name;
import pl.dominik.cmms.entity.orders.Order;
import pl.dominik.cmms.repository.equipment.EquipmentRepository;
import pl.dominik.cmms.repository.equipment.StatusRepository;
import pl.dominik.cmms.repository.order.NameRepository;
import pl.dominik.cmms.repository.order.OrderRepository;
import pl.dominik.cmms.repository.security.MechanicRepository;
import pl.dominik.cmms.service.security.CurrentUser;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final NameRepository nameRepository;
    private final EquipmentRepository equipmentRepository;
    private final MechanicRepository mechanicRepository;
    private final StatusRepository statusRepository;


    public OrderController(OrderRepository orderRepository, NameRepository nameRepository, EquipmentRepository equipmentRepository, MechanicRepository mechanicRepository, StatusRepository statusRepository) {
        this.orderRepository = orderRepository;
        this.nameRepository = nameRepository;
        this.equipmentRepository = equipmentRepository;
        this.mechanicRepository = mechanicRepository;
        this.statusRepository = statusRepository;
    }

    @ModelAttribute("name")
    public List<Name> getStatus() {
        return nameRepository.findAll();
    }

    @ModelAttribute("namesforuser")
    public List<Name> userStatus() {
        return nameRepository.findAllByNameOrName("DAMAGE", "INSPECTION");
    }

    @ModelAttribute("equipments")
    public List<Equipment> selectEquipment() {
        return equipmentRepository.findAll();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/order")
    public String homeOrder(Model model) {
        List<Order> order = orderRepository.findAll();
        model.addAttribute("order", order);
        return "order/order";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MECH"})
    @RequestMapping("/add-order")
    public String addOrder(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "order/form";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MECH"})
    @RequestMapping(value = "/add-order", method = RequestMethod.POST)
    public String saveOrder(@Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "order/form";
        }
        order.setCreated(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
        return "redirect:/order";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/update-order/{id}")
    public String editOrder(@PathVariable int id, Model model) {
        Order order = orderRepository.findOne(id);
        model.addAttribute("order", order);
        return "/order/formup";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update-order", method = RequestMethod.POST)
    public String editOrder(@RequestParam int id, @Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "order/formup";
        }
        order.setId(id);
        orderRepository.save(order);
        return "redirect:/order";
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("/delete-order/{id}")
    public String delEquip(@PathVariable int id) {
        Order order = orderRepository.findOne(id);
        orderRepository.delete(order);
        return "redirect:/order";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN"})
    @RequestMapping("/showOrders")
    public String orders(Model model) {
        List<Order> order = orderRepository.findAllByName_IdOrName_Id(1, 2);
        model.addAttribute("order", order);
        return "order/mech";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN"})
    @RequestMapping("/ordersHistory")
    public String history(Model model) {
        List<Order> order = orderRepository.findAllByName_Id(3);
        model.addAttribute("order", order);
        return "order/mechENDED";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("/order-user")
    public String userAdd(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "order/formUser";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/order-user", method = RequestMethod.POST)
    public String save(@AuthenticationPrincipal CurrentUser customUser, @Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "order/formUser";
        }
        System.out.println(order.getEquipment().getId());
        Equipment equipment = equipmentRepository.findOne(order.getEquipment().getId());
        if(order.getName().getId() == 3) {
            Status status = statusRepository.findOne(1);
            equipment.setStatus(status);
        } else if (order.getName().getId() == 1 ) {
            Status status = statusRepository.findOne(2);
            equipment.setStatus(status);
        }
        order.setUser(customUser.getUser());
        order.setCreated(new Timestamp(System.currentTimeMillis()));
        equipmentRepository.save(equipment);
        orderRepository.save(order);
        return "redirect:/";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("/equipment-user")
    public String equipment(Model model) {
        List<Equipment> list = equipmentRepository.findAll();
        model.addAttribute("equipment", list);
        return "order/equipment";
    }


    @Secured({"ROLE_MECH", "ROLE_ADMIN"})
    @RequestMapping("/end-order/{id}")
    public String endOrder(@PathVariable int id, Model model) {
        Order order = orderRepository.findOne(id);
        model.addAttribute("order", order);
        return "/order/formEnd";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN"})
    @RequestMapping(value = "/end-order", method = RequestMethod.POST)
    public String endOrder(@RequestParam int id, @AuthenticationPrincipal CurrentUser customUser, @ModelAttribute Order order) {
        String end = order.getEnd();
        order = orderRepository.findOne(id);
        Name name = nameRepository.findOne(3);
        Mechanic mechanic = mechanicRepository.findByName(customUser.getUser().getUsername());
        Equipment equipment = equipmentRepository.findOne(order.getEquipment().getId());
        System.out.println(equipment);
        if(order.getName().getId() == 1) {
            Status status = statusRepository.findOne(1);
            equipment.setStatus(status);
        } else if (order.getName().getId() == 3) {
            Status status = statusRepository.findOne(2);
            equipment.setStatus(status);
        }
        System.out.println(equipment);
        equipmentRepository.save(equipment);
        order.setId(id);
        order.setEnd(end);
        order.setEnded(new Timestamp(System.currentTimeMillis()));
        order.setName(name);
        order.setMechanic(mechanic);
        orderRepository.save(order);
        return "redirect:/";
    }

}
