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

import com.github.jinahya.jsonrpc.bind.v2.JsonrpcResponseMessage;
import com.github.jinahya.jsonrpc.bind.v2.examples.ExampleResourceResponseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static com.github.jinahya.jsonrpc.bind.BeanValidationTests.requireValid;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public abstract class RandomOrgV2BasicResponseTest
        extends ExampleResourceResponseTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void read_generateInteger_01_response() throws IOException {
        acceptResourceStream(
                "generateIntegers_01_response.json",
                s -> {
                    final JsonrpcResponseMessage message = JsonrpcResponseMessage.fromJson(s);
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
                    final String json = message.toJson();
                    log.debug("json: {}", json);
                    assertNotNull(json);
                }
        );
    }
}
