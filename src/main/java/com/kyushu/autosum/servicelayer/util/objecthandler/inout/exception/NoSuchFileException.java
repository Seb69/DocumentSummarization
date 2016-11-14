package com.kyushu.autosum.servicelayer.util.objecthandler.inout.exception;

import com.kyushu.autosum.servicelayer.util.objecthandler.ExceptionTextGenerator;

/**
 * Runtime Exception : NoSuchFileException
 *
 * @author ANDRE
 * @since 27/05/16
 */
public class NoSuchFileException extends RuntimeException {

    /**
     * Constructs a RuntimeException with the
     * specified detail message.
     *
     * @param message the detail message pertaining to this exception.
     */
    public NoSuchFileException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}.
     *
     * @param cause the cause (which is saved for later retrieval)
     */
    public NoSuchFileException(Throwable cause) {
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
    public NoSuchFileException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param  message the detail message.
     * @param cause the cause.  (A {@code null} value is permitted,
     * and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression whether or not suppression is enabled
     *                          or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     *
     * @since 1.7
     */
    public NoSuchFileException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructs a new runtime exception with the specified detail
     * message,exception, and writable stack trace enabled or disabled.
     *
     * @param  message the detail message.
     * @param ex exception that cause the throw
     *
     * @since 1.7
     */
    public NoSuchFileException(String message, Exception ex) {
        super(message.trim().concat(ExceptionTextGenerator.getText(ex)), ex.getCause(), false, false);
    }

}
