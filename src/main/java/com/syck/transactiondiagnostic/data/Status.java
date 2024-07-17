package com.syck.transactiondiagnostic.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    OK(1), WARNING(2), ERROR(3);

    private final int order;
}
