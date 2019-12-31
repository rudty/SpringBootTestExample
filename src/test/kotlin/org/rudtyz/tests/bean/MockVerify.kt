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

//    @BeforeEach
//    fun setUpExample() {
//        MockitoAnnotations.initMocks(this)
//        sampleService = SampleService(restTemplateBuilder)
//    }

    @Test
    fun callBuildInService() {
        /**
         * 보통 테스트에서는 필드 변수로 Mock 변수를 두고 쓰고 있으므로
         * 검사를 하기 위해서는 일단 Mock 의 초기화 후 함수를 호출하는지 검사가 필요합니다.
         *
         * @BeforeEach 에서는 아직 Mock 객체가 null 이므로 명시적으로 초기화 하기
         * 위해서는  MockitoAnnotations.initMocks(this) 를 초기화 한 후
         * 서비스를 초기화하여 사용할 수 있습니다. 위 주석 참고
         */
        val restTemplateBuilder = mock(RestTemplateBuilder::class.java)
        SampleService(restTemplateBuilder)

        verify(restTemplateBuilder).build()
    }

}