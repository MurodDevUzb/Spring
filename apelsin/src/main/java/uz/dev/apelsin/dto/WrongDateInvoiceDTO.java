package uz.dev.apelsin.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class WrongDateInvoiceDTO {
    private Long id;
    private Date issued;
    private Long order_id;
}
