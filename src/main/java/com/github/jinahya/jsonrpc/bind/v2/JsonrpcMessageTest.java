package com.github.jinahya.jsonrpc.bind.v2;

/*-
 * #%L
 * jsonrpc-bind
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

/**
 * An abstract class for testing subclasses of {@link AbstractJsonrpcMessage} class.
 *
 * @param <T> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
public abstract class JsonrpcMessageTest<T extends JsonrpcMessage> extends JsonrpcObjectTest<T> {

    /**
     * Creates a new instance with specified message class.
     *
     * @param objectClass the message class to test.
     * @see #objectClass
     */
    protected JsonrpcMessageTest(final Class<T> objectClass) {
        super(objectClass);
    }
}
