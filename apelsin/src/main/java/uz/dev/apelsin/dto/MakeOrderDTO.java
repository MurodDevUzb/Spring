package uz.dev.apelsin.dto;

import lombok.Data;

@Data
public class MakeOrderDTO {
    private Long customer_id;
    private Long product_id;
    private short quantity;
}
