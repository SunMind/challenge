package inventi.challenge.api.controller;

import inventi.challenge.domain.service.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/v1/bank/transactions/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class BankController {

    TransactionService transactionService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> importBankStatement(@RequestParam("file") MultipartFile statementFile) throws IOException {
        transactionService.importStatement(statementFile);
        return ResponseEntity.ok("Import successful");
    }

}
