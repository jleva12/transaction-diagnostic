package com.syck.transactiondiagnostic.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.syck.transactiondiagnostic.data.AppName;
import com.syck.transactiondiagnostic.data.CorrelationId;

import java.io.IOException;

public class AppNameSerializer extends StdSerializer<AppName> {
    public AppNameSerializer() {
        this(null);
    }

    public AppNameSerializer(Class<AppName> t) {
        super(t);
    }

    @Override
    public void serialize(AppName appName, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(appName.getValue());
    }
}
