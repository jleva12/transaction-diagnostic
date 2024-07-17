package com.syck.transactiondiagnostic.service;

import com.syck.transactiondiagnostic.data.DiagnosticEntry;
import com.syck.transactiondiagnostic.data.DiagnosticRecord;
import com.syck.transactiondiagnostic.messages.MessageType;

public interface DiagnosticHandler {
    void handle(DiagnosticRecord diagnosticRecord);
}
