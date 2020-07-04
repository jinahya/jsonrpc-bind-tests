package com.github.jinahya.jsonrpc.bind.v2.examples;

/*-
 * #%L
 * jsonrpc-bind-tests
 * %%
 * Copyright (C) 2020 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class ExampleResourceTests {

    /**
     * Applies a stream of specified resource to specified function and returns the result.
     *
     * @param name     the resource name to open.
     * @param function the function to be applied with the resource stream.
     * @param <R>      result type parameter.
     * @return the result of the function
     * @throws IOException if an I/O error occurs.
     */
    public static <R> R applyResourceStream(final Class<?> clazz, final String name,
                                            final Function<? super InputStream, ? extends R> function)
            throws IOException {
        requireNonNull(clazz, "clazz is null");
        requireNonNull(name, "name is null");
        requireNonNull(function, "function is null");
        try (InputStream resource = clazz.getResourceAsStream(name)) {
            assertNotNull(resource, "null resource stream loaded from '" + name + "'");
            return function.apply(resource);
        }
    }

    public static void acceptResourceStream(final Class<?> clazz, final String name,
                                            final Consumer<? super InputStream> consumer)
            throws IOException {
        requireNonNull(clazz, "clazz is null");
        requireNonNull(name, "name is null");
        requireNonNull(consumer, "consumer is null");
        applyResourceStream(clazz, name, s -> {
            consumer.accept(s);
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ExampleResourceTests() {
        throw new AssertionError("instantiation is not allowed");
    }
}
