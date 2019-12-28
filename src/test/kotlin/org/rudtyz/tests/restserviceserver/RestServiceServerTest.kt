package org.rudtyz.tests.restserviceserver

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.rudtyz.tests.SampleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

@RestClientTest(SampleService::class)
class RestServiceServerTest {

    // SampleService 의 restTemplate 는
    // 반드시 builder 로 생성할것
    // Config class 를 통해서 Bean 으로 new RestTemplate ...
    // 로 만들었을때는 Autowired 로 주입받지 못하고 추가적인 설정이 필요함
    @Autowired
    lateinit var mockRestServiceServer: MockRestServiceServer

    @Autowired
    lateinit var sampleService: SampleService

    @Test
    fun serviceSample() {
        mockRestServiceServer.expect(requestTo("/request_sample"))
                .andRespond(withSuccess("response_sample", MediaType.TEXT_PLAIN))

        val seriousString = sampleService.seriousWork()
        assertThat(seriousString).isEqualTo("response_sample")
    }

    @Test
    fun serviceGithubSample() {
        mockRestServiceServer.expect(requestTo("https://github.com"))
                .andRespond(withSuccess("github_body", MediaType.TEXT_PLAIN))

        val githubString = sampleService.getGithub()
        assertThat(githubString).isEqualTo("github_body")
    }
}