package uz.dev.apelsin.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MakePaymentDTO {
    private String status;
    private Long id;
    private Timestamp time;
    private int amount;
    private Long invoiceId;

    public MakePaymentDTO() {
    }

    public MakePaymentDTO(String status) {
        this.status = status;
    }
}
