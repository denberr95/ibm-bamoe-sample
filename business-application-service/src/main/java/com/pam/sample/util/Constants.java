package com.pam.sample.util;

public class Constants {
    
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SPAN_KEY_CORRELATION_KEY = "correlation.key";
    public static final String SPAN_KEY_PROCESS_INSTANCE_ID = "process.instance.id";
    public static final String SPAN_KEY_PROCESS_NAME = "process.name";
    public static final String SPAN_KEY_SIGNAL_NAME = "signal.name";
    public static final String SPAN_KEY_CLASS_NAME = "class.name";
    public static final char C_DOT = '.';
}
