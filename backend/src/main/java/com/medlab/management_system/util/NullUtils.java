package com.medlab.management_system.util;

import java.util.function.Consumer;

public final class NullUtils {
    private NullUtils() {}

    public static <T> void updateIfPresent(Consumer<T> setter, T newValue) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }
}
