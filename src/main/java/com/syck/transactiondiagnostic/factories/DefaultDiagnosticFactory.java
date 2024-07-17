package com.syck.transactiondiagnostic.factories;

import com.syck.transactiondiagnostic.context.DiagnosticContext;
import com.syck.transactiondiagnostic.context.DiagnosticContextHolder;
import com.syck.transactiondiagnostic.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class DefaultDiagnosticFactory implements DiagnosticFactory {

    private final Logger logger = LoggerFactory.getLogger(DefaultDiagnosticFactory.class);
    private final AppName appName;
    private final IdGenerator diagnosticIdGenerator;
    private final DiagnosticContext context;
    private final IdGenerator idGenerator;

    /**
     * Creates a new DefaultDiagnosticFactory instance.
     *
     * @param appName                 The application name associated with the diagnostic record. Cannot be null.
     * @param diagnosticIdGenerator   The generator for generating unique diagnostic IDs. Cannot be null.
     * @param context                 The diagnostic context for the application. Cannot be null.
     * @param idGenerator             The generator for generating unique entry IDs. Cannot be null.
     */
    public DefaultDiagnosticFactory(String appName,
                                    IdGenerator diagnosticIdGenerator,
                                    DiagnosticContext context,
                                    IdGenerator idGenerator) {
        Objects.requireNonNull(appName, "appName cannot be null");
        Objects.requireNonNull(diagnosticIdGenerator, "diagnosticIdGenerator cannot be null");
        Objects.requireNonNull(context, "context cannot be null");
        Objects.requireNonNull(idGenerator, "entry idGenerator cannot be null");
        this.idGenerator = idGenerator;
        this.context = context;
        this.appName = AppName.of(appName);
        this.diagnosticIdGenerator = diagnosticIdGenerator;
        DiagnosticContextHolder.setCtx(context);
    }

    @Override
    public DiagnosticRecord create(CorrelationId correlationId, MetaData metaData) {
        logger.debug("Create diagnostic record for {} with metadata {}", correlationId, metaData);
        final DiagnosticRecord record = buildRecord(correlationId, metaData);
        context.register(record);
        return record;
    }

    @Override
    public DiagnosticRecord create(CorrelationId correlationId) {
        logger.debug("Create diagnostic record for {}", correlationId);
        final DiagnosticRecord record = buildRecord(correlationId, null);
        context.register(record);
        return record;
    }

    @Override
    public DiagnosticRecord create() {
        logger.debug("Create diagnostic record");
        final DiagnosticRecord record = buildRecord(null, null);
        context.register(record);
        return record;
    }


    private DiagnosticRecord buildRecord(CorrelationId correlationId, MetaData metaData) {
       return new DiagnosticRecord(
                idGenerator,
                appName,
                DiagnosticId.of(diagnosticIdGenerator.get()),
                correlationId,
                metaData);
    }
}
