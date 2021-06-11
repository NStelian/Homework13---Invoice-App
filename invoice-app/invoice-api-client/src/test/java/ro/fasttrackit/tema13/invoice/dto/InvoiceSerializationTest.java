package ro.fasttrackit.tema13.invoice.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceSerializationTest {

    @Test
    void invoiceToJson() throws JsonProcessingException {
        Invoice invoice = new Invoice("abc", "description", 1, "sender", "receiver", false);
        String result = new ObjectMapper().writeValueAsString(invoice);

        assertEquals(result, """
                {"id":"abc","description":"description","amount":1.0,"sender":"sender","receiver":"receiver","payed":false}
                """.trim());
    }

    @Test
    void jsonToInvoice() throws JsonProcessingException {
        String json = """
                {"id":"abc","description":"description","amount":1, "sender": "sender","receiver": "receiver", "payed": false}
                """;
        Invoice result = new ObjectMapper().readValue(json, Invoice.class);
        assertEquals(result, new Invoice("abc", "description", 1, "sender", "receiver", false));
    }

}