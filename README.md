### **Architecture**

![Untitled](https://github.com/rookedsysc/delivery-app/assets/67862775/6b78c3bf-277a-4fe0-bebb-edb39a33dd7f)

### 기술 스택

Lang & Framework : Java / Kotlin / Spring Boot 2.7 / Spring Security

Database : MySQL, Redis

MQ : RabbitMQ

### 프로젝트 개요

스프링 학습 목적으로 개발한 `개인 프로젝트` 입니다. 배달앱을 모티브로 개발했습니다. 

> **주요 기능 및 개발 내용**
> 
1. 회원가입
2. 유저
    1. 가게 정보 및 메뉴 정보 검색
    2. 주문 접수 Consumer
3. 가게 사장
    1. 가게 등록
    2. 메뉴 등록
    3. 주문 접수 Producer

> 헥사고날 아키텍처
> 

응집도를 낮추고 유지보수 및 확장성을 향상시켰습니다. 각 레이어의 역할을 명확하게 정의하여 개발 및 운영 효율성을 높였습니다.

> MSA 아키텍처
> 

유저와 가맹점주의 서버 분리로 독립적인 확장 및 운영이 가능합니다. 새로운 기능 추가 및 변경에 유연하게 대응할 수 있습니다.

> Response 공통화
> 

공통 Response 모델을 통해 개발 코드 중복을 줄이고 일관성을 유지합니다. 상세한 Response를 남겨 개발자간 의사소통 효율을 증진 시킵니다.

> Rabbit MQ와 Non Blocking
> 

동기식 구조의 응답 지연 문제를 해결하여 시스템 성능을 개선합니다. 비동기 처리를 통해 높은 트래픽에도 안정적으로 대응할 수 있습니다.

> AOP
> 

User Session 정보 추출 코드를 AOP로 간략화하여 코드 관리 효율성을 높입니다. 'user/me' 컨트롤러 및 서비스에서 User 정보 추출 코드를 중복 없이 사용할 수 있습니다.

> Kotlin Migration
> 

Java보다 훨씬 적은 코드로 동일한 기능을 구현하여 코드 관리 및 유지보수가 용이해집니다. null safety, data class 등의 기능으로 안전하고 안정적인 코드를 작성할 수 있습니다. 표현력이 풍부하고 간결한 코드로 개발 속도를 높이고 생산성을 향상시킬 수 있습니다.
