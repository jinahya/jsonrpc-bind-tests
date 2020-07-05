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

import com.github.jinahya.jsonrpc.bind.JsonrpcBindException;
import com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage;
import com.github.jinahya.jsonrpc.bind.v2.examples.ExampleResourceRequestTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static com.github.jinahya.jsonrpc.bind.BeanValidationTests.requireValid;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage.fromJson;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage.newInstance;
import static com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.JsonrpcOrgExamples.forEachRequestResource;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public abstract class JsonrpcOrgRequestTest
        extends ExampleResourceRequestTest {

    @Test
    public void testForEachRequestResource() throws IOException {
        forEachRequestResource(s -> {
            final JsonrpcRequestMessage message = fromJson(s);
            requireValid(message);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void read_e01_positional_parameters_01_request() throws IOException {
        acceptResourceStream(
                "e01_positional_parameters_01_request.json",
                s -> {
                    final JsonrpcRequestMessage message = fromJson(s);
                    requireValid(message);
                    {
                        assertEquals("subtract", message.getMethod());
                    }
                    {
                        assertTrue(message.hasParams());
                        final List<Integer> params = message.getParamsAsArray(Integer.class);
                        assertIterableEquals(asList(42, 23), params);
                        {
                            final Integer[] array = message.getParamsAsObject(Integer[].class);
                            assertThat(array).isNotNull().containsSequence(42, 23);
                        }
                        {
                            final int[] array = message.getParamsAsObject(int[].class);
                            assertThat(array).isNotNull().containsSequence(42, 23);
                        }
                    }
                    {
                        assertTrue(message.hasId());
                        assertEquals("1", message.getIdAsString());
                        assertThat(message.getIdAsNumber()).isNotNull()
                                .isEqualByComparingTo(BigInteger.valueOf(1L));
                        assertThat(message.getIdAsLong()).isNotNull().isEqualTo(1L);
                        assertThat(message.getIdAsInteger()).isNotNull().isEqualTo(1);
                    }
                }
        );
    }

    @Test
    public void write_e01_positional_parameters_01_request() {
        final JsonrpcRequestMessage message = newInstance();
        message.setMethod("subtract");
        message.setParamsAsArray(asList(42, 23));
        message.setIdAsInteger(1);
        requireValid(message);
        final String json = message.toJson();
        log.debug("json: {}", json);
        assertNotNull(json);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void read_e01_positional_parameters_02_request() throws IOException {
        acceptResourceStream(
                "e01_positional_parameters_02_request.json",
                s -> {
                    final JsonrpcRequestMessage message = fromJson(s);
                    requireValid(message);
                    {
                        assertEquals("subtract", message.getMethod());
                    }
                    {
                        assertTrue(message.hasParams());
                        final List<Integer> params = message.getParamsAsArray(Integer.class);
                        assertIterableEquals(asList(23, 42), params);
                        {
                            final Integer[] array = message.getParamsAsObject(Integer[].class);
                            assertThat(array).isNotNull().containsSequence(23, 42);
                        }
                        {
                            final int[] array = message.getParamsAsObject(int[].class);
                            assertThat(array).isNotNull().containsSequence(23, 42);
                        }
                    }
                    {
                        assertTrue(message.hasId());
                        assertEquals("2", message.getIdAsString());
                        assertThat(message.getIdAsNumber()).isNotNull()
                                .isEqualByComparingTo(BigInteger.valueOf(2L));
                        assertThat(message.getIdAsLong()).isNotNull().isEqualTo(2L);
                        assertThat(message.getIdAsInteger()).isNotNull().isEqualTo(2);
                        assertFalse(message.isNotification());
                    }
                }
        );
    }

    @Test
    public void write_e01_positional_parameters_02_request() {
        final JsonrpcRequestMessage message = newInstance();
        message.setMethod("subtract");
        message.setParamsAsObject(new int[] {23, 42});
        message.setIdAsInteger(2);
        requireValid(message);
        final String json = message.toJson();
        log.debug("json: {}", json);
        assertNotNull(json);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void read_e02_named_parameters_01_request() throws IOException {
        acceptResourceStream(
                "e02_named_parameters_01_request.json",
                s -> {
                    final JsonrpcRequestMessage message = fromJson(s);
                    log.debug("message: {}", message);
                    requireValid(message);
                    {
                        assertEquals("subtract", message.getMethod());
                    }
                    {
                        assertTrue(message.hasParams());
                        final NamedParams params = message.getParamsAsObject(NamedParams.class);
                        assertEquals(23, params.subtrahend);
                        assertEquals(42, params.minuend);
                        {
                            final List<NamedParams> array = message.getParamsAsArray(NamedParams.class);
                            assertThat(array)
                                    .isNotNull()
                                    .hasSize(1)
                                    .allSatisfy(e -> {
                                        assertEquals(23, e.subtrahend);
                                        assertEquals(42, e.minuend);
                                    });
                        }
                    }
                    {
                        assertTrue(message.hasId());
                        assertEquals("3", message.getIdAsString());
                        assertThat(message.getIdAsNumber()).isNotNull()
                                .isEqualByComparingTo(BigInteger.valueOf(3L));
                        assertThat(message.getIdAsLong()).isNotNull().isEqualTo(3L);
                        assertThat(message.getIdAsInteger()).isNotNull().isEqualTo(3);
                    }
                }
        );
    }

    @Test
    public void write_e02_named_parameters_01_request() {
        final JsonrpcRequestMessage message = newInstance();
        message.setMethod("subtract");
        message.setParamsAsObject(NamedParams.builder().subtrahend(23).minuend(42).build());
        message.setIdAsInteger(3);
        requireValid(message);
        final String json = message.toJson();
        log.debug("json: {}", json);
        assertNotNull(json);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void read_e02_named_parameters_02_request() throws IOException {
        acceptResourceStream(
                "e02_named_parameters_02_request.json",
                s -> {
                    final JsonrpcRequestMessage message = fromJson(s);
                    log.debug("message: {}", message);
                    requireValid(message);
                    {
                        assertEquals("subtract", message.getMethod());
                    }
                    {
                        assertTrue(message.hasParams());
                        final NamedParams params = message.getParamsAsObject(NamedParams.class);
                        assertEquals(42, params.minuend);
                        assertEquals(23, params.subtrahend);
                        {
                            final List<NamedParams> array = message.getParamsAsArray(NamedParams.class);
                            assertThat(array)
                                    .isNotNull()
                                    .hasSize(1)
                                    .allSatisfy(e -> {
                                        assertEquals(42, e.minuend);
                                        assertEquals(23, e.subtrahend);
                                    });
                        }
                    }
                    {
                        assertTrue(message.hasId());
                        assertEquals("4", message.getIdAsString());
                        assertThat(message.getIdAsNumber()).isNotNull()
                                .isEqualByComparingTo(BigInteger.valueOf(4L));
                        assertThat(message.getIdAsLong()).isNotNull().isEqualTo(4L);
                        assertThat(message.getIdAsInteger()).isNotNull().isEqualTo(4);
                    }
                }
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void read_e03_notification_01_request() throws IOException {
        acceptResourceStream(
                "e03_notification_01_request.json",
                s -> {
                    final JsonrpcRequestMessage message = fromJson(s);
                    requireValid(message);
                    {
                        assertEquals("update", message.getMethod());
                    }
                    {
                        assertTrue(message.hasParams());
                        final List<Integer> params = message.getParamsAsArray(int.class);
                        assertThat(params).isNotNull().containsExactly(1, 2, 3, 4, 5);
                    }
                    {
                        assertTrue(message.hasParams());
                        final List<Integer> params = message.getParamsAsArray(Integer.class);
                        assertThat(params).isNotNull().containsExactly(1, 2, 3, 4, 5);
                    }
                    {
                        assertThrows(JsonrpcBindException.class, () -> message.getParamsAsObject(int.class));
                        final int[] params = message.getParamsAsObject(int[].class);
                        assertThat(params).isNotNull().containsSequence(1, 2, 3, 4, 5);
                    }
                    {
                        assertThrows(JsonrpcBindException.class, () -> message.getParamsAsObject(Integer.class));
                        final Integer[] array = message.getParamsAsObject(Integer[].class);
                        assertThat(array).isNotNull().containsExactly(1, 2, 3, 4, 5);
                    }
                    {
                        assertFalse(message.hasId());
                        assertTrue(message.isNotification());
                    }
                }
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void read_e03_notification_02_request() throws IOException {
        acceptResourceStream(
                "e03_notification_02_request.json",
                s -> {
                    final JsonrpcRequestMessage message = fromJson(s);
                    requireValid(message);
                    {
                        assertEquals("foobar", message.getMethod());
                    }
                    {
                        assertFalse(message.hasParams());
                    }
                    {
                        assertFalse(message.hasId());
                        assertTrue(message.isNotification());
                    }
                }
        );
    }
}
