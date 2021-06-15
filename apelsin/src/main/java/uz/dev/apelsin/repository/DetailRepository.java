package uz.dev.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dev.apelsin.model.Detail;

@Repository
public interface DetailRepository extends JpaRepository<Detail,Long> {
}
