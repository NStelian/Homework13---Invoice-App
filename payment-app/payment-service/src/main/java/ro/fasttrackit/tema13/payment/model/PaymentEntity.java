package ro.fasttrackit.tema13.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.fasttrackit.tema13.enums.StatusEnum;

@Document("payments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    private String id;
    private String invoiceId;
    private StatusEnum status;
    private double amountPayable;
}
