package org.rudtyz.tests.bean

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.rudtyz.tests.SampleService
import org.springframework.boot.web.client.RestTemplateBuilder

class MockAndNew {
    @Mock
    lateinit var restTemplateBuilder: RestTemplateBuilder

    lateinit var sampleService: SampleService

    @BeforeEach
    fun setUp() {
        // initMocks 를 호출 전에는 restTemplateBuilder 는 null 입니다
        // 호출 후에는 null 이 아닙니다
        // AutoWired 대신 생성자를 통한 Bean 주입을 사용하고 있기 때문에
        // SpringBootTest 를 사용하지 않고 다음과 같은 방법을 사용할 수 있습니다
        MockitoAnnotations.initMocks(this)
        sampleService = SampleService(restTemplateBuilder)
    }

    // initMocks(this) 구문을 지우면 에러
    @Test
    fun notNullService() {
        sampleService.isRestTemplateNull()
    }
}