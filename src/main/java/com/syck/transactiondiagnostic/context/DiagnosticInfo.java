package com.syck.transactiondiagnostic.context;

import com.syck.transactiondiagnostic.data.CorrelationId;
import com.syck.transactiondiagnostic.data.DiagnosticId;

public record DiagnosticInfo(
        DiagnosticId diagnosticId,
        CorrelationId correlationId
) {
}
