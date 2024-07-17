package com.syck.transactiondiagnostic.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.syck.transactiondiagnostic.data.DiagnosticId;

import java.io.IOException;

public class DiagnosticIdSerializer extends StdSerializer<DiagnosticId> {

    public DiagnosticIdSerializer() {
        this(null);
    }

    public DiagnosticIdSerializer(Class<DiagnosticId> t) {
        super(t);
    }

    @Override
    public void serialize(DiagnosticId diagnosticId, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(diagnosticId.getValue());
    }
}
