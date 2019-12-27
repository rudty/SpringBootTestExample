package org.rudtyz.tests.webclient;

import org.junit.jupiter.api.Test;
import org.rudtyz.tests.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

// spring-boot-starter-webflux 를 build.gradle 에 추가할 것.
// 반드시 컨테이너 기동이 필요해서 기본값인 MOCK 을 제외한 인자를 넣을 것.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class WebTestClientTest2 {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void getString() {
        webTestClient.get()
                .uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("hello world");
    }

    @Test
    public void getSample() {
        webTestClient.get()
                .uri("/sample")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("sample name")
                .jsonPath("$.number").isEqualTo(42);
    }

    @Test
    public void postSample() {
        String sampleName = "postSample";
        int sampleNumber = 99;
        Sample requestSample = new Sample(sampleName, sampleNumber);

        webTestClient.post()
                .uri("/sample")
                .body(Mono.just(requestSample), requestSample.getClass())
//                .bodyValue(requestSample) // 이것도 사용가능 비동기 기능을 사용하지 않고 동기로 전달
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo(sampleName)
                .jsonPath("$.number").isEqualTo(sampleNumber);
    }
}
