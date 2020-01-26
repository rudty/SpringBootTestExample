package org.rudtyz.tests.bean

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@TestConfiguration
class HelloWordConfig1 {
    @Bean
    fun helloWorldBean() = "HelloWorld1"
}

@TestConfiguration
class HelloWordConfig2 {
    @Bean
    fun helloWorldBean() = "HelloWorld2"
}

// @Configuration: 실행 시 컨테이너에 반드시 넣습니다
// @TestConfiguration: @Import 로 주입이 필요합니다
@SpringBootTest
@Import(HelloWordConfig1::class)
class TestConfig {
    /**
     * Qualifier 는 실행에 영향을 주지않습니다만
     * IDE 의 경고를 제거합니다
     */
    @Qualifier("helloWorldBean")
    @Autowired
    lateinit var helloWorldBean: String

    @Test
    fun helloWorld() {
        print(helloWorldBean)
    }
}