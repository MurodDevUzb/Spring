package uz.dev.apelsin.facade;

import org.springframework.stereotype.Component;
import uz.dev.apelsin.dto.CustomerDTO;
import uz.dev.apelsin.model.Customer;

@Component
public class CustomerFacade {

    public CustomerDTO customerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setCountry(customer.getCountry());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }

    public CustomerDTO objectToCustomerDTO(Object ob){
        CustomerDTO customerDTO = new CustomerDTO();
        return customerDTO;
    }

}
