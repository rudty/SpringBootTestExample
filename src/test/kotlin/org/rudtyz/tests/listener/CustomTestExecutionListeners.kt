package org.rudtyz.tests.listener

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestExecutionListener
import org.springframework.test.context.TestExecutionListeners

@SpringBootTest
@TestExecutionListeners(value = [CustomTestExecutionListeners::class])
class TestClass {
    @Test
    fun some() {
        System.out.println("test")
    }
}

class CustomTestExecutionListeners : TestExecutionListener {

    override fun beforeTestExecution(testContext: TestContext) {
        System.out.println("beforeTestExecution " + testContext.testMethod)
    }

    override fun afterTestClass(testContext: TestContext) {
        System.out.println("afterTestClass " + testContext.testClass)
    }

    override fun afterTestMethod(testContext: TestContext) {
        System.out.println("beforeTestExecution " + testContext.testMethod)
    }

    override fun beforeTestClass(testContext: TestContext) {
        System.out.println("beforeTestClass " + testContext.testClass)
    }
}
