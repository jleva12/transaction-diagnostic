package com.syck.transactiondiagnostic.proxy;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagnosticLoggerFactory implements ILoggerFactory {
    private static final ILoggerFactory defaultFactory = LoggerFactory.getILoggerFactory();

    @Override
    public Logger getLogger(String name) {
        return new LoggerAdapter(defaultFactory.getLogger(name));
    }

    // Static methods for convenience
    public static Logger getLogger(Class<?> clazz) {
        return new LoggerAdapter(defaultFactory.getLogger(clazz.getName()));
    }

}
