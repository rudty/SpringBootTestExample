package org.rudtyz.tests.output

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.system.CapturedOutput
import org.springframework.boot.test.system.OutputCaptureExtension
import java.lang.Exception
import java.lang.RuntimeException

/**
 * 표준 출력으로 출력한것이 output 에 추가됩니다.
 */
@ExtendWith(OutputCaptureExtension::class)
class SystemOutPrintlnTests {
    @Test
    fun testSystemOutPrintln(output: CapturedOutput) {
        System.out.println("hello world")
        System.out.println("hello world")
        assertThat(output.toString()).isEqualTo("hello world\nhello world\n")
    }

    @Test
    fun testException(output: CapturedOutput) {
        try {
            throw RuntimeException("err")
        } catch(e: Exception) {
            e.printStackTrace()
        }
        assertThat(output.err).contains("java.lang.RuntimeException: err")
    }
}