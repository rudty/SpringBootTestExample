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

### Mock 테스트
- [MockBean](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/bean/MockBeanTest.kt)
  - Component 의 껍데기를 가져와서 반환값을 조정  
- [SpyBean](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/bean/SpyBeanTest.kt)
  - Component 를 가져와서 반환값을 조정
- [initMocks](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/bean/MockAndNew.kt)
  - @BeforeEach 에서 mock init 후 사용 
- [verify](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/bean/MockVerify.kt)
  - mock 의 특정 함수를 사용했는지 확인
- [TestConfig](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/bean/TestConfig.kt)
  - Test 용 Bean 및 Test 용 Configuration 설정 

### RestTemplate 테스트
RestTemplate 를 원격지 연결 없이 테스트 안에서 동작할 수 있도록 한다
- [MockRestServiceServer](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/restserviceserver/RestServiceServerTest.kt)

### 데이터베이스
- [JpaRepository](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/db/JpaRepositoryTest.kt)
- [JdbcTemplate](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/db/JdbcTest.kt)

### json 테스트
- [JacksonTester](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/json/JsonTest.kt)

### Logger, System.out.println 이나 Exception.printStackTrace 출력 테스트
 - [SystemOutPrintlnTests](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/output/SystemOutPrintlnTests.kt)
 - [LoggerTests](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/output/LoggerTests.kt) 
 
### 환경 테스트
 - [프로그램 argument 주기](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/env/ApplicationStartArgument.kt)
 - [환경변수에 추가](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/env/ApplicationEnvironment.java) 
 
### 예외 테스트
- [예외가 발생하지 않을 시 assert](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/exception/ExceptionTest.kt)
 
### 테스트 자체에 listener 를 받기 
- [TestListener](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/listener/CustomTestExecutionListeners.kt)

## 기타 
### ApplicationContext 재시작
기본적으로 @SpringBootTest 어노테이션이 붙은 테스트를 사용한다면 ApplicationContext 를 생성하고 로드하게 됩니다.
여러 개의 테스트를 한번에 테스트 시에는(./gradlew test 수행 시)
기존 컨테이너가 존재한다면 재활용하게 됩니다
이 때 시작 인자를 테스트 하고 있는 이 테스트가 오류가 발생이 되는데
문제를 해결하기 위해서는 다시 ApplicationContext 를 생성하고 로드가 필요합니다
1. @MockBean 어노테이션을 사용하여 강제로 로드하게 합니다
@MockBean 은 컨테이너에 Bean 주입이 필요하므로
ApplicationContext 를 다시 불러오게 하는 효과가 있습니다
2. @SpringBootTest 에 webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT 를 추가합니다
랜덤 포트 설정 및 실제로 서버 오픈이 필요하기 때문에 ApplicationContext 를 다시 불러오게 하는 효과가 있습니다
 
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

