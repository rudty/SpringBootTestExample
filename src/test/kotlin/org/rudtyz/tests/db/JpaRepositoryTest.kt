package org.rudtyz.tests.db

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.rudtyz.tests.Sample
import org.rudtyz.tests.SampleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
class JpaRepositoryTest {
    @Autowired
    lateinit var repository: SampleRepository

    private fun insertDummyData1000() {
        for (i in 1..1000) {
            val s = Sample(
                    name = "rep_sample $i",
                    number = i
            )
            repository.save(s)
        }
    }

    private fun insertDummyData1() {
        val s = Sample(
                name = "rep_sample",
                number = 3
        )
        repository.save(s)
    }

    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun save_and_load_all() {
        insertDummyData1()
        val l = repository.findAll()
        assertThat(l).isNotNull
        assertThat(l).isNotEmpty
    }


    @Test
    fun select_one_1_not_found_throw() {
//        insertDummyData1()
        assertThrows<RuntimeException> {
            repository.getOne("rep_sample")
        }
}

    @Test
    fun select_one_1_not_found_optional() {
        val elem = repository.findById("rep_sample")
        assertThat(elem.isEmpty).isTrue()
    }

    @Test
    fun select_one_1_found_optional() {
        insertDummyData1()
        val elem = repository.findById("rep_sample")
        assertThat(elem.isEmpty).isFalse()
    }

    @Test
    fun select_offset_limit() {
        insertDummyData1000()

        /**
         * select *
         * from sample
         * order by
         *   number desc
         * limit 10 offset 20
         *
         * limit = size
         * offset = page * size
         */
        val pageRequest = PageRequest.of(
                /*page*/2,
                /*size*/10,
                Sort.Direction.DESC,
                /*number 로 order by */"number")
        val queryResult = repository.findAll(pageRequest)
        assertThat(queryResult).isNotNull
        assertThat(queryResult).isNotEmpty

        val list = queryResult.content
        assertThat(list).isNotNull

        val firstElem = list[0]
        assertThat(firstElem).isNotNull
        assertThat(firstElem.name).isEqualTo("rep_sample 980")
        assertThat(firstElem.number).isEqualTo(980)
    }
}