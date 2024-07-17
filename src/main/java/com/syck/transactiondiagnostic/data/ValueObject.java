package com.syck.transactiondiagnostic.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
public abstract class ValueObject<T> {
    @Getter
    private final T value;

    protected ValueObject(T value) {
        this.value = value;
    }

}
