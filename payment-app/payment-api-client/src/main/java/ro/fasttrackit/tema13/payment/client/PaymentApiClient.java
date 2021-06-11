package ro.fasttrackit.tema13.payment.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.fasttrackit.tema13.payment.dto.Payment;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Component
public class PaymentApiClient {
    private final String baseUrl;
    private final RestTemplate rest;

    public PaymentApiClient(@Value("${payment.service.location:NOT_DEFINED}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.rest = new RestTemplate();
    }

    public List<Payment> getAllPayments() {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/payments")
                .toUriString();
        log.info("Getting all payments: " + url);
        return rest.exchange(url, GET, new HttpEntity<>(null), new ParameterizedTypeReference<List<Payment>>() {
        }).getBody();
    }

//    public Invoice addInvoice(Invoice invoice) {
//        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
//                .path("/invoices")
//                .toUriString();
//
//        return rest.postForObject(url, invoice, Invoice.class);
//    }
//
//    public Invoice deleteInvoice(String invoiceId) {
//        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
//                .path("/invoices/")
//                .path(invoiceId)
//                .toUriString();
//        return rest.exchange(url, DELETE, new HttpEntity<>(null), Invoice.class).getBody();
//    }
//
//    public Optional<Invoice> getById(String invoiceId) {
//        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
//                .path("/invoices/")
//                .path(invoiceId)
//                .toUriString();
//        try {
//            return ofNullable(rest.getForObject(url, Invoice.class));
//        } catch (HttpClientErrorException ex) {
//            return empty();
//        }
//    }
}
