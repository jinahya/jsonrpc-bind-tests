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

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage;
import com.github.jinahya.jsonrpc.bind.v2.examples.ExampleResourceRequestTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static com.github.jinahya.jsonrpc.bind.BeanValidationTests.requireValid;
import static com.github.jinahya.jsonrpc.bind.v2.JsonrpcRequestMessage.fromJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public abstract class RandomOrgV2BasicRequestTest
        extends ExampleResourceRequestTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void read_generateInteger_01_request() throws IOException {
        acceptResourceStream(
                "generateIntegers_01_request.json",
                s -> {
                    final JsonrpcRequestMessage message = fromJson(s);
                    log.debug("message: {}", message);
                    requireValid(message);
                    {
                        assertEquals("generateIntegers", message.getMethod());
                    }
                    {
                        assertTrue(message.hasParams());
                        final GenerateIntegersParams params = message.getParamsAsObject(GenerateIntegersParams.class);
                        requireValid(params);
                        assertEquals("6b1e65b9-4186-45c2-8981-b77a9842c4f0", params.getApiKey());
                        assertEquals(6, params.getN());
                        assertEquals(1, params.getMin());
                        assertEquals(6, params.getMax());
                        assertTrue(params.getReplacement());
                    }
                    {
                        assertTrue(message.hasId());
                        assertEquals("42", message.getIdAsString());
                        assertThat(message.getIdAsNumber()).isNotNull().isEqualByComparingTo(BigInteger.valueOf(42L));
                        assertThat(message.getIdAsLong()).isNotNull().isEqualTo(42L);
                        assertThat(message.getIdAsInteger()).isNotNull().isEqualTo(42);
                        assertFalse(message.isNotification());
                    }
                    final String json = message.toJson();
                    log.debug("json: {}", json);
                    assertNotNull(json);
                }
        );
    }
}
