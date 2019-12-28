package org.rudtyz.tests.jpa

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.rudtyz.tests.Sample
import org.rudtyz.tests.SampleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
class JpaRepositoryTest {
    @Autowired
    lateinit var repository: SampleRepository

    @Test
    @Transactional
    fun save_and_load_all() {
        val s = Sample(
                name = "rep_sample",
                number = 3
        )
        repository.save(s)

        val l = repository.findAll()
        assertThat(l).isNotNull
        assertThat(l).isNotEmpty
    }


}