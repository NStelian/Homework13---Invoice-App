package ro.fasttrackit.tema13.payment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ro.fasttrackit.tema13.enums.StatusEnum;
import ro.fasttrackit.tema13.exceptions.ResourceNotFoundException;
import ro.fasttrackit.tema13.payment.model.PaymentEntity;
import ro.fasttrackit.tema13.payment.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentNotifications paymentNotifications;
    private final ObjectMapper mapper;

    public List<PaymentEntity> getAll() {
        return paymentRepository.findAll();
    }

    public PaymentEntity addPayment(PaymentEntity newPayment) {
        newPayment.setId(null);
        return paymentRepository.save(newPayment);
    }

    @SneakyThrows
    public PaymentEntity patchPayment(JsonPatch patch, String paymentId) {
        PaymentEntity dbPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find payment with id " + paymentId));

        JsonNode patchedPaymentJson = patch.apply(mapper.valueToTree(dbPayment));
        PaymentEntity patchedPayment = mapper.treeToValue(patchedPaymentJson, PaymentEntity.class);
        return replacePayment(paymentId, patchedPayment);
    }

    public PaymentEntity replacePayment(String paymentId, PaymentEntity newPayment) {
        newPayment.setId(paymentId);
        if (newPayment.getStatus().equals(StatusEnum.DONE)) {
            paymentNotifications.notifyPaymentAdded(newPayment);
        }
        PaymentEntity dbPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find payment with id " + paymentId));
        copyPayment(newPayment, dbPayment);
        return paymentRepository.save(dbPayment);
    }

    private void copyPayment(PaymentEntity newPayment, PaymentEntity dbPayment) {
        dbPayment.setInvoiceId(newPayment.getInvoiceId());
        dbPayment.setAmountPayable(newPayment.getAmountPayable());
        dbPayment.setStatus(newPayment.getStatus());
    }
}
