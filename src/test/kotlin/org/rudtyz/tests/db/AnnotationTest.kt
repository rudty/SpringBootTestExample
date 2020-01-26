package org.rudtyz.tests.db

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlConfig
import org.springframework.test.context.transaction.AfterTransaction
import org.springframework.test.context.transaction.BeforeTransaction

@JdbcTest
class AnnotationTest {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @BeforeTransaction
    fun beforeTran() {
        System.out.println("hello tran")
    }

    @AfterTransaction
    fun afterTran() {
        System.out.println("bye tran")
    }

    @Sql(
            scripts = ["/a.sql"],
            config = SqlConfig(commentPrefix = "```", separator = "@")
            //java config=@SqlConfig(commentPrefix = "```", separator = "@")
    )
    @Test
    fun runSQL() {
        // a.sql 에 annotation_test 가 정의되어있기 때문에 실행이 가능합니다
        // 실행 시 @BeforeTransaction 과 @AfterTransaction 이 붙은 함수가 전후에 실행됩니다
        jdbcTemplate.execute("select * from annotation_test")
    }


}