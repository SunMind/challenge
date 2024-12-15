package inventi.challenge.persistence.repository;

import inventi.challenge.persistence.model.TransactionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface TransactionRepository {

    @Insert("""
     INSERT INTO BANK_TRANSACTION (
      account_number,
      beneficiary_number,
      operation_date,
      comment,
      amount,
      currency_id
    ) VALUES (
      #{accountNumber},
      #{beneficiaryNumber},
      #{operationDate},
      #{comment},
      #{amount},
              SELECT id
              FROM CURRENCY
              WHERE code = #{currency}
    )
    """)
    void insert(TransactionEntity entity);
}
