package com.syck.transactiondiagnostic.proxy;

import com.syck.transactiondiagnostic.context.DiagnosticContext;
import com.syck.transactiondiagnostic.data.DiagnosticRecord;
import com.syck.transactiondiagnostic.data.Level;
import com.syck.transactiondiagnostic.data.LogEntry;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.util.Optional;
import java.util.function.Consumer;

import static com.syck.transactiondiagnostic.context.DiagnosticContextHolder.ctx;

public class LoggerAdapter implements Logger {
    private final Logger delegate;

    public LoggerAdapter(Logger delegate) {
        this.delegate = delegate;
    }

    protected String format(String s, Object... args) {
        return String.format(s.replaceAll("\\{}", "%s"), args);
    }

    protected void addToDiagnosticIfPresentAndLog(String message, Level level, Throwable throwable, Consumer<Logger> log) {
        final DiagnosticContext ctx = ctx();
        if(ctx.get().isPresent()) {
            final Optional<DiagnosticRecord> diagnosticRecord = ctx.get();
            diagnosticRecord.ifPresent(d -> d.addLogEntry(new LogEntry(level.name(), message,getName(), throwable)));
            log.accept(delegate);
        } else {
            log.accept(delegate);
        }
    }

    private String determineCallingClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean foundLoggerAdapter = false;
        for (StackTraceElement element : stackTrace) {
            // Skip until we find the first call from LoggerAdapter
            if (element.getClassName().equals(this.getClass().getName())) {
                foundLoggerAdapter = true;
                continue;
            }
            // After finding LoggerAdapter, return the next class that's not a part of Java libraries
            if (foundLoggerAdapter && !element.getClassName().startsWith("java.") && !element.getClassName().startsWith("com.syck.transactiondiagnostic.proxy.LoggerAdapter")) {
                return element.getClassName();
            }
        }
        return "Unknown";
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return delegate.isTraceEnabled();
    }

    @Override
    public void trace(String s) {
        addToDiagnosticIfPresentAndLog(s, Level.TRACE, null, l -> l.trace(s));
    }

    @Override
    public void trace(String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.TRACE, null, l -> l.trace(s, o));
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.TRACE, null, l -> l.trace(s, o, o1));
    }

    @Override
    public void trace(String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.TRACE, null, l -> l.trace(s, objects));
    }

    @Override
    public void trace(String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.TRACE, throwable, l -> l.trace(s, throwable));
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return delegate.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String s) {
        addToDiagnosticIfPresentAndLog(s, Level.TRACE, null, l -> l.trace(marker, s));
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.TRACE, null, l -> l.trace(marker, s, o));
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.TRACE, null, l -> l.trace(marker, s, o, o1));
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.TRACE, null, l -> l.trace(marker, s, objects));
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.TRACE, throwable, l -> l.trace(marker, s, throwable));
    }

    @Override
    public boolean isDebugEnabled() {
        return delegate.isDebugEnabled();
    }

    @Override
    public void debug(String s) {
        addToDiagnosticIfPresentAndLog(s, Level.DEBUG, null, l -> l.debug(s));
    }

    @Override
    public void debug(String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.DEBUG, null, l -> l.debug(s, o));
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.DEBUG, null, l -> l.debug(s, o, o1));
    }

    @Override
    public void debug(String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.DEBUG, null, l -> l.debug(s, objects));
    }

    @Override
    public void debug(String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.DEBUG, throwable, l -> l.debug(s, throwable));
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return delegate.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String s) {
        addToDiagnosticIfPresentAndLog(s, Level.DEBUG, null, l -> l.debug(marker, s));
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.DEBUG, null, l -> l.debug(marker, s, o));
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.DEBUG, null, l -> l.debug(marker, s, o, o1));
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.DEBUG, null, l -> l.debug(marker, s, objects));
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.DEBUG, throwable, l -> l.debug(marker, s, throwable));
    }

    @Override
    public boolean isInfoEnabled() {
        return delegate.isInfoEnabled();
    }

    @Override
    public void info(String s) {
        addToDiagnosticIfPresentAndLog(s, Level.INFO, null, l -> l.info(s));
    }

    @Override
    public void info(String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.INFO, null, l -> l.info(s, o));
    }

    @Override
    public void info(String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.INFO, null, l -> l.info(s, o, o1));
    }

    @Override
    public void info(String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.INFO, null, l -> l.info(s, objects));
    }

    @Override
    public void info(String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.INFO, throwable, l -> l.info(s, throwable));
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return delegate.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String s) {
        addToDiagnosticIfPresentAndLog(s, Level.INFO, null, l -> l.info(marker, s));
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.INFO, null, l -> l.info(marker, s, o));
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.INFO, null, l -> l.info(marker, s, o, o1));
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.INFO, null, l -> l.info(marker, s, objects));
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.INFO, throwable, l -> l.info(marker, s, throwable));
    }

    @Override
    public boolean isWarnEnabled() {
        return delegate.isWarnEnabled();
    }

    @Override
    public void warn(String s) {
        addToDiagnosticIfPresentAndLog(s, Level.WARN, null, l -> l.warn(s));
    }

    @Override
    public void warn(String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.WARN, null, l -> l.warn(s, o));
    }

    @Override
    public void warn(String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.WARN, null, l -> l.warn(s, objects));
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.WARN, null, l -> l.warn(s, o, o1));
    }

    @Override
    public void warn(String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.WARN, throwable, l -> l.warn(s, throwable));
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return delegate.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String s) {
        addToDiagnosticIfPresentAndLog(s, Level.WARN, null, l -> l.warn(marker, s));
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.WARN, null, l -> l.warn(marker, s, o));
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.WARN, null, l -> l.warn(marker, s, o, o1));
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.WARN, null, l -> l.warn(marker, s, objects));
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.WARN, throwable, l -> l.warn(marker, s, throwable));
    }

    @Override
    public boolean isErrorEnabled() {
        return delegate.isErrorEnabled();
    }

    @Override
    public void error(String s) {
        addToDiagnosticIfPresentAndLog(s, Level.ERROR, null, l -> l.error(s));
    }

    @Override
    public void error(String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.ERROR, null, l -> l.error(s, o));
    }

    @Override
    public void error(String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.ERROR, null, l -> l.error(s, o, o1));
    }

    @Override
    public void error(String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.ERROR, null, l -> l.error(s, objects));
    }

    @Override
    public void error(String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.ERROR, throwable, l -> l.error(s, throwable));
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return delegate.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String s) {
        addToDiagnosticIfPresentAndLog(s, Level.ERROR, null, l -> l.error(marker, s));
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        addToDiagnosticIfPresentAndLog(format(s, o), Level.ERROR, null, l -> l.error(marker, s, o));
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {
        addToDiagnosticIfPresentAndLog(format(s, o, o1), Level.ERROR, null, l -> l.error(marker, s, o, o1));
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        addToDiagnosticIfPresentAndLog(format(s, objects), Level.ERROR, null, l -> l.error(marker, s, objects));
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        addToDiagnosticIfPresentAndLog(s, Level.ERROR, throwable, l -> l.error(marker, s, throwable));
    }
}