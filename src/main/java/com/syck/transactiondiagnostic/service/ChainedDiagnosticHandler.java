package com.syck.transactiondiagnostic.service;

import com.syck.transactiondiagnostic.data.DiagnosticRecord;
import com.syck.transactiondiagnostic.publishers.DiagnosticPublisher;

import java.util.List;

public class ChainedDiagnosticHandler implements DiagnosticHandler{

    private final List<DiagnosticPublisher> publishers;


    public ChainedDiagnosticHandler(List<DiagnosticPublisher> publishers) {
        this.publishers = publishers;
    }

    @Override
    public void handle(DiagnosticRecord diagnosticRecord) {
        publishers.forEach(publisher -> publisher.publish(diagnosticRecord));
    }


}
