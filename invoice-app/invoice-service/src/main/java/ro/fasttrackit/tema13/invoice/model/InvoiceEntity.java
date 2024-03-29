package ro.fasttrackit.tema13.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Invoices")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity {
    @Id
    private String id;
    private String description;
    private double amount;
    private String sender;
    private String receiver;
    private Boolean payed;

}
