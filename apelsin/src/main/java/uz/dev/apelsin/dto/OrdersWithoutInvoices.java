package uz.dev.apelsin.dto;

import java.sql.Date;

public interface OrdersWithoutInvoices {
    Long getId();
    Date getDate();
    int  getTotalPrice();
}
