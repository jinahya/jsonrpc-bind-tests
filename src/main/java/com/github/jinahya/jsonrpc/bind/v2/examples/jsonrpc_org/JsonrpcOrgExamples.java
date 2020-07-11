package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org;

/*-
 * #%L
 * jsonrpc-bind-jackson
 * %%
 * Copyright (C) 2019 - 2020 Jinahya, Inc.
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

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

@Slf4j
public final class JsonrpcOrgExamples {

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> R applyRequestResourceNames(final Function<? super Stream<String>, ? extends R> function)
            throws IOException {
        requireNonNull(function, "function is null");
        try (InputStream stream = JsonrpcOrgExamples.class.getResourceAsStream("requests.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
             Stream<String> lines = reader.lines()) {
            return function.apply(lines);
        }
    }

    public static void forEachRequestResource(final BiConsumer<? super String, ? super InputStream> consumer)
            throws IOException {
        requireNonNull(consumer, "consumer is null");
        applyRequestResourceNames(s -> {
            s.forEach(n -> {
                try {
                    try (InputStream i = JsonrpcOrgExamples.class.getResourceAsStream(n)) {
                        consumer.accept(n, i);
                    }
                } catch (final IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            });
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <R> R applyResponseResourceNames(final Function<? super Stream<String>, ? extends R> function)
            throws IOException {
        requireNonNull(function, "function is null");
        try (InputStream stream = JsonrpcOrgExamples.class.getResourceAsStream("responses.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
             Stream<String> lines = reader.lines()) {
            return function.apply(lines);
        }
    }

    public static void forEachResponseResource(final BiConsumer<? super String, ? super InputStream> consumer)
            throws IOException {
        requireNonNull(consumer, "consumer is null");
        applyResponseResourceNames(s -> {
            s.forEach(n -> {
                try {
                    try (InputStream i = JsonrpcOrgExamples.class.getResourceAsStream(n)) {
                        consumer.accept(n, i);
                    }
                } catch (final IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            });
            return null;
        });
    }
}
