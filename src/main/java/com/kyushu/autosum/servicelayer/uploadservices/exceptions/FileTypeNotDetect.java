package com.kyushu.autosum.servicelayer.uploadservices.exceptions;

import java.io.IOException;

/**
 * @author ANDRE
 * @since 22/05/16
 */
public class FileTypeNotDetect extends IOException {

    private static final long serialVersionUID = 5162714444389028792L;

    /**
     * Constructs a FileTypeNotDetect with no
     * detail message.
     */
    public FileTypeNotDetect() {
    }

    /**
     * Constructs a {@code InvalidFile} with the
     * specified detail message.
     *
     * @param message the detail message pertaining to this exception.
     */
    public FileTypeNotDetect(String message) {
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
    public FileTypeNotDetect(Throwable cause) {
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
    public FileTypeNotDetect(String message, Throwable cause) {
        super(message, cause);
    }
}
