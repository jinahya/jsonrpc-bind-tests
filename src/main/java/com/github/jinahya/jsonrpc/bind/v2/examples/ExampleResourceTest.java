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
