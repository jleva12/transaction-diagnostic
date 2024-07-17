package com.syck.transactiondiagnostic.data;

import lombok.Data;

import java.io.PrintWriter;
import java.io.StringWriter;

@Data
public class ExceptionDetail {
    private final String rootExceptionName;
    private final String rootExceptionMessage;
    private final String serializedStackTrace;
    private final String methodName;
    private final int lineNumber;
    private final String filePath;

    /**
     * Represents the details of an exception.
     */
    public ExceptionDetail(Throwable throwable) {
        Throwable rootCause = getRootCause(throwable);
        this.rootExceptionName = rootCause.getClass().getName();
        this.rootExceptionMessage = rootCause.getMessage();
        this.serializedStackTrace = getSerializedStackTrace(throwable);

        StackTraceElement[] rootStackTrace = rootCause.getStackTrace();
        if (rootStackTrace.length > 0) {
            StackTraceElement element = rootStackTrace[0];
            this.methodName = element.getMethodName();
            this.lineNumber = element.getLineNumber();
            this.filePath = element.getFileName();
        }else {
            this.methodName = null;
            this.lineNumber = 0;
            this.filePath = null;
        }
    }

    /**
     *
     * Returns the root cause of the given throwable.
     *
     * @param throwable The throwable for which to find the root cause.
     * @return The root cause of the given throwable.
     */
    private Throwable getRootCause(Throwable throwable) {
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    /**
     * Returns the stack trace of a given Throwable as a String.
     *
     * @param throwable The Throwable for which to retrieve the stack trace.
     * @return The stack trace of the given Throwable as a String.
     */
    private String getSerializedStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
