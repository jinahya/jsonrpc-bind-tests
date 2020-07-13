package com.github.jinahya.jsonrpc.bind.v2.examples.random_org.v2.basic;

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

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcMessage;
import com.github.jinahya.jsonrpc.bind.v2.JsonrpcResponseMessage;
import com.github.jinahya.jsonrpc.bind.v2.examples.ExampleResourceResponseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static com.github.jinahya.jsonrpc.bind.BeanValidationTests.requireValid;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public abstract class RandomOrgV2BasicResponseTest
        extends ExampleResourceResponseTest {

    // -----------------------------------------------------------------------------------------------------------------
    private void verify_generateInteger_01_response(final JsonrpcResponseMessage message) {
        assert message != null;
        log.debug("message: {}", message);
        log.debug("json: {}", message.toJson());
        requireValid(message);
        {
            assertTrue(message.hasResult());
            final GenerateIntegersResult result = message.getResultAsObject(GenerateIntegersResult.class);
            assertThat(result.getRandom())
                    .isNotNull()
                    .satisfies(v -> {
                        assertThat(v.getData())
                                .isNotNull()
                                .containsExactly(1, 5, 4, 6, 6, 4);
                        assertThat(v.getCompletionTime())
                                .isNotNull()
                                .isEqualTo("2011-10-10 13:19:12Z");
                    });
            assertEquals(16, result.getBitsUsed());
            assertEquals(199984, result.getBitsLeft());
            assertEquals(9999, result.getRequestsLeft());
            assertEquals(0, result.getAdvisoryDelay());
        }
        {
            assertFalse(message.hasError());
        }
        {
            assertTrue(message.hasId());
            assertEquals("42", message.getIdAsString());
            assertThat(message.getIdAsNumber()).isNotNull()
                    .isEqualByComparingTo(BigInteger.valueOf(42L));
            assertThat(message.getIdAsLong()).isNotNull().isEqualTo(42L);
            assertThat(message.getIdAsInteger()).isNotNull().isEqualTo(42);
        }
    }

    @Test
    public void read_generateInteger_01_response() throws IOException {
        acceptResourceStream(
                "generateIntegers_01_response.json",
                s -> {
                    final JsonrpcResponseMessage message = JsonrpcResponseMessage.fromJson(s);
                    verify_generateInteger_01_response(message);
                }
        );
    }

    @Test
    public void write_generateInteger_01_response() {
        final JsonrpcResponseMessage message = JsonrpcResponseMessage.newInstance();
        assertEquals(JsonrpcMessage.PROPERTY_VALUE_JSONRPC, message.getJsonrpc());
        message.setResultAsObject(
                GenerateIntegersResult.builder()
                        .random(GenerateIntegersResult.Random.builder()
                                        .data(asList(1, 5, 4, 6, 6, 4))
                                        .completionTime("2011-10-10 13:19:12Z")
                                        .build()
                        )
                        .bitsUsed(16)
                        .bitsLeft(199984)
                        .requestsLeft(9999)
                        .advisoryDelay(0)
                        .build()
        );
        message.setIdAsInteger(42);
        verify_generateInteger_01_response(message);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private void verify_generateInteger_02_response(final JsonrpcResponseMessage message) {
        assert message != null;
        log.debug("message: {}", message);
        log.debug("json: {}", message.toJson());
        requireValid(message);
        {
            assertTrue(message.hasResult());
            final GenerateIntegersResult result = message.getResultAsObject(GenerateIntegersResult.class);
            assertThat(result.getRandom())
                    .isNotNull()
                    .satisfies(v -> {
                        assertThat(v.getData())
                                .isNotNull()
                                .containsExactly(39, 24, 18, 46, 6, 52, 36, 30, 40, 42, 37, 4, 7, 20, 1, 44, 25, 9, 21,
                                                 29, 51, 41, 14, 15, 48, 50, 31, 17, 3, 19, 45, 35, 2, 43, 26, 16, 5,
                                                 23, 12, 8, 10, 47, 13, 33, 34, 49, 22, 11, 28, 27, 38, 32);
                        assertThat(v.getCompletionTime())
                                .isNotNull()
                                .isEqualTo("2011-10-10 13:19:12Z");
                    });
            assertEquals(296, result.getBitsUsed());
            assertEquals(199704, result.getBitsLeft());
            assertEquals(9999, result.getRequestsLeft());
            assertEquals(2000, result.getAdvisoryDelay());
        }
        {
            assertFalse(message.hasError());
        }
        {
            assertTrue(message.hasId());
            assertEquals("3076", message.getIdAsString());
            assertThat(message.getIdAsNumber()).isNotNull()
                    .isEqualByComparingTo(BigInteger.valueOf(3076L));
            assertThat(message.getIdAsLong()).isNotNull().isEqualTo(3076L);
            assertThat(message.getIdAsInteger()).isNotNull().isEqualTo(3076);
        }
    }

    @Test
    public void read_generateInteger_02_response() throws IOException {
        acceptResourceStream(
                "generateIntegers_02_response.json",
                s -> {
                    final JsonrpcResponseMessage message = JsonrpcResponseMessage.fromJson(s);
                    verify_generateInteger_02_response(message);
                }
        );
    }

    @Test
    public void write_generateInteger_02_response() {
        final JsonrpcResponseMessage message = JsonrpcResponseMessage.newInstance();
        assertEquals(JsonrpcMessage.PROPERTY_VALUE_JSONRPC, message.getJsonrpc());
        message.setResultAsObject(
                GenerateIntegersResult.builder()
                        .random(GenerateIntegersResult.Random.builder()
                                        .data(asList(39, 24, 18, 46, 6, 52, 36, 30, 40, 42, 37, 4, 7, 20, 1, 44, 25, 9,
                                                     21, 29, 51, 41, 14, 15, 48, 50, 31, 17, 3, 19, 45, 35, 2, 43, 26,
                                                     16, 5, 23, 12, 8, 10, 47, 13, 33, 34, 49, 22, 11, 28, 27, 38, 32))
                                        .completionTime("2011-10-10 13:19:12Z")
                                        .build()
                        )
                        .bitsUsed(296)
                        .bitsLeft(199704)
                        .requestsLeft(9999)
                        .advisoryDelay(2000)
                        .build()
        );
        message.setIdAsInteger(3076);
        verify_generateInteger_02_response(message);
    }
}
