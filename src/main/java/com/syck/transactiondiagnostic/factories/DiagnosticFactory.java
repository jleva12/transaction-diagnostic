package com.syck.transactiondiagnostic.factories;

import com.syck.transactiondiagnostic.data.CorrelationId;
import com.syck.transactiondiagnostic.data.DiagnosticRecord;
import com.syck.transactiondiagnostic.data.MetaData;

public interface DiagnosticFactory {
    DiagnosticRecord create(CorrelationId correlationId, MetaData metaData);
    DiagnosticRecord create(CorrelationId correlationId);
    DiagnosticRecord create();
}
