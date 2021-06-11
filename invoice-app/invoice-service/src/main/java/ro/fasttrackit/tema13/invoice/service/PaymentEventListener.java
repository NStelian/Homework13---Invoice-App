package ro.fasttrackit.tema13.invoice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.tema13.payment.events.PaymentEvent;

import static ro.fasttrackit.tema13.payment.events.PaymentEventType.ADDED;


@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventListener {
    private final InvoiceService invoiceService;

    @RabbitListener(queues = "#{paymentQueue.name}")
    void processPaymentEvent(PaymentEvent event) {
        if (event.getType() == ADDED) {
            invoiceService.updateInvoicePayed(event.getPayment().getInvoiceId());
            log.info("Updated invoice payed field to true");
        }
    }
}
