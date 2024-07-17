package com.syck.transactiondiagnostic.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.syck.transactiondiagnostic.deserializers.DiagnosticIdDeserializer;
import com.syck.transactiondiagnostic.serializers.DiagnosticIdSerializer;

@JsonSerialize(using = DiagnosticIdSerializer.class)
@JsonDeserialize(using = DiagnosticIdDeserializer.class)
public class DiagnosticId extends ValueObject<String> {

    private DiagnosticId(String value) {
        super(value);
    }

    public static DiagnosticId of(String value) {
        return new DiagnosticId(value);
    }

}
