package ro.fasttrackit.tema13.payment.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import ro.fasttrackit.tema13.enums.StatusEnum;

@Value
@Builder
@JsonDeserialize(builder = Payment.PaymentBuilder.class)
public class Payment {
    String id;
    String invoiceId;
    StatusEnum status;
    double amountPayable;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PaymentBuilder {
    }
}
