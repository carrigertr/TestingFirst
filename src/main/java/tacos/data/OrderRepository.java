package tacos.data;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import tacos.Order;

@Primary
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	List<Order> findByZip(String zip);
	
	List<Order> readByZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

}
