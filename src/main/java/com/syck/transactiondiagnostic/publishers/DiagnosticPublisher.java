package com.syck.transactiondiagnostic.publishers;

import com.syck.transactiondiagnostic.data.DiagnosticEntry;
import com.syck.transactiondiagnostic.data.DiagnosticRecord;
import com.syck.transactiondiagnostic.messages.DiagnosticMessage;

public interface DiagnosticPublisher {
    void publish(DiagnosticRecord record);
}
