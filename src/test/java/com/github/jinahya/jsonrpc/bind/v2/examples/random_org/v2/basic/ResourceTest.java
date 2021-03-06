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

import com.github.jinahya.jsonrpc.bind.v2.examples.ExampleResourceTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ResourceTest extends ExampleResourceTest {

    @Test
    void r_generateInteger_01_request() throws IOException {
        acceptResourceStream(
                "generateIntegers_01_request.json",
                s -> {
                }
        );
    }
}
