package com.syck.transactiondiagnostic.context;

/**
 * The DiagnosticContextHolder class provides access to the DiagnosticContext for the current thread.
 * It is used to store and retrieve diagnostic information associated with the thread.
 */
public class DiagnosticContextHolder {

    private static DiagnosticContext ctx;

    /**
     * Retrieves the DiagnosticContext for the current thread.
     *
     * @return The DiagnosticContext for the current thread.
     */
    public static DiagnosticContext ctx(){
        return ctx;
    }

    /**
     * Sets the DiagnosticContext for the current thread.
     *
     * @param ctx The DiagnosticContext to be set.
     */
    public static void setCtx(DiagnosticContext ctx) {
        DiagnosticContextHolder.ctx = ctx;
    }

}
