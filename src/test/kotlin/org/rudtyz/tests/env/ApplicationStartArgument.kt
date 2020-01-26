package org.rudtyz.tests.env

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.rudtyz.tests.SampleController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

// 이 테스트는
// SpringBootTest 에 arg 를 사용하였습니다.
// 실행 인자로 java -jar .... 과 같이 추가하는것 과 같습니다.
@SpringBootTest(args = ["--app.t1=123", "--app.t2=456"])
class ApplicationStartArgument {
    @Autowired
    lateinit var args: ApplicationArguments

    // 기본적으로 @SpringBootTest 어노테이션이 붙은 테스트를 사용한다면
    // ApplicationContext 를 생성하고 로드하게 됩니다.
    // 여러 개의 테스트를 한번에 테스트 시에는(./gradlew test 수행 시)
    // 기존 컨테이너가 존재한다면 재활용하게 됩니다
    // 이 때 시작 인자를 테스트 하고 있는 이 테스트가 오류가 발생이 되는데
    // 문제를 해결하기 위해서는 다시 ApplicationContext 를 생성하고 로드가 필요합니다
    // 1. @MockBean 어노테이션을 사용하여 강제로 로드하게 합니다
    // @MockBean 은 컨테이너에 Bean 주입이 필요하므로
    // ApplicationContext 를 다시 불러오게 하는 효과가 있습니다
    // 2. @SpringBootTest 에 webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT 를 추가합니다
    // 랜덤 포트 설정 및 실제로 서버 오픈이 필요하기 때문에 ApplicationContext 를 다시 불러오게 하는 효과가 있습니다
    @MockBean
    lateinit var sampleController: SampleController

    @Test
    fun argv() {
        assertThat(args.optionNames).contains("app.t1")
        assertThat(args.optionNames).contains("app.t2")

        assertThat(args.getOptionValues("app.t1")).contains("123")
        assertThat(args.getOptionValues("app.t2")).contains("456")
        assertThat(args.getOptionValues("app.t3")).isNull()
    }

}