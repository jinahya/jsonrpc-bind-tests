package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.JsonrpcOrgExamples.forEachRequestResource;
import static com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.JsonrpcOrgExamples.forEachResponseResource;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class JsonrpcOrgExamplesTest {

    /**
     * Tests {@link JsonrpcOrgExamples#forEachRequestResource(Consumer)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void testForEachRequestResource() throws IOException {
        forEachRequestResource(s -> {
            log.debug("s: {}", s);
            assertNotNull(s);
        });
    }

    /**
     * Tests {@link JsonrpcOrgExamples#forEachResponseResource(Consumer)} method.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void testForEachResponseResource() throws IOException {
        forEachResponseResource(s -> {
            log.debug("s: {}", s);
            assertNotNull(s);
        });
    }
}