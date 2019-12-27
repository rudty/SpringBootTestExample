package org.rudtyz.tests.webclient

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.rudtyz.tests.Sample
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

// spring-boot-starter-webflux 를 build.gradle 에 추가할 것.
// 반드시 컨테이너 기동이 필요해서 기본값인 MOCK 을 제외한 인자를 넣을 것.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class WebTestClientTest1 {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun getString() {
        webTestClient.get()
                .uri("/")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .consumeWith {
                    // 코틀린에서는 문법 오류같은데 해결이 잘안됨....
                    // 자바라면
                    // expectBody(String.class)
                    // isEqualTo("hello")
                    // 로 가능
                    val resBody = String(it.responseBody!!)
                    Assertions.assertThat(resBody).isEqualTo("hello world")
                }
    }

    @Test
    fun getSample() {
        webTestClient.get()
                .uri("/sample")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.name").isEqualTo("sample name")
                .jsonPath("$.number").isEqualTo(42)
    }

    @Test
    fun postSample() {
        val sampleName = "postSample"
        val sampleNumber = 99
        val requestSample = Sample(name = sampleName, number = sampleNumber)

        webTestClient.post()
                .uri("/sample")
                .body(Mono.just(requestSample), requestSample.javaClass)
//                .bodyValue(requestSample) // 이것도 사용가능 비동기 기능을 사용하지 않고 동기로 전달
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.name").isEqualTo(sampleName)
                .jsonPath("$.number").isEqualTo(sampleNumber)
    }
}