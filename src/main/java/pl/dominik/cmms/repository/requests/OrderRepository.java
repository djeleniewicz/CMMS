package pl.dominik.cmms.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.requests.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findById(Long orderId);

//    //    @Query("SELECT o FROM Order o where Order.name = 1 order by desc")
//    List<Order> findAllByName_IdOrName_IdOrderByCreatedDesc(int id, int id2);
//
//    List<Order> findAllByName_IdOrderByEndedDesc(int id);
//
//    List<Order> findAllByUserOrderByEndedDesc(User user);
//
//    List<Order> findAllByEquipmentOrderByEndedDesc(Equipment equipment);


}