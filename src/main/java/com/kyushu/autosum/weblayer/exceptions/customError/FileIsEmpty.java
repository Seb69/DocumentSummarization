package com.kyushu.autosum.weblayer.exceptions.customError;

/**
 * RuntimeException : file is empty
 * <p>
 * Is thrown when the upload file is empty
 */
public class FileIsEmpty extends RuntimeException {

    /**
     * Constructs a FileIsEmpty with no
     * detail message.
     */
    public FileIsEmpty() {
    }

    /**
     * Constructs a {@code InvalidFile} with the
     * specified detail message.
     *
     * @param message the detail message pertaining to this exception.
     */
    public FileIsEmpty(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link Throwable#getCause()} method).  (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     */
    public FileIsEmpty(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     * <p>
     * <p>Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link Throwable#getCause()} method).  (A {@code null} value
     *                is permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public FileIsEmpty(String message, Throwable cause) {
        super(message, cause);
    }
}
