package ro.fasttrackit.tema13.invoice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.tema13.invoice.events.InvoiceEvent;
import ro.fasttrackit.tema13.invoice.model.InvoiceEntity;
import ro.fasttrackit.tema13.invoice.model.mappers.InvoiceMapper;

import static ro.fasttrackit.tema13.invoice.events.InvoiceEventType.ADDED;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceNotifications {
    private final RabbitTemplate rabbit;
    private final InvoiceMapper mapper;
    private final FanoutExchange invoiceExchange;

    public void notifyInvoiceAdded(InvoiceEntity invoice) {
        InvoiceEvent event = InvoiceEvent.builder()
                .invoice(mapper.toApi(invoice))
                .type(ADDED)
                .build();
        log.info("Sending event : " + event);
        rabbit.convertAndSend(invoiceExchange.getName(), "", event);
    }
}
