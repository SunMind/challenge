package inventi.challenge.configuration.mybatis;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.ibatis.session.*;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyBatisSqlSessionFactory implements SqlSessionFactory {

    SqlSessionFactory target;

    private void assertActiveTransactionExists() {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            throw new NoActiveTransactionException();
        }
    }

    @Override
    public SqlSession openSession() {
        assertActiveTransactionExists();
        return target.openSession();
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        assertActiveTransactionExists();
        return target.openSession(autoCommit);
    }

    @Override
    public SqlSession openSession(Connection connection) {
        assertActiveTransactionExists();
        return target.openSession(connection);
    }

    @Override
    public SqlSession openSession(TransactionIsolationLevel level) {
        assertActiveTransactionExists();
        return target.openSession(level);
    }

    @Override
    public SqlSession openSession(ExecutorType execType) {
        assertActiveTransactionExists();
        return target.openSession(execType);
    }

    @Override
    public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
        assertActiveTransactionExists();
        return target.openSession(execType, autoCommit);
    }

    @Override
    public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
        assertActiveTransactionExists();
        return target.openSession(execType, level);
    }

    @Override
    public SqlSession openSession(ExecutorType execType, Connection connection) {
        assertActiveTransactionExists();
        return target.openSession(execType, connection);
    }

    @Override
    public Configuration getConfiguration() {
        return target.getConfiguration();
    }

}
