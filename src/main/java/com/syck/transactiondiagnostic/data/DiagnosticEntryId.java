package com.syck.transactiondiagnostic.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.syck.transactiondiagnostic.deserializers.DiagnosticEntryIdDeserializer;
import com.syck.transactiondiagnostic.serializers.DiagnosticEntryIdSerializer;

@JsonSerialize(using = DiagnosticEntryIdSerializer.class)
@JsonDeserialize(using = DiagnosticEntryIdDeserializer.class)
public class DiagnosticEntryId extends ValueObject<String>{

    private DiagnosticEntryId(String value) {
        super(value);
    }

    public static DiagnosticEntryId of(String value) {
        return new DiagnosticEntryId(value);
    }
}
