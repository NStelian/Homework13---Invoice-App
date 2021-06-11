package ro.fasttrackit.tema13.payment.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.tema13.payment.dto.Payment;
import ro.fasttrackit.tema13.payment.model.mappers.PaymentMapper;
import ro.fasttrackit.tema13.payment.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @GetMapping
    List<Payment> getAll() {
        return paymentMapper.toApi(paymentService.getAll());
    }

    @PatchMapping("{paymentId}")
    Payment patchPayment(@RequestBody JsonPatch patch, @PathVariable String paymentId) {
        return paymentMapper.toApi(paymentService.patchPayment(patch, paymentId));
    }
}
