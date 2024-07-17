package com.syck.transactiondiagnostic.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.syck.transactiondiagnostic.data.DiagnosticEntryId;
import com.syck.transactiondiagnostic.data.DiagnosticId;

import java.io.IOException;

public class DiagnosticEntryIdSerializer extends StdSerializer<DiagnosticEntryId> {

    public DiagnosticEntryIdSerializer() {
        this(null);
    }

    public DiagnosticEntryIdSerializer(Class<DiagnosticEntryId> t) {
        super(t);
    }

    @Override
    public void serialize(DiagnosticEntryId diagnosticId, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(diagnosticId.getValue());
    }
}
