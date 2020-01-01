package org.rudtyz.tests.argument;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicationEnvironment {

    /**
     * 환경변수
     * 프로그램 시작전
     * hello=world
     * foo=bar
     *
     * IDE 에서는 환경 변수 설정에 추가
     * Environment variables: hello=world;foo=bar
     */
    @Autowired
    ConfigurableEnvironment env;

    @BeforeEach
    public void setUp() {
        TestPropertyValues.of("hello=world", "foo=bar").applyTo(env);
    }

    @Test
    public void hello_world(){
        assertThat(env.getProperty("hello")).isEqualTo("world");
    }

    @Test
    public void foo_bar(){
        assertThat(env.getProperty("foo")).isEqualTo("bar");
    }
}
