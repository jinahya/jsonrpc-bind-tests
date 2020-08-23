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

import org.assertj.core.util.introspection.ClassUtils;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.hibernate.validator.testutil.ValidationInvocationHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * An abstract class for testing subclasses of {@link AbstractJsonrpcObject} class.
 *
 * @param <T> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class JsonrpcObjectTest<T extends JsonrpcObject> {

    /**
     * Creates a new instance with specified object class.
     *
     * @param objectClass the object class to test.
     * @see #objectClass
     */
    protected JsonrpcObjectTest(final Class<T> objectClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClazz is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link Object#toString()} method on an instance of {@link T}.
     */
    @DisplayName("toString() method returns non-null")
    @Test
    void testToString() {
        final String actual = objectInstance().toString();
        assertNotNull(actual);
    }

    /**
     * Tests {@link JsonrpcObject#isContextuallyValid()} method.
     */
    @Test
    void testIsContextuallyValid() {
        {
            assertTrue(objectInstance().isContextuallyValid());
        }
        {
            assertTrue(validationProxy().isContextuallyValid());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    JsonrpcObject validationProxy() {
        if (validationProxy == null) {
            final Validator validator = Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();
            final ClassLoader classLoader = objectClass.getClassLoader();
            final Class<?>[] classInterfaces = ClassUtils.getAllInterfaces(objectClass).toArray(new Class<?>[0]);
            final InvocationHandler invocationHandler = new ValidationInvocationHandler(objectInstance(), validator);
            validationProxy = (JsonrpcObject) Proxy.newProxyInstance(classLoader, classInterfaces, invocationHandler);
        }
        return validationProxy;
    }

    @SuppressWarnings({"unchecked"})
    T objectInstance() {
        try {
            return (T) constructorHandle().invoke();
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    MethodHandle constructorHandle() {
        if (constructorHandle == null) {
            try {
                constructorHandle = MethodHandles.lookup().unreflectConstructor(objectConstructor());
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException(iae);
            }
        }
        return constructorHandle;
    }

    Constructor<T> objectConstructor() {
        if (objectConstructor == null) {
            try {
                objectConstructor = objectClass.getDeclaredConstructor();
            } catch (final NoSuchMethodException nsme) {
                throw new RuntimeException(nsme);
            }
            objectConstructor.setAccessible(true);
        }
        return objectConstructor;
    }

    protected final Class<T> objectClass;

    private JsonrpcObject validationProxy;

    private Constructor<T> objectConstructor;

    private MethodHandle constructorHandle;
}
