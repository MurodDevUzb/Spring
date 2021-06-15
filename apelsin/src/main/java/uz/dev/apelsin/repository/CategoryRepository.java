package uz.dev.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.dev.apelsin.dto.CategoryListDTO;
import uz.dev.apelsin.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findCategoryById(Long id);

    @Query(value = "select distinct category.name " +
            "from category " +
            "left join product p on category.id = p.category_id " +
            "where p.category_id is not null", nativeQuery = true)
    List<CategoryListDTO> findCategoriesByProduct();

    @Query(value = "select category.name " +
            "from category " +
            "left join product p on category.id = p.category_id " +
            "where p.id= :id ", nativeQuery = true)
    CategoryListDTO findCategoryByProductId(@Param("id") Long id);
}
