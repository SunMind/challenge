package inventi.challenge.api.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import inventi.challenge.api.model.TransactionCSVRepresentation;
import inventi.challenge.domain.service.TransactionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/v1/bank/transactions/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class TransactionsController {

    TransactionService transactionService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> importBankStatement(@RequestParam("file") MultipartFile statementFile) throws IOException {
        transactionService.importStatement(statementFile);
        return ResponseEntity.ok("Import successful");
    }

    @GetMapping(produces="text/csv")
    public void getBankStatement(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateFrom,
                                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTo,
                                                   HttpServletResponse response) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        String filename = "bankStatements.csv";
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        StatefulBeanToCsv<TransactionCSVRepresentation> writer = new StatefulBeanToCsvBuilder<TransactionCSVRepresentation>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false)
                .build();

        writer.write(transactionService.getStatements(dateFrom, dateTo));
    }

}
