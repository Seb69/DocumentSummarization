package com.kyushu.autosum.servicelayer.convertorservice.exceptions;

import com.kyushu.autosum.servicelayer.util.objecthandler.ExceptionTextGenerator;

/**
 * Runtime Exception : FailedToConvert
 *
 * @author ANDRE
 * @since 28/05/16
 */
public class FailedToConvert extends RuntimeException {

    /**
     * Constructs a RuntimeException with the
     * specified detail message.
     *
     * @param message the detail message pertaining to this exception.
     */
    public FailedToConvert(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}.
     *
     * @param cause the cause (which is saved for later retrieval)
     */
    public FailedToConvert(Throwable cause) {
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
     * @param cause   the cause (which is saved for later retrieval)
     */
    public FailedToConvert(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail
     * message,exception, and writable stack trace enabled or disabled.
     *
     * @param message the detail message.
     * @param ex      exception that cause the throw
     * @since 1.7
     */
    public FailedToConvert(String message, Exception ex) {
        super(message.trim().concat(ExceptionTextGenerator.getText(ex)), ex.getCause(), false, false);
    }

}
