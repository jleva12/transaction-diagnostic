package com.syck.transactiondiagnostic.configuration;


import com.syck.transactiondiagnostic.context.DiagnosticContext;
import com.syck.transactiondiagnostic.data.DiagnosticRecord;
import com.syck.transactiondiagnostic.factories.DefaultDiagnosticFactory;
import com.syck.transactiondiagnostic.factories.DiagnosticFactory;
import com.syck.transactiondiagnostic.factories.IdGenerator;
import com.syck.transactiondiagnostic.publishers.DiagnosticPublisher;
import com.syck.transactiondiagnostic.publishers.KafkaDiagnosticMessagePublisher;
import com.syck.transactiondiagnostic.publishers.PublisherFailover;
import com.syck.transactiondiagnostic.service.ChainedDiagnosticHandler;
import com.syck.transactiondiagnostic.service.DiagnosticHandler;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.spi.LoggerContextFactory;
//import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@Configuration
public class DiagnosticConfiguration {



    @Bean
    public IdGenerator diagnosticIdGenerator() {
        return new IdGenerator("diag");
    }

    @Bean
    public IdGenerator diagnosticEntryIdGenerator() {
        return new IdGenerator("entry");
    }

    @Bean
    public DiagnosticPublisher diagnosticPublisher(KafkaTemplate<String, DiagnosticRecord> kafkaTemplate,
                                                   @Value("${diagnostic.publisher.kafka.topic}") String topic) {
        return new KafkaDiagnosticMessagePublisher(kafkaTemplate, topic, new PublisherFailover.LogPublisherFailover());
    }

    @Bean
    public DiagnosticHandler diagnosticHandler(List<DiagnosticPublisher> publishers) {
        return new ChainedDiagnosticHandler(publishers);
    }

    @Bean
    public DiagnosticContext diagnosticContext(DiagnosticHandler diagnosticHandler) {
        return new DiagnosticContext(diagnosticHandler);
    }

    @Bean
    public DiagnosticFactory diagnosticFactory(@Value("${diagnostic.appName:default_app}") String appName,
                                               DiagnosticContext diagnosticContext
                                               ) {
        return new DefaultDiagnosticFactory(appName, diagnosticIdGenerator(), diagnosticContext, diagnosticEntryIdGenerator());
    }
}
