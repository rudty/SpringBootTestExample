package org.rudtyz.tests.bean

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.rudtyz.tests.SampleService
import org.springframework.boot.web.client.RestTemplateBuilder

class MockVerify {

    @Test
    fun mockVerifyList() {
        // java: List<String> list = mock(List.class);
        @Suppress("UNCHECKED_CAST")
        val list: MutableList<String> = mock(MutableList::class.java) as MutableList<String>
        list.add("hello")

        /**
         * 이 함수보다 위쪽에서 list 를 활용해서
         * .add("hello")를 호출하였다면 통과
         * 아니라면 예외를 던집니다.
         */
        verify(list).add("hello")
    }

    @Test
    fun callBuildInService() {
        /**
         * 보통 테스트에서는 필드 변수로 Mock 변수를 두고 쓰고 있으므로
         * 검사를 하기 위해서는 일단 Mock 의 초기화 후 함수를 호출하는지 검사가 필요합니다.
         *
         * 보통 테스트에서는 필드 변수를 주로 사용하고 있는데
         * 만약 @Before, @BeforeEach 에서 초기화가 필요하다면
         * https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/bean/MockAndNew.kt
         * 를 참고하여 작성할 수 있습니다.
         */
        val restTemplateBuilder = mock(RestTemplateBuilder::class.java)
        SampleService(restTemplateBuilder)

        verify(restTemplateBuilder).build()
    }

}