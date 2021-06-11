package ro.fasttrackit.tema13.invoice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fasttrackit.tema13.exceptions.ResourceNotFoundException;
import ro.fasttrackit.tema13.invoice.model.InvoiceEntity;
import ro.fasttrackit.tema13.invoice.repository.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceNotifications invoiceNotifications;

    public List<InvoiceEntity> getAll() {
        return invoiceRepository.findAll();
    }

    public Optional<InvoiceEntity> deleteInvoice(String invoiceId) {
        Optional<InvoiceEntity> invoice = invoiceRepository.findById(invoiceId);

        invoice.ifPresent(this::deleteExistingInvoice);
        return invoice;
    }

    private void deleteExistingInvoice(InvoiceEntity invoiceEntity) {
        invoiceRepository.delete(invoiceEntity);
    }

    public InvoiceEntity addInvoice(InvoiceEntity newInvoice) {
        newInvoice.setId(null);
        InvoiceEntity invoice = invoiceRepository.save(newInvoice);
        invoiceNotifications.notifyInvoiceAdded(invoice);

        return invoice;
    }

    public Optional<InvoiceEntity> getInvoice(String invoiceId) {
        return invoiceRepository.findById(invoiceId);
    }

    public InvoiceEntity updateInvoicePayed(String invoiceId) {
        InvoiceEntity invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice with id " + invoiceId + " is not found"));
        invoice.setPayed(true);
        return invoiceRepository.save(invoice);
    }
}
