package com.syck.transactiondiagnostic.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.syck.transactiondiagnostic.data.AppName;
import com.syck.transactiondiagnostic.data.CorrelationId;

import java.io.IOException;

public class AppNameDeserializer extends StdDeserializer<AppName> {
    public AppNameDeserializer() {
        this(null);
    }

    public AppNameDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AppName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return AppName.of(value);
    }
}
