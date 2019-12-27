package org.rudtyz.tests.webclient

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.rudtyz.tests.Sample
import org.rudtyz.tests.SampleController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(value = [SampleController::class])
// WebMvcTest 의 value 에 controller clz 를 넣으면 해당 Controller 만 Bean 생성
// 없다면 모든 컨트롤러 생성
class WebMvcTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getString() {
        mockMvc.perform(get("/"))
                .andExpect(handler().handlerType(SampleController::class.java))     // 처리 controller clz
                .andExpect(handler().methodName("index"))                    // 처리 함수 이름
                .andExpect(status().isOk)
                .andExpect(content().string("hello world"))
                .andDo(print())
    }

    @Test
    fun getSample() {
        mockMvc.perform(get("/sample"))
                .andExpect(handler().handlerType(SampleController::class.java))     // 처리 controller clz
                .andExpect(handler().methodName("getSample"))                    // 처리 함수 이름
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", `is`("sample name")))
                .andExpect(jsonPath("$.number", `is`(42)))
                .andDo(print())
    }


    @Test
    fun postSample() {
        val sampleName = "postSample"
        val sampleNumber = 99
        val requestSample = Sample(name = sampleName, number = sampleNumber)

        mockMvc.perform(post("/sample")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestSample)) // 바로는 사용할 수 없어서 확장함수를 사용합니다. 아래를 참고하세요
                .andExpect(handler().handlerType(SampleController::class.java))     // 처리 controller clz
                .andExpect(handler().methodName(SampleController::postSample.name)) // 처리 함수 이름
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", `is`(sampleName)))
                .andExpect(jsonPath("$.number", `is`(sampleNumber)))
                .andDo(print())
    }

}

/**
 * ObjectMapper 를 사용한 MockMvc 에 value 추가 확장함수
 */
private fun MockHttpServletRequestBuilder.content(v: Any): RequestBuilder {
    val mapper = ObjectMapper()
    val jsonContent = mapper.writeValueAsString(v)
    return this.content(jsonContent)
}
