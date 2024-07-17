package com.syck.transactiondiagnostic.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.syck.transactiondiagnostic.factories.DiagnosticEntryIdGenerator;
import com.syck.transactiondiagnostic.factories.IdGenerator;
import lombok.*;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagnosticRecord {
    @JsonIgnore
    private final IdGenerator idGenerator;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private ZonedDateTime start = ZonedDateTime.now(ZoneOffset.UTC);
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private ZonedDateTime end;
    private long duration;
    private final AppName appName;
    private final DiagnosticId diagnosticId;
    private final CorrelationId correlationId;
    private Status status;
    private final MetaData metaData;
    private final List<DiagnosticEntry> entries = new ArrayList<>();
    private final List<LogEntry> logEntries = new ArrayList<>();

    public void end() {
        end =ZonedDateTime.now(ZoneOffset.UTC);
        duration = Duration.between(start, end).toMillis();
    }


    public void info(String entryName, String message, MetaData metaData) {
        addEntry(entryName, message, metaData, Level.INFO, null);
    }


    public void info(String entryName, String message) {
        addEntry(entryName, message, null, Level.INFO, null);
    }


    public void error(String entryName, String message, Throwable throwable) {
        addEntry(entryName, message, null, Level.ERROR, throwable);
    }


    public void error(String entryName, String message, MetaData metaData, Throwable throwable) {
        addEntry(entryName, message, metaData, Level.ERROR, throwable);
    }


    public void addLogEntry(LogEntry logEntry) {
        this.logEntries.add(logEntry);
    }


    private void addEntry(String entryName, String message, MetaData metaData, Level level, Throwable throwable) {
        if (Objects.equals(Level.ERROR, level)) {
            this.status = Status.ERROR;
        }
        final DiagnosticEntry entry = new DiagnosticEntry(
                DiagnosticEntryId.of(idGenerator.get()),
                level,
                metaData,
                Thread.currentThread().getName(),
                entryName,
                message,
                throwable != null ? new ExceptionDetail(throwable) : null
        );
        entries.add(entry);
    }


}
