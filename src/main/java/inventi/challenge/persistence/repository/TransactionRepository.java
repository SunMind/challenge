package inventi.challenge.persistence.repository;

import inventi.challenge.persistence.model.TransactionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

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

    @Select("""
      SELECT
        t.ACCOUNT_NUMBER as accountNumber,
        t.BENEFICIARY_NUMBER as beneficiaryNumber,
        t.OPERATION_DATE as operationDate,
        t.COMMENT as comment,
        t.AMOUNT as amount,
        c.CODE as currency
      FROM
        BANK_TRANSACTION t
      JOIN
        CURRENCY c
      ON
        t.CURRENCY_ID = c.ID
      WHERE
        (#{dateFrom} IS NULL OR t.OPERATION_DATE >= #{dateFrom})
        AND (#{dateTo} IS NULL OR t.OPERATION_DATE <= #{dateTo})
    """)
    List<TransactionEntity> findByDate(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

}
