package com.syck.transactiondiagnostic.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.syck.transactiondiagnostic.data.DiagnosticId;

import java.io.IOException;

public class DiagnosticIdDeserializer extends StdDeserializer<DiagnosticId> {

    public DiagnosticIdDeserializer() {
        this(null);
    }

    public DiagnosticIdDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public DiagnosticId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return DiagnosticId.of(value);
    }
}
