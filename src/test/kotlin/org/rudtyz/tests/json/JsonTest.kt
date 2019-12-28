package org.rudtyz.tests.json

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.rudtyz.tests.Sample
import org.springframework.boot.test.json.JacksonTester

// SpringBoot 테스트를 쓰지 않은 것을 참고하세요
class JsonTest {
    lateinit var json: JacksonTester<Sample>

    @BeforeEach
    fun setUp() {
        // Autowired 를 사용하여 Bean 주입으로도 사용할 수 있지만
        // 기본 Bean 주입 생성 시간이 걸리고
        // JacksonTester 가 generic 이라서
        // IDE 의 지원을 받지 못할 수 있습니다.
        JacksonTester.initFields(this, ObjectMapper())
    }

    @Test
    fun testJson() {
        val sample = Sample(
                name = "json_name",
                number = 88
        )

        val sampleJson = json.write(sample)
        assertThat(sampleJson)
                .hasJsonPathStringValue("$.name")
                .extractingJsonPathStringValue("$.name").isEqualTo("json_name")
        assertThat(sampleJson)
                .hasJsonPathNumberValue("$.number")
                .extractingJsonPathNumberValue("$.number").isEqualTo(88)

//        print(sampleJson.getJson())

    }
}