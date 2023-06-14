# spring-security-practice

# 스프링 시큐리티의 간단 개념 
## 인증 관련 로직의 흐름을 설명
[스프링 시큐리티 인증 관련 정리파일](https://github.com/yeomyaloo/spring-security-practice/files/11650790/spring.security.pdf)


# 스프링 시큐리티 적용을 해보자(목표)
## 1. 스프링 시큐리티 설정
## 2. authenticationFilter를 대신해서 JWT 사용을 위한 필터를 구현해보자
- 해당 작업은 서버를 구조가 어떻게 되느냐에 따라서 달라진다.
- 분산 서버의 경우라면 아래처럼 구현할 수 있다.
![image](https://github.com/yeomyaloo/spring-security-practice/assets/81970382/72c9bf79-a47b-46f1-b3b2-1f088cd128b0)

- **단일 서버로 하나의 프로세스 아래서 진행된다면?**
    - 필터에 해당 작업을 몰아두고 진행해도 무방하다. (인증에 성공했을 로직에 jwt를 발급하고 redis에 해당 정보를 넣어 세션 유지를 진행하면 된다.)
    - jwt 발급과 인증 작업을 다른 필터로 두고 진행하는 것이 oauth 추가 진행에 더 유연하게 대처할 수 있다.
    - 해당 작업이 진행되었다면 다음단계로 넘어가기 위해서 manager, provider들에게 인증 객체를 넘겨주어야 한다.

## 3. Logout 관련 작업을 진행해보자.
## 4. 인가(athorization)작업을 진행해보자. (역할에 따라서 접근할 수 있는 페이지를 나눈다든가 하는 등의 ..) 
- 해당 작업은 해당 페이지에 접근할 권리가 있는지를 확인할 때 사용된다.
- 작업은 interceptor를 사용해서 진행한다. 이때 여러 서버로 나누어진 경우라면 restTemplate에 해당 인증 정보를 넘겨주어야 하는데 이 작업은 cache 작업인것인가
