package uz.dev.apelsin.dto;

import java.sql.Date;

public interface OrderDetailsDTO {
    Long getDetailId();
    int getQuantity();
    Long getProductId();
    Date getDate();
    Long getCustomerId();
    String getProductName();
}
