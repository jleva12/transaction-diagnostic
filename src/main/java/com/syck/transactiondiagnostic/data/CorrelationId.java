package com.syck.transactiondiagnostic.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.syck.transactiondiagnostic.deserializers.CorrelationIdDeserializer;
import com.syck.transactiondiagnostic.serializers.CorrelationIdSerializer;

@JsonSerialize(using = CorrelationIdSerializer.class)
@JsonDeserialize(using = CorrelationIdDeserializer.class)
public class CorrelationId extends ValueObject<String>{

    protected CorrelationId(String value) {
        super(value);
    }

    public static CorrelationId of(String id) {
        return new CorrelationId(id);
    }
}
