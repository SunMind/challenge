package inventi.challenge.domain.mapper.persistence;

import inventi.challenge.api.model.TransactionCSVRepresentation;
import inventi.challenge.persistence.model.TransactionEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionEntityMapper {

    public static TransactionEntity entityOf(TransactionCSVRepresentation dto) {
        return TransactionEntity.builder()
                .accountNumber(dto.getAccountNumber())
                .operationDate(dto.getOperationDate())
                .beneficiaryNumber(dto.getBeneficiaryNumber())
                .amount(dto.getAmount())
                .comment(dto.getComment())
                .currency(dto.getCurrency())
                .build();
    }

}
