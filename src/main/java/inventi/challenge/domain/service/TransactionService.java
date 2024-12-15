package inventi.challenge.domain.service;

import inventi.challenge.api.model.TransactionCSVRepresentation;
import inventi.challenge.domain.mapper.persistence.TransactionEntityMapper;
import inventi.challenge.domain.util.CsvUtils;
import inventi.challenge.persistence.model.TransactionEntity;
import inventi.challenge.persistence.repository.TransactionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TransactionService {

    TransactionRepository transactionRepository;

    @Transactional
    public void importStatement(MultipartFile statementFile) throws IOException {
        List<TransactionCSVRepresentation> transactions = CsvUtils.convertToModel(statementFile, TransactionCSVRepresentation.class);
        log.info("transactions: {}", transactions);

        List<TransactionEntity> transactionEntities = transactions.stream()
                .map(TransactionEntityMapper::entityOf)
                .toList();

        transactionEntities.forEach(transactionRepository::insert);
    }

}
