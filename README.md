# SpringBootTestExample
스프링 부트 테스트 클래스 활용법 예제 

코틀린을 사용했지만 자바로 이식이 쉽도록 작성하였음

프로젝트 는 2019년 12월 현재 [https://start.spring.io](https://start.spring.io) 기본값인 2.2.2 로 사용하였음

- [테스트 폴더 바로가기](https://github.com/rudty/SpringBootTestExample/tree/master/src/test/kotlin/org/rudtyz/tests)  
- [소스 폴더 바로가기](https://github.com/rudty/SpringBootTestExample/tree/master/src/main/kotlin/org/rudtyz/tests)

### HttpClient 테스트 
모두 같은 기능을 구현하였으며 기능상에 큰 차이는 없으니 선호하는 클래스를 활용하여 작성할 것 
 
WebTestClient 는 WebFlux 모듈의 추가가 필요 
- [TestRestTemplate](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/webclient/TestRestTemplateTest.kt)
- [WebMvcTest](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/webclient/WebMvcTest.kt)
- [WebTestClientTest](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/webclient/WebTestClientTest1.kt) - Kotlin
- [WebTestClientTest](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/webclient/WebTestClientTest2.java) - Java

### RestTemplate 테스트
RestTemplate 를 원격지 연결 없이 테스트 안에서 동작할 수 있도록 한다
- [MockRestServiceServer](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/restserviceserver/RestServiceServerTest.kt)

### JpaRepository 테스트
- [JpaRepository](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/jpa/JpaRepositoryTest.kt)

### json 테스트
- [JacksonTester](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/json/JsonTest.kt)

### Logger, System.out.println 이나 Exception.printStackTrace 출력 테스트
 - [SystemOutPrintlnTests](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/output/SystemOutPrintlnTests.kt)
 - [LoggerTests](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/output/LoggerTests.kt) 
 
## 기타 
### 표준 assert 와 assertThat
- assertThat 은 org.assertj.core.api.Assertions.assertThat
- assert 와 assertThat 모두 사용 가능 
- assert 사용시 간편함 == 비교 true 에만 유의 
- assertThat 은 fail 시 틀린 부분을 보여줌

### kotlin project 에서 java 사용
- 기본적으로는 src/main/java 폴더를 만들어서 사용
- 소스가 나눠져 있으면 가독성이 좋지 않으므로 kotlin 폴더로 통합
- build.gradle.kts 의 sourceSets main java setSrcDirs 참고

### h2-console 을 사용하여 sql 로 조회 
- 다음 코드를 application.properties 에 추가  
```properties
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```
- [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/) 접속
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Connect 클릭 

