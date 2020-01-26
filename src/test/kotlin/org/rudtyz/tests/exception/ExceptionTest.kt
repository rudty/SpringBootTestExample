package org.rudtyz.tests.exception

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ExceptionTest {
    @Test
    fun throwRuntimeException() {
        // 예외는 반드시 발생해야 합니다
        // 만약 예외가 발생하지 않는다면 assert
        Assertions.assertThrows(RuntimeException::class.java) {
            throw RuntimeException("something went wrong")
        }
    }
}
