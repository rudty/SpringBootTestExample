package org.rudtyz.tests.env

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.test.context.SpringBootTest

// 이 테스트는
// SpringBootTest 에 arg 를 사용하였습니다.
// 실행 인자로 java -jar .... 과 같이 추가하는것 과 같으며
// 테스트를 위해서는 반드시 이 테스트 단독으로 실행하세요
@SpringBootTest(args = ["--app.t1=123", "--app.t2=456"])
class ApplicationStartArgument {
    @Autowired
    lateinit var args: ApplicationArguments

    @Test
    fun argv() {
        assertThat(args.optionNames).contains("app.t1")
        assertThat(args.optionNames).contains("app.t2")

        assertThat(args.getOptionValues("app.t1")).contains("123")
        assertThat(args.getOptionValues("app.t2")).contains("456")
        assertThat(args.getOptionValues("app.t3")).isNull()
    }

}