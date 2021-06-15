package uz.dev.apelsin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.dev.apelsin.dto.MakeOrderDTO;
import uz.dev.apelsin.exception.CustomerNotFoundException;
import uz.dev.apelsin.exception.ProductNotFoundException;
import uz.dev.apelsin.model.*;
import uz.dev.apelsin.repository.*;

import java.sql.Date;
import java.util.Calendar;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final DetailRepository detailRepository;
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, DetailRepository detailRepository, InvoiceRepository invoiceRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.detailRepository = detailRepository;
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Invoice makeOrder(MakeOrderDTO makeOrderDTO){

        Order order = new Order();
        Customer customer = customerRepository.findById(makeOrderDTO.getCustomer_id()).orElseThrow(()->new CustomerNotFoundException("Customer not found"));
        order.setCustomer(customer);
        order.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        Order newOrder = orderRepository.save(order);

        Detail detail = new Detail();
        Product product = productRepository.findById(makeOrderDTO.getProduct_id()).orElseThrow(()->new ProductNotFoundException("Product not Found"));
        detail.setOrder(newOrder);
        detail.setProduct(product);
        detail.setQuantity(makeOrderDTO.getQuantity());
        detailRepository.save(detail);
        Invoice invoice = new Invoice();
        invoice.setAmount(product.getPrice()*makeOrderDTO.getQuantity());
        invoice.setOrder(newOrder);
        invoice.setIssued(new Date(Calendar.getInstance().getTime().getTime()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);
        invoice.setDue(new Date(calendar.getTime().getTime()));
        return invoiceRepository.save(invoice);
    }

}
