package uz.dev.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.dev.apelsin.dto.NumberOfPRoductInYaerDTO;
import uz.dev.apelsin.dto.OrderDetailsDTO;
import uz.dev.apelsin.dto.OrdersWithoutInvoices;
import uz.dev.apelsin.model.Order;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "select ord.id,ord.date,ord.customer_id from order_table ord where ord.date<'2016-09-06';", nativeQuery = true)
    List<Order> findOrdersWithoutDetails();

    List<Order> findOrdersByDateIsBefore(Date date);

    @Query(value = "select c.country, count(ot.id) as totalNumber " +
            "from order_table ot " +
            "left outer join customer c on c.id = ot.customer_id " +
            "where ot.date between '2016-01-01' and '2016-12-31' " +
            "group by c.country", nativeQuery = true)
    List<NumberOfPRoductInYaerDTO> findNumberofProductsInYear();

    @Query(value = "select order_table.id, order_table.date,p.price*d.quantity as totalPrice " +
            "from order_table " +
            "left join detail d on order_table.id = d.order_id " +
            "left join product p on p.id = d.product_id " +
            "where d.order_id is not null", nativeQuery = true)
    List<OrdersWithoutInvoices> findOrdersWithoutInvoices();

    @Query(value = "select d.id as detailId, d.quantity, d.product_id as productId, ot.date, ot.customer_id as customerId, p.name as productName " +
            "from detail d " +
            "left join product p on p.id = d.product_id " +
            "left join order_table ot on ot.id = d.order_id " +
            "where d.order_id = :id", nativeQuery = true)
    OrderDetailsDTO findOrderDeatailById(@Param("id")Long id);
}
