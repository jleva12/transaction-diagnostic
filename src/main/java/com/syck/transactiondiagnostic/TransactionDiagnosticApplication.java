package com.syck.transactiondiagnostic;

import com.syck.transactiondiagnostic.context.DiagnosticContextHolder;
import com.syck.transactiondiagnostic.data.CorrelationId;
import com.syck.transactiondiagnostic.data.DiagnosticRecord;
import com.syck.transactiondiagnostic.data.MetaData;
import com.syck.transactiondiagnostic.factories.DiagnosticFactory;
import com.syck.transactiondiagnostic.proxy.DiagnosticLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

import static com.syck.transactiondiagnostic.context.DiagnosticContext.runWithDiagnostic;

@SpringBootApplication
public class TransactionDiagnosticApplication implements CommandLineRunner {

    final Logger logger = DiagnosticLoggerFactory.getLogger(TransactionDiagnosticApplication.class);

    public TransactionDiagnosticApplication(DiagnosticFactory diagnosticFactory, JdbcTemplate jdbcTemplate) {
        this.diagnosticFactory = diagnosticFactory;
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(TransactionDiagnosticApplication.class, args);
    }


    private final DiagnosticFactory diagnosticFactory;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Now it should use your custom factory
        System.out.println("SLF4J Logger Factory: " + LoggerFactory.getILoggerFactory().getClass().getName());
        DiagnosticRecord record = diagnosticFactory.create(CorrelationId.of("1234"));

        String s = runWithDiagnostic(
                () -> {
                    logger.warn("testing logger");
                    new SomeBusinessStuff().handle();
//                    throw  new NewException("wrapped", new IllegalStateException("something went wrong!!"));

                    String version = jdbcTemplate.queryForObject("SELECT VERSION()", String.class);

                    return "hello";
                }
        );

        System.out.println("result " + s);


    }

    static class NewException extends RuntimeException {
        public NewException(String message) {
            super(message);
        }

        public NewException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    static class SomeBusinessStuff {
        final Logger logger = DiagnosticLoggerFactory.getLogger(SomeBusinessStuff.class);

        public void handle() {
            logger.info("SomeBusinessStuff logger");
            DiagnosticContextHolder
                    .ctx().addEntry(d -> d.info("example_entry", "doingsomeWork",
                    MetaData.of("myKey", 10098)));

            String h = "hello world";

            logger.info("another log message {}", h);

            try {
                Thread.sleep(1000L);
            }catch (Exception e) {}
        }
    }
}
