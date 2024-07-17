package com.syck.transactiondiagnostic.publishers;

import com.syck.transactiondiagnostic.data.DiagnosticEntry;
import com.syck.transactiondiagnostic.data.DiagnosticRecord;
import com.syck.transactiondiagnostic.messages.DiagnosticMessage;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class KafkaDiagnosticMessagePublisher implements DiagnosticPublisher {

    private final Logger logger = LoggerFactory.getLogger(KafkaDiagnosticMessagePublisher.class);

    private final KafkaTemplate<String, DiagnosticRecord> kafkaTemplate;
    private final String topic;
    private final PublisherFailover failover;

    /**
     * KafkaDiagnosticMessagePublisher is a class responsible for publishing DiagnosticMessages to a Kafka topic.
     *
     * @param kafkaTemplate The KafkaTemplate used for sending messages.
     *                      It should be instantiated with appropriate key and value serializers.
     * @param topic         The name of the topic where messages will be published to.
     *                      The topic should already exist in the Kafka cluster.
     */
    public KafkaDiagnosticMessagePublisher(KafkaTemplate<String, DiagnosticRecord> kafkaTemplate,
                                           String topic, PublisherFailover failover) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.failover = failover;
    }


    /**
     * Publishes a DiagnosticMessage to a Kafka topic.
     *
     * @param message The DiagnosticMessage to be published.
     *                It contains the type of the message and the payload.
     * @throws KafkaException         If a Kafka error occurs while sending the message.
     * @throws SerializationException If a serialization error occurs while sending the message.
     */
    @Override
    public void publish(DiagnosticRecord message) {
        try {
            final CompletableFuture<SendResult<String, DiagnosticRecord>> future = kafkaTemplate.send(topic, message);
            future.whenComplete(new FallbackCallback(message));
        } catch (KafkaException e) {
            logger.error("Kafka error occurred while sending message", e);
        } catch (SerializationException e) {
            logger.error("Serialization error occurred while sending message", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while sending message", e);
        }
    }




    /**
     * FallbackCallback is a private class that implements the BiConsumer interface.
     * It is used as a callback function when completing a CompletableFuture in the KafkaDiagnosticMessagePublisher class.
     */
    private class FallbackCallback implements BiConsumer<SendResult<String, DiagnosticRecord>, Throwable> {

        private DiagnosticRecord record;


        FallbackCallback(final DiagnosticRecord record) {
            this.record = record;
        }

        @Override
        public void accept(SendResult<String, DiagnosticRecord> result, Throwable throwable) {
            if (throwable != null) {
                failover.failover(record, throwable);
            }
        }


    }

}
