package com.syck.transactiondiagnostic.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.syck.transactiondiagnostic.deserializers.AppNameDeserializer;
import com.syck.transactiondiagnostic.serializers.AppNameSerializer;

@JsonDeserialize(using = AppNameDeserializer.class)
@JsonSerialize(using = AppNameSerializer.class)
public class AppName extends ValueObject<String>{

    private AppName(String value) {
        super(value);
    }

    public static AppName of(String value) {
        return new AppName(value);
    }
}
