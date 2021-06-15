package uz.dev.apelsin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.dev.apelsin.dto.*;
import uz.dev.apelsin.facade.CustomerFacade;
import uz.dev.apelsin.facade.InvoiceFacade;
import uz.dev.apelsin.facade.OrderFacade;
import uz.dev.apelsin.repository.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/admin")
public class RestController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InvoiceFacade invoiceFacade;
    @Autowired
    private OrderFacade orderFacade;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerFacade customerFacade;
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/expired_invoices")
    public ResponseEntity<List<InvoiceDTO>> expiredInvoices(){


        List<InvoiceDTO> invoices = invoiceRepository.findInvoicesByIssuedAfterAndDue()
                .stream()
                .map(invoiceFacade::invoiceToInvoiceDTO)
                .collect(Collectors.toList());


        return  new ResponseEntity<>(invoices, HttpStatus.OK);

    }

    @GetMapping("/wrong_date_invoices")
    public ResponseEntity<List<WrongDateInvoiceDTO>> wrongDateInvoices(){

        List<WrongDateInvoiceDTO> invoices = invoiceRepository.findwronginvoice()
                .stream()
                .map(invoiceFacade::wrongDateInvoiceDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(invoices,HttpStatus.OK);
    }

    @GetMapping("/orders_without_details")
    public ResponseEntity<List<OrderDTO>> ordersWithoutDetails(){

        Date date = Date.valueOf("2016-09-06");

        List<OrderDTO> orders = orderRepository.findOrdersByDateIsBefore(date)
                .stream()
                .map(orderFacade::orderToOrderDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/customers_without_orders")
    public ResponseEntity<List<CustomerDTO>> customerWithoutOrders(){

            List<Long> listId = customerRepository.findCustomersWithoutOrdersId();
            List<CustomerDTO> customers = customerRepository.findCustomersById(listId)
                    .stream()
                    .map(customerFacade::customerToCustomerDTO)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("/customers_last_orders")
    public ResponseEntity<List<CustomerLastOrderDTO>> customersLastOrders(){
        List<CustomerLastOrderDTO> customers = customerRepository.findCustomersLastOrders();

        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("/overpaid_invoices")
    public ResponseEntity<List<InvoiceOverpaidDTO>> overpaidInvoices(){
        List<InvoiceOverpaidDTO> invoices = invoiceRepository.findOverpaidInvoice();
        return new ResponseEntity<>(invoices,HttpStatus.OK);
    }

    @GetMapping("/high_demand_products")
    public ResponseEntity<List<HighDemandProductDTO>> highDemandProducts(){
        List<HighDemandProductDTO>  products = productRepository.findHighDemandProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/bulk_products")
    public ResponseEntity<List<BulkProductsDTO>> bulkProducts(){
        List<BulkProductsDTO>  products = productRepository.findBulkProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping("/number_of_products_in_year")
    public ResponseEntity<List<NumberOfPRoductInYaerDTO>>numberOfPRoductsInYear(){
        List<NumberOfPRoductInYaerDTO> orders = orderRepository.findNumberofProductsInYear();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/orders_without_invoices")
    public ResponseEntity<List<OrdersWithoutInvoices>> ordersWithoutInvoices(){
        List<OrdersWithoutInvoices> orders = orderRepository.findOrdersWithoutInvoices();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
