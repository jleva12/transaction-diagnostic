package com.syck.transactiondiagnostic.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.syck.transactiondiagnostic.data.DiagnosticEntryId;
import com.syck.transactiondiagnostic.data.DiagnosticId;

import java.io.IOException;

public class DiagnosticEntryIdDeserializer extends StdDeserializer<DiagnosticEntryId> {

    public DiagnosticEntryIdDeserializer() {
        this(null);
    }

    public DiagnosticEntryIdDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public DiagnosticEntryId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return DiagnosticEntryId.of(value);
    }
}
