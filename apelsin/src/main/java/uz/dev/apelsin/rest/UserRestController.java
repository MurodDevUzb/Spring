package uz.dev.apelsin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import uz.dev.apelsin.dto.*;
import uz.dev.apelsin.facade.InvoiceFacade;
import uz.dev.apelsin.facade.ProductFacade;
import uz.dev.apelsin.model.Invoice;
import uz.dev.apelsin.model.Product;
import uz.dev.apelsin.repository.CategoryRepository;
import uz.dev.apelsin.repository.OrderRepository;
import uz.dev.apelsin.repository.ProductRepository;
import uz.dev.apelsin.service.OrderService;
import uz.dev.apelsin.service.PaymentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductFacade productFacade;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private InvoiceFacade invoiceFacade;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/category/list")
    public ResponseEntity<List<CategoryListDTO>> allCategoryByProducts(){
        List<CategoryListDTO> categories = categoryRepository.findCategoriesByProduct();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryListDTO> categoryByProductId(@RequestParam("product_id") String id){
        CategoryListDTO category = categoryRepository.findCategoryByProductId(Long.parseLong(id));

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/product/list")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> products = productRepository.findAll()
                .stream()
                .map(productFacade::productToProductDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/details")
    public ResponseEntity<Object> getProductById(@RequestParam("product_id") String id){
        Product product = productRepository.findById(Long.parseLong(id)).orElse(null);
        if(product==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ProductDTO productDTO = productFacade.productToProductDTO(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<Object> makeOrder(@RequestBody MakeOrderDTO makeOrderDTO){

        Invoice invoice = orderService.makeOrder(makeOrderDTO);
        CreateInvoiceDTO createInvoiceDTO = invoiceFacade.invoiceToInvoiceNumber(invoice);
        return  new ResponseEntity<>(createInvoiceDTO, HttpStatus.OK);
    }

    @GetMapping("/order/details")
    public ResponseEntity<OrderDetailsDTO> getOrderDetailById(@RequestParam("order_id") String id){
        OrderDetailsDTO orderDetailsDTO = orderRepository.findOrderDeatailById(Long.parseLong(id));
        return new ResponseEntity<>(orderDetailsDTO,HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<Object> makePayment(@RequestBody PaymnetIdDTO paymnetIdDTO){

        MakePaymentDTO makePaymentDTO = paymentService.makePayment(paymnetIdDTO.getInvoice_id());
        if(makePaymentDTO.getStatus().equals("FAILED")){
            return new ResponseEntity<>(makePaymentDTO,HttpStatus.EXPECTATION_FAILED);
        }
        return  new ResponseEntity<>(makePaymentDTO, HttpStatus.OK);
    }

    @GetMapping("/payment/details")
    public ResponseEntity<PaymentDetailDTO> getPaymentDetailsById(@RequestParam("id") String id){
        PaymentDetailDTO paymentDetailDTO = paymentService.getPaymentDetailById(Long.parseLong(id));
        return new ResponseEntity<>(paymentDetailDTO,HttpStatus.OK);
    }

}
