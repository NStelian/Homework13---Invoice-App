package ro.fasttrackit.tema13.invoice.model.mappers;

import org.springframework.stereotype.Component;
import ro.fasttrackit.tema13.invoice.dto.Invoice;
import ro.fasttrackit.tema13.invoice.model.InvoiceEntity;
import ro.fasttrackit.tema13.utils.ModelMappers;

@Component
public class InvoiceMapper implements ModelMappers<Invoice, InvoiceEntity> {

    public Invoice toApi(InvoiceEntity source) {
        return Invoice.builder()
                .id(source.getId())
                .description(source.getDescription())
                .amount(source.getAmount())
                .sender(source.getSender())
                .receiver(source.getReceiver())
                .payed(source.getPayed())
                .build();
    }

    public InvoiceEntity toDb(Invoice source) {
        return InvoiceEntity.builder()
                .id(source.getId())
                .description(source.getDescription())
                .amount(source.getAmount())
                .sender(source.getSender())
                .receiver(source.getReceiver())
                .payed(source.isPayed())
                .build();
    }

}
