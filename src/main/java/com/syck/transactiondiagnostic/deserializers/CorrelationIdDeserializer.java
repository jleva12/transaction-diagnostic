package com.syck.transactiondiagnostic.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.syck.transactiondiagnostic.data.CorrelationId;

import java.io.IOException;

public class CorrelationIdDeserializer extends StdDeserializer<CorrelationId> {

    public CorrelationIdDeserializer() {
        this(null);
    }

    public CorrelationIdDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CorrelationId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return CorrelationId.of(value);
    }
}
