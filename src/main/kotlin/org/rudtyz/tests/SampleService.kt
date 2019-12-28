package org.rudtyz.tests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class SampleService(
        restTemplateBuilder: RestTemplateBuilder
) {
    private val restTemplate = restTemplateBuilder.build()

    fun seriousWork(): String {
        val res = restTemplate.getForObject("/request_sample", String::class.java)
        val a = 1
        val b = 2
        val c = a + b
        return "$res $c"
    }
}
