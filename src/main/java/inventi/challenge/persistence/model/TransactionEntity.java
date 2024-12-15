package inventi.challenge.persistence.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    String accountNumber;
    String beneficiaryNumber;
    Timestamp operationDate;
    String comment;
    Double amount;
    String currency;

}
