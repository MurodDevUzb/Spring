package uz.dev.apelsin.facade;

import org.springframework.stereotype.Component;
import uz.dev.apelsin.dto.CreateInvoiceDTO;
import uz.dev.apelsin.dto.InvoiceDTO;
import uz.dev.apelsin.dto.MakeOrderDTO;
import uz.dev.apelsin.dto.WrongDateInvoiceDTO;
import uz.dev.apelsin.model.Invoice;

@Component
public class InvoiceFacade {

    public InvoiceDTO invoiceToInvoiceDTO(Invoice invoice){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setAmount(invoice.getAmount());
        invoiceDTO.setIssued(invoice.getIssued());
        invoiceDTO.setDue(invoice.getDue());
        return invoiceDTO;
    }

    public WrongDateInvoiceDTO wrongDateInvoiceDTO(Invoice invoice){
        WrongDateInvoiceDTO wrongDateInvoiceDTO = new WrongDateInvoiceDTO();
        wrongDateInvoiceDTO.setId(invoice.getId());
        wrongDateInvoiceDTO.setIssued(invoice.getIssued());
        wrongDateInvoiceDTO.setOrder_id(invoice.getOrder().getId());
        return wrongDateInvoiceDTO;
    }

    public CreateInvoiceDTO invoiceToInvoiceNumber(Invoice invoice){
        CreateInvoiceDTO createInvoiceDTO = new CreateInvoiceDTO();
        createInvoiceDTO.setInvoice_number(invoice.getId());
        createInvoiceDTO.setStatus("Success");
        return createInvoiceDTO;
    }
}
