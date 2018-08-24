package pl.dominik.cmms.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.orders.Order;
import pl.dominik.cmms.entity.security.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    @Query("SELECT o FROM Order o where Order.name = 1 order by desc")
    List<Order> findAllByName_IdOrName_IdOrderByCreatedDesc(int id, int id2);
    List<Order> findAllByName_IdOrderByEndedDescCreatedDesc(int id);

    List<Order> findAllByUserOrderByEndedDesc(User user);
    List<Order> findAllByEquipmentOrderByEndedDesc(Equipment equipment);



}