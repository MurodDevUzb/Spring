package uz.dev.apelsin.service;

import org.springframework.stereotype.Service;
import uz.dev.apelsin.dto.MakePaymentDTO;
import uz.dev.apelsin.dto.PaymentDetailDTO;
import uz.dev.apelsin.exception.InvoiceNotFoundException;
import uz.dev.apelsin.exception.PaymentNotFoundException;
import uz.dev.apelsin.facade.PaymentFacade;
import uz.dev.apelsin.model.Invoice;
import uz.dev.apelsin.model.Payment;
import uz.dev.apelsin.repository.InvoiceRepository;
import uz.dev.apelsin.repository.PaymentRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentFacade paymentFacade;

    public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository, PaymentFacade paymentFacade) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
        this.paymentFacade = paymentFacade;
    }

    public MakePaymentDTO makePayment(Long id){
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(()->new InvoiceNotFoundException("Invoice not found"));
        Date nowDate = new Date(Calendar.getInstance().getTime().getTime());
        if(invoice.getDue().before(nowDate)){
            return new MakePaymentDTO("FAILED");
        }
        Payment payment = new Payment();
        payment.setAmount(invoice.getAmount());
        payment.setInvoice(invoice);
        payment.setTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        MakePaymentDTO makePaymentDTO =  paymentFacade.paymentToMakePaymentDTO(paymentRepository.save(payment));
        return makePaymentDTO;
    }

    public PaymentDetailDTO getPaymentDetailById(Long id){
         Payment payment = paymentRepository.findById(id).orElseThrow(()-> new PaymentNotFoundException("Payment not found"));
         return paymentFacade.paymentToPaymentDetailDTO(payment);
    }
}
