package org.rudtyz.tests.profile

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles(profiles = ["dev", "real"])
class ActiveProfileTest {

    @Autowired
    lateinit var env: Environment

    @Test
    fun foo() {
        assertThat(env.activeProfiles).contains("dev")
        assertThat(env.activeProfiles).contains("real")
        assertThat(env.activeProfiles).doesNotContainSequence("foo")
    }
}