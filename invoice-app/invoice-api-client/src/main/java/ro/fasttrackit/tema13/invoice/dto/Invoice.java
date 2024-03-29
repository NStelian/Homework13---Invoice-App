package ro.fasttrackit.tema13.invoice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Invoice.InvoiceBuilder.class)
public class Invoice {
    String id;
    String description;
    double amount;
    String sender;
    String receiver;
    boolean payed;

    @JsonPOJOBuilder(withPrefix = "")
    public static class InvoiceBuilder {
    }
}
