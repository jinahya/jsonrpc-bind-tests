package com.github.jinahya.jsonrpc.bind.v2.examples;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public abstract class ExampleResourceTest {

    protected <R> R applyResourceStream(final String name, final Function<? super InputStream, ? extends R> function)
            throws IOException {
        requireNonNull(name, "name is null");
        requireNonNull(function, "function is null");
        return ExampleResourceTests.applyResourceStream(getClass(), name, function);
    }

    protected void acceptResourceStream(final String name, final Consumer<? super InputStream> consumer)
            throws IOException {
        requireNonNull(name, "name is null");
        requireNonNull(consumer, "consumer is null");
        ExampleResourceTests.applyResourceStream(getClass(), name, s -> {
            consumer.accept(s);
            return null;
        });
    }
}
