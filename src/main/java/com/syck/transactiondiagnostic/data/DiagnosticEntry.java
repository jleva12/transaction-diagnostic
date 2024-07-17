package com.syck.transactiondiagnostic.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;


@RequiredArgsConstructor
@Data
public class DiagnosticEntry {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private final ZonedDateTime timeStamp = ZonedDateTime.now(ZoneOffset.UTC);
    private final DiagnosticEntryId entryId;
    private final Level level;
    private final MetaData metaData;
    private final String threadName;
    private final String entryName;
    private final String message;
    private final ExceptionDetail exceptionDetail;
}
