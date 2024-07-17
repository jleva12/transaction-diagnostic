package com.syck.transactiondiagnostic.data;

import lombok.Data;

import java.time.Instant;

@Data
public class LogEntry {
    private final Instant timestamp;
    private final String threadName;
    private final String level;
    private final String callingClassName;
    private final String message;
    private final ExceptionDetail exceptionDetail;

    public LogEntry(String level, String message, String callingClassName,  Throwable throwable) {
        this.timestamp = Instant.now();
        this.threadName = Thread.currentThread().getName();
        this.level = level;
        this.callingClassName = callingClassName;
        this.message = message;
        this.exceptionDetail = throwable != null ? new ExceptionDetail(throwable) : null;
    }


}
