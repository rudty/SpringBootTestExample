# SpringBootTestExample
스프링 부트 테스트 클래스 활용법 예제 
[코드 바로가기](https://github.com/rudty/SpringBootTestExample/tree/master/src/test/kotlin/org/rudtyz/tests)
### HttpClient 테스트 
모두 같은 기능을 구현하였으며 기능상에 큰 차이는 없으니 선호하는 클래스를 활용하여 작성할 것 
 
WebTestClient 는 WebFlux 모듈의 추가가 필요 
- [TestRestTemplate](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/webclient/TestRestTemplateTest.kt)
- [WebMvcTest](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/webclient/WebMvcTest.kt)
- [WebTestClientTest](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/webclient/WebTestClientTest1.kt) - Kotlin
- [WebTestClientTest](https://github.com/rudty/SpringBootTestExample/blob/master/src/test/kotlin/org/rudtyz/tests/webclient/WebTestClientTest2.java) - Java

 
## 기타 
### 표준 assert 와 assertThat
- assertThat 은 org.assertj.core.api.Assertions.assertThat
- assert 와 assertThat 모두 사용 가능 
- assert 사용시 간편함 == 비교 true 에만 유의 
- assertThat 은 fail 시 틀린 부분을 보여줌
