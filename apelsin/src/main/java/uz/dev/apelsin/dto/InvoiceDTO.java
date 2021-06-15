package uz.dev.apelsin.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class InvoiceDTO {

    private Long id;
    private int amount;
    private Date issued;
    private Date due;
}
