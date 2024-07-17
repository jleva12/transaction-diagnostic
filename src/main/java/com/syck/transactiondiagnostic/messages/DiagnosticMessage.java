package com.syck.transactiondiagnostic.messages;


public record DiagnosticMessage<T>(MessageType type, T payload) { }
