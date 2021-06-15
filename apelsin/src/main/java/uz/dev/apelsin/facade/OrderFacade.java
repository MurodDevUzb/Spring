package uz.dev.apelsin.facade;

import org.springframework.stereotype.Component;
import uz.dev.apelsin.dto.OrderDTO;
import uz.dev.apelsin.model.Order;

@Component
public class OrderFacade {

    public OrderDTO orderToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setDate(order.getDate());
        orderDTO.setCustomer_id(order.getCustomer().getId());

        return orderDTO;
    }

}
