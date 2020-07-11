package com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.JsonrpcOrgExamples.forEachRequestResource;
import static com.github.jinahya.jsonrpc.bind.v2.examples.jsonrpc_org.JsonrpcOrgExamples.forEachResponseResource;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class JsonrpcOrgExamplesTest {

    @Test
    void testForEachRequestResource() throws IOException {
        forEachRequestResource((n, s) -> {
            log.debug("name: {}", n);
            log.debug("stream: {}", s);
            assertNotNull(s);
        });
    }

    @Test
    void testForEachResponseResource() throws IOException {
        forEachResponseResource((n, s) -> {
            log.debug("name: {}", n);
            log.debug("stream: {}", s);
            assertNotNull(s);
        });
    }
}