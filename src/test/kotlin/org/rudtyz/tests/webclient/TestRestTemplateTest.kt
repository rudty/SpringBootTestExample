package org.rudtyz.tests.webclient

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.rudtyz.tests.Sample
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

// 반드시 컨테이너 기동이 필요해서 기본값인 MOCK 을 제외한 인자를 넣을 것.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestRestTemplateTest {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun getString() {
        val body = testRestTemplate.getForObject("/", String::class.java)
        assertThat(body).isEqualTo("hello world")
    }

    @Test
    fun getSample() {
        val sample = testRestTemplate.getForObject("/sample", Sample::class.java)
        assertThat(sample).isNotNull
        assertThat(sample.name).isEqualTo("sample name")
        assertThat(sample.number).isEqualTo(42)
    }

    @Test
    fun postSample1() {
        val sampleName = "postSample"
        val sampleNumber = 99
        val requestSample = Sample(name = sampleName, number = sampleNumber)

        val resSample = testRestTemplate.postForObject("/sample", requestSample, Sample::class.java)

        assertThat(resSample).isNotNull
        assertThat(resSample.name).isEqualTo(sampleName)
        assertThat(resSample.number).isEqualTo(sampleNumber)
    }

    @Test
    fun postSample2() {
        val sampleName = "postSample"
        val sampleNumber = 99
        val requestSample = Sample(name = sampleName, number = sampleNumber)

        val res = testRestTemplate.postForEntity("/sample", requestSample, Sample::class.java)

        assertThat(res).isNotNull
        assertThat(res.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(res.body).isNotNull
        assertThat(res.body?.name).isEqualTo(sampleName)
        assertThat(res.body?.number).isEqualTo(sampleNumber)
    }

}