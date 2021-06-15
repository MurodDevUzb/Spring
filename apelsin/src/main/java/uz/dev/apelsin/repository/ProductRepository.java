package uz.dev.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dev.apelsin.dto.BulkProductsDTO;
import uz.dev.apelsin.dto.HighDemandProductDTO;
import uz.dev.apelsin.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select distinct p.id,(select sum(detail.quantity) from detail where detail.product_id=p.id) as total " +
            "from detail " +
            "left join product p on p.id = detail.product_id " +
            "where (select sum(detail.quantity) from detail where detail.product_id=p.id)>10", nativeQuery = true)
    List<HighDemandProductDTO> findHighDemandProducts();

    @Query(value = "select p.id, p.price " +
            "from detail dt " +
            "right join product p on p.id = dt.product_id " +
            "where dt.quantity>=8 and dt.order_id is not null", nativeQuery = true)
    List<BulkProductsDTO> findBulkProducts();

    @Query(value = "select * from product", nativeQuery = true)
    List<Product> findAll();
}
