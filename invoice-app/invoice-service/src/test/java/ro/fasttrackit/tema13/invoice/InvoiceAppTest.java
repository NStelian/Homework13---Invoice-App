package ro.fasttrackit.tema13.invoice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ro.fasttrackit.tema13.invoice.dto.Invoice;
import ro.fasttrackit.tema13.invoice.model.InvoiceEntity;
import ro.fasttrackit.tema13.invoice.repository.InvoiceRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class InvoiceAppTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    InvoiceRepository repository;

    @Test
    @DisplayName("GET /invoices")
    @SneakyThrows
    void getInvoicesTest() {
        repository.save(InvoiceEntity.builder()
                .description("description")
                .amount(1)
                .sender("sender")
                .receiver("receiver")
                .payed(false)
                .build());

        MvcResult mvcResult = mvc.perform(get("/invoices"))
                .andDo(print())
                .andReturn();
        String strResponse = mvcResult.getResponse().getContentAsString();
        List<Invoice> result = new ObjectMapper().readValue(strResponse, new TypeReference<List<Invoice>>() {
        });

        assertThat(result)
                .extracting("description", "amount", "sender", "receiver", "payed")
                .containsExactly(tuple("description", 1, "sender", "receiver", false));
    }
}
