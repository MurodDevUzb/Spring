package uz.dev.apelsin.dto;

import lombok.Data;

@Data
public class CreateInvoiceDTO {
    private Long invoice_number;
    private String status;
}
