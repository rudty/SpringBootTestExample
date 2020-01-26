package org.rudtyz.tests.db

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.rudtyz.tests.Sample
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

// @DataJpaTest ?
// JDBC 기본 기능만 사용하려면
// @DataJdbcTest 를 활용하십시오
// 본 예제 에서는 create table 쿼리 생략 용도로
// JPA 까지 로드했습니다

@DataJpaTest
class JdbcTest {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    // 1. 기본적으로 @Test 종료후에는 자동으로 db의 롤백을 하지만
    // Propagation.NOT_SUPPORTED 이 붙은 transaction 은
    // 테스트 종료 시 롤백하지 않습니다.
    // 2. 이 테스트는 Table 과 관계 없는 테스트이므로
    // @SpringBootTest 대신 @DataJdbcTest 만 사용하여도 동작합니다.
    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    fun select_1() {
        val num = jdbcTemplate.queryForObject(
                "select 1 as number;", Int::class.java)
        assertThat(num).isEqualTo(1)
    }

    // 이 테스트는 Sample Entity 를 사용하고 있으므로
    // @SpringBootTest 가 필요합니다.
    // 만약 Entity 에 의존적이지 않다면
    // @DataJdbcTest 만 사용하여도 동작합니다.
    @Test
    fun insertSample() {
        jdbcTemplate.execute("insert into sample values('insertSample', 99);");
        val sample = jdbcTemplate
                .queryForObject("select name, number from sample where name='insertSample' limit 1;") { rs, _ ->
                    // new RowMapper<Sample>() {
                    val name = rs.getString(1)
                    val number = rs.getInt(2)
                    Sample(name, number)
                }

        assertThat(sample).isNotNull
        assertThat(sample?.name).isEqualTo("insertSample")
        assertThat(sample?.number).isEqualTo(99)
    }
}