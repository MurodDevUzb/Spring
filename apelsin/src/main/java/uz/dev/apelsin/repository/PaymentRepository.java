package uz.dev.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dev.apelsin.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
