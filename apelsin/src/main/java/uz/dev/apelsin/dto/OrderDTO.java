package uz.dev.apelsin.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderDTO {

    private Long id;
    private Date date;
    private Long customer_id;

}

