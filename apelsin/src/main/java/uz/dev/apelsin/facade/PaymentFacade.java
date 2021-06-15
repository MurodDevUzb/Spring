package uz.dev.apelsin.facade;

import org.springframework.stereotype.Component;
import uz.dev.apelsin.dto.MakePaymentDTO;
import uz.dev.apelsin.dto.PaymentDetailDTO;
import uz.dev.apelsin.model.Payment;

@Component
public class PaymentFacade {

    public MakePaymentDTO paymentToMakePaymentDTO(Payment payment){
        MakePaymentDTO makePaymentDTO = new MakePaymentDTO();
        makePaymentDTO.setId(payment.getId());
        makePaymentDTO.setAmount(payment.getAmount());
        makePaymentDTO.setInvoiceId(payment.getInvoice().getId());
        makePaymentDTO.setTime(payment.getTime());
        makePaymentDTO.setStatus("SUCCESS");
        return makePaymentDTO;
    }

    public PaymentDetailDTO paymentToPaymentDetailDTO(Payment payment){
        PaymentDetailDTO paymentDetailDTO = new PaymentDetailDTO();
        paymentDetailDTO.setId(payment.getId());
        paymentDetailDTO.setAmount(payment.getAmount());
        paymentDetailDTO.setInvoiceId(payment.getInvoice().getId());
        paymentDetailDTO.setTime(payment.getTime());
        return paymentDetailDTO;
    }
}
