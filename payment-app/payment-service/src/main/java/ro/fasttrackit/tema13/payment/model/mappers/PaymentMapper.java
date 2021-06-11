package ro.fasttrackit.tema13.payment.model.mappers;

import org.springframework.stereotype.Component;
import ro.fasttrackit.tema13.payment.dto.Payment;
import ro.fasttrackit.tema13.payment.model.PaymentEntity;
import ro.fasttrackit.tema13.utils.ModelMappers;

@Component
public class PaymentMapper implements ModelMappers<Payment, PaymentEntity> {

    public Payment toApi(PaymentEntity source) {
        return Payment.builder()
                .id(source.getId())
                .invoiceId(source.getInvoiceId())
                .status(source.getStatus())
                .amountPayable(source.getAmountPayable())
                .build();
    }

    public PaymentEntity toDb(Payment source) {
        return PaymentEntity.builder()
                .id(source.getId())
                .invoiceId(source.getInvoiceId())
                .status(source.getStatus())
                .amountPayable(source.getAmountPayable())
                .build();
    }

}
