package org.rudtyz.tests.bean

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.rudtyz.tests.SampleController
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean

@SpringBootTest
class SpyBeanTest {

    @SpyBean
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

        Assertions.assertThat(sampleController.index()).isEqualTo("hello mock")
    }

    /**
     * SpyBean SampleController 를 그대로 가져오고 있으므로
     * 다른 값도 null 이 아닙니다
     */
    @Test
    fun call_sample() {
        Assertions.assertThat(sampleController.sample).isNotNull
    }
}
