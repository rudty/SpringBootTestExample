package org.rudtyz.tests;

import org.springframework.web.bind.annotation.*;

/**
 * 테스트 예제를 작성하기 위해 간단한 controller 를 제작하였습니다.
 * kotlin 안에서 java 를 사용하기 위한 방법은
 * 프로젝트 루트 / build.gradle.kts / sourceSets.main.java.setSrcDirs 를 참고하세요
 */
@RestController
public class SampleController {

    @GetMapping("/")
    public String index() {
        return "hello world";
    }

    @GetMapping("/sample")
    public Sample getSample() {
        return new Sample("sample name", 42);
    }

    @PostMapping("/sample")
    public Sample postSample(@RequestBody Sample sample) {
        return sample;
    }
}
