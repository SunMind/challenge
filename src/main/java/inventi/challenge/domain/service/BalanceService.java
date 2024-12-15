package inventi.challenge.domain.service;

import inventi.challenge.persistence.repository.TransactionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BalanceService {

    TransactionRepository transactionRepository;

    @Transactional
    public List<Map<String, BigDecimal>> getBalance(String accountNumber, LocalDateTime dateFrom, LocalDateTime dateTo) {
        return transactionRepository.getBalance(accountNumber, dateFrom, dateTo);
    }
}
