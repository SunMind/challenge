package inventi.challenge.api.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCSVRepresentation {

    @CsvBindByName(column = "ACCOUNT NUMBER",required = true)
    String accountNumber;
    @CsvDate("yyyy-MM-dd HH:mm:ss")
    @CsvBindByName(column = "OPERATION DATE/TIME",required = true)
    Timestamp operationDate;
    @CsvBindByName(column = "BENEFICIARY")
    String beneficiaryNumber;
    @CsvBindByName(column = "COMMENT",required = true)
    String comment;
    @CsvBindByName(column = "AMOUNT",required = true)
    Double amount;
    @CsvBindByName(column = "CURRENCY",required = true)
    String currency;
}
