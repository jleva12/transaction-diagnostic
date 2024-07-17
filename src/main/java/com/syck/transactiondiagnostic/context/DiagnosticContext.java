package com.syck.transactiondiagnostic.context;

import com.syck.transactiondiagnostic.data.*;
import com.syck.transactiondiagnostic.service.DiagnosticHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DiagnosticContext {
    private final static Logger logger = LoggerFactory.getLogger(DiagnosticContext.class);

    private final ThreadLocal<DiagnosticRecord> threadContext = new ThreadLocal<>();
    private final DiagnosticHandler diagnosticHandler;

    /**
     * Represents a diagnostic context that allows for setting and retrieving diagnostic information
     * associated with the current thread.
     *
     * @param diagnosticHandler     The handler for processing diagnostic entries.
     */
    public DiagnosticContext(DiagnosticHandler diagnosticHandler) {
        Objects.requireNonNull(diagnosticHandler, "Diagnostic handler cannot be null");
        this.diagnosticHandler = diagnosticHandler;
    }

    public void register(DiagnosticRecord record) {
        threadContext.set(record);
    }


    public Optional<DiagnosticRecord> get() {
        return Optional.of(threadContext.get());
    }

    /**
     * Executes a supplier function with added diagnostic functionality.
     * Any unhandled exception thrown by the supplier will be caught and logged in the diagnostic context.
     * The diagnostic context will then be unregistered.
     *
     * @param supplier The supplier function to be executed.
     * @param <T> The type of the value returned by the supplier.
     * @return The value returned by the supplier.
     * @throws Exception Any exception thrown by the supplier.
     */
    public static <T> T runWithDiagnostic(Supplier<T> supplier) {
        try {
            logger.debug("running with diagnostic");
            return supplier.get();
        } catch (Throwable e) {
            DiagnosticContextHolder.ctx()
                    .addEntry(d -> d.error(
                            "unhandled_error",
                            "unhandled error caught in diagnostic context",
                            e));
            throw e;
        } finally {
            DiagnosticContextHolder.ctx().unRegister();
        }
    }

    /**
     * Executes the given VoidRunner with diagnostic functionality.
     *
     * @param runner The VoidRunner to be executed.
     * @throws Exception Any exception thrown by the runner.
     */
    public static void runWithDiagnostic(VoidRunner runner) {
        try {
            logger.debug("running with diagnostic void");
            runner.execute();
        } catch (Throwable e) {
            DiagnosticContextHolder.ctx()
                    .addEntry(d -> d.error(
                            "unhandled_error",
                            "unhandled error caught in diagnostic context",
                            e));
            throw e;
        } finally {
            DiagnosticContextHolder.ctx().unRegister();
        }
    }

    /**
     * Unregisters the DiagnosticContext by closing the current diagnostic record, handling it, and removing it from the thread context.
     * If there is no current diagnostic record, this method does nothing.
     * After calling this method, the diagnostic context for the current thread will be empty.
     */
    public void unRegister() {
        logger.debug("Unregistering DiagnosticContext");
        final Optional<DiagnosticRecord> diagnosticRecord = get();
        diagnosticRecord.map(closeDiagnostic());
    }

    /**
     * Closes the diagnostic record, handles it, and removes it from the thread context.
     *
     * @return A function that takes a diagnostic record as input and returns a boolean value.
     */
    private Function<DiagnosticRecord, Boolean> closeDiagnostic() {
        return d -> {
            d.end();
            diagnosticHandler.handle(d);
            threadContext.remove();
            return true;
        };
    }

    /**
     * Adds a new entry to the diagnostic record.
     *
     * @param consumer The consumer that accepts the diagnostic record.
     *                It will be invoked if the optional diagnostic record is present.
     *                The consumer can perform operations on the diagnostic record.
     *                It is recommended to use method references or lambda expressions when defining the consumer.
     */
    public void addEntry(Consumer<DiagnosticRecord> consumer) {
        get().ifPresent(r -> {
            logger.debug("adding entry to diagnostic with id [{}]", r.getDiagnosticId().getValue());
            consumer.accept(r);
        });
    }


    public interface VoidRunner {
        void execute();
    }

}
