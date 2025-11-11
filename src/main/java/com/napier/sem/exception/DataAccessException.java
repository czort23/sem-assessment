package com.napier.sem.exception;
/**
 * Custom runtime exception used to wrap and rethrow SQL or data access errors
 * in a consistent and readable way across all DAO classes.
 *
 * <p>This allows database-related errors to be handled uniformly without
 * requiring each DAO method to declare checked exceptions.</p>
 *
 * Example usage:
 * <pre>
 *     throw new DataAccessException("Failed to fetch countries", e);
 * </pre>
 */
public class DataAccessException extends RuntimeException {
    /**
     * Creates a new DataAccessException with a detailed message and root cause.
     *
     * @param message A human-readable description of what operation failed.
     * @param cause   The underlying exception (e.g., {@link java.sql.SQLException}).
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
