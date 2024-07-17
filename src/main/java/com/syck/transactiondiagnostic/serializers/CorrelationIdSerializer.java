package com.syck.transactiondiagnostic.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.syck.transactiondiagnostic.data.CorrelationId;
import com.syck.transactiondiagnostic.data.DiagnosticId;

import java.io.IOException;

public class CorrelationIdSerializer extends StdSerializer<CorrelationId> {

    public CorrelationIdSerializer() {
        this(null);
    }

    public CorrelationIdSerializer(Class<CorrelationId> t) {
        super(t);
    }

    @Override
    public void serialize(CorrelationId correlationId, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(correlationId.getValue());
    }
}
