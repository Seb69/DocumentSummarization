package com.kyushu.autosum.servicelayer.util.objecthandler;

/**
 * @author ANDRE
 * @since 27/05/16
 */
public class ExceptionTextGenerator {

    /**
     * This method create the text when an Exception is thrown
     * @return the text generated
     */
    public static String getText(Exception ex) {

        // Get cause information
        String[] cause = ex.toString().split(":");
        String causeException = cause[0].substring(cause[0].lastIndexOf('.') + 1);
        String message;
        if (cause.length>1) {
            message = cause[1];
        } else message = "";

        // Get stacktrace
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement;
        if (stackTraceElements.length<3) stackTraceElement = stackTraceElements[2];
        else stackTraceElement = Thread.currentThread().getStackTrace()[4];

        // Retrieve Class Name
        String superClassName = stackTraceElement.getFileName();

        // Delete last 5 characters (.java)
        superClassName = superClassName.substring(0,superClassName.length()-5);

        // Get method name
        String method = stackTraceElement.getMethodName();

        // Get le line of the error
        int line = stackTraceElement.getLineNumber();

        return " | Cause => Exception: ".concat(causeException.trim())
                .concat(", Message: ").concat(message.trim())
                .concat(" | Location => Class: ").concat(superClassName.trim())
                .concat(", Method: ").concat(method.trim())
                .concat(", Line: ").concat(String.valueOf(line).trim());
    }
}
