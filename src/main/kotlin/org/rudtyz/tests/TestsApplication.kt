package org.rudtyz.tests

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestsApplication

fun main(args: Array<String>) {
	runApplication<TestsApplication>(*args)
}
