package inventi.challenge.configuration.mybatis;

public class NoActiveTransactionException extends RuntimeException {

    public NoActiveTransactionException() {
        super("No active transaction found. Consider using annotation"
              + " @Transactional(readOnly = true) to mark read-only MyBatis operations or"
              + " @Transactional for read-write ones.");
    }

}
