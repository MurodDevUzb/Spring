package uz.dev.apelsin.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentDetailDTO {
    private Long id;
    private Timestamp time;
    private int amount;
    private Long invoiceId;
}
