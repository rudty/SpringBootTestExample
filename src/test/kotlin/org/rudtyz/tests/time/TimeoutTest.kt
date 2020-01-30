package org.rudtyz.tests.time

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

class TimeoutTest {

    // 시간 제한 설정
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    fun timeout1000() {
//        Thread.sleep(10);
    }
}