package uz.dev.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dev.apelsin.dto.InvoiceOverpaidDTO;
import uz.dev.apelsin.model.Invoice;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    @Query(value = "select * from invoice i where i.id=1", nativeQuery = true)
    List<Invoice> findInvoicesByIssuedAfterAndDue();


    @Query(value = "SELECT * FROM invoice inv left join order_table ot on ot.id = inv.order_id where inv.issued<ot.date",nativeQuery = true)
    List<Invoice> findwronginvoice();
    

    @Query(value = "select distinct inv.id, ((select sum(payment.amount) from payment " +
            " where inv.id=payment.invoice_id)-inv.amount) as amount " +
            "from invoice inv " +
            "left join payment p on inv.id = p.invoice_id " +
            "where ((select sum(payment.amount) from payment " +
            " where inv.id=payment.invoice_id)-inv.amount) >0", nativeQuery = true)
    List<InvoiceOverpaidDTO> findOverpaidInvoice();


}
