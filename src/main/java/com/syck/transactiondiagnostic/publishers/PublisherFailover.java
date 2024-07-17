package com.syck.transactiondiagnostic.publishers;

import com.syck.transactiondiagnostic.data.DiagnosticRecord;
import com.syck.transactiondiagnostic.messages.DiagnosticMessage;

public interface PublisherFailover {
    /**
     * Performs failover for the given diagnostic message.
     *
     * @param message the diagnostic message to perform failover for
     */
    void failover(DiagnosticRecord record, Throwable throwable);

    /**
     * LogPublisherFailover is a class that implements the PublisherFailover interface. It provides a method
     * to perform failover for a given diagnostic message.
     */
    class LogPublisherFailover implements PublisherFailover {


        @Override
        public void failover(DiagnosticRecord record, Throwable throwable) {
            //todo: write logger for messages;
        }

    }
}
