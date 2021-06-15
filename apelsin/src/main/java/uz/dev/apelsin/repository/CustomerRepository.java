package uz.dev.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.dev.apelsin.dto.CustomerDTO;
import uz.dev.apelsin.dto.CustomerLastOrderDTO;
import uz.dev.apelsin.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

        @Query(value = "select customer.id " +
                "from customer " +
                "left join order_table ot on customer.id = ot.customer_id " +
                "where  ot.date NOT between '2016-01-01' and '2016-12-31'" +
                "except " +
                "select customer.id " +
                "from customer " +
                "left join order_table ot on customer.id = ot.customer_id " +
                "where  ot.date between '2016-01-01' and '2016-12-31';",nativeQuery = true)
        List<Long> findCustomersWithoutOrdersId();

        @Query("SELECT c FROM Customer c WHERE c.id IN (:id)")
        List<Customer> findCustomersById(@Param("id") List<Long> listId);

        @Query(value = "select ct.id, ct.name, max(ot.date) as date " +
                "from customer ct " +
                "left join order_table ot on ct.id = ot.customer_id " +
                "where ot.customer_id is not null " +
                "group by ct.id;", nativeQuery = true)
        List<CustomerLastOrderDTO> findCustomersLastOrders();

}
