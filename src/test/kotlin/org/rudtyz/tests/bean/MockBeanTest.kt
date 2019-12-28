package org.rudtyz.tests.bean

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.rudtyz.tests.SampleController
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class MockBeanTest {
    @MockBean
    lateinit var sampleController: SampleController

    /**
     *  내부적으로는 마지막에 호출한 함수를 추적하여 return 값을 바꾸는 동작입니다.
     *  따라서
     *
     *  sampleController.index()
     *  BDDMockito
     *      .given("")
     *      .willReturn("hello mock")
     *
     *  도 동작합니다
     */
    @Test
    fun mock_index() {
        BDDMockito
                .given(sampleController.index())
                .willReturn("hello mock")
        
        assertThat(sampleController.index()).isEqualTo("hello mock")
    }


    /**
     * MockBean 은 내부적으로 메서드의 형태만 있고 구현은 없어서
     * mock 없이 호출 시 null 값이 들어가게 됩니다
     *
     * 만약 모든 기존 Bean 이 있는 형태로 테스트가 필요할 시에는
     * SpyBean 을 활용하세요
     */
    @Test
    fun call_sample() {
        assertThat(sampleController.sample).isNull()
    }
}