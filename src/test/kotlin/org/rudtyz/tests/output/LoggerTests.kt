package org.rudtyz.tests.output

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.system.CapturedOutput
import org.springframework.boot.test.system.OutputCaptureExtension
import java.util.logging.Logger

/**
 * 표준 출력으로 출력한것이 output 에 추가됩니다.
 * logger 를 사용해도 대부분의 logger 는 console 에도 출력하고 있으므로
 * 테스트 가능합니다.
 */
@ExtendWith(OutputCaptureExtension::class)
class LoggerTests {

    companion object {
        private val logger: Logger = Logger.getLogger(this.javaClass.name)
    }

    @Test
    fun testSystemOutPrintln(output: CapturedOutput) {
        logger.info("hello world")
        Assertions.assertThat(output.toString()).contains("hello world")
    }

}