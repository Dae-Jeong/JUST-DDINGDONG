# DD-API

Spring Boot 기반의 RESTful API 서버입니다.

## 🛠 기술 스택

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Security** (JWT 토큰 인증 준비)
- **Spring Data JPA** (Hibernate)
- **PostgreSQL** (데이터베이스)
- **RabbitMQ** (메시지 큐)
- **Docker & Docker Compose** (컨테이너 환경)
- **Swagger/OpenAPI 3** (API 문서화)
- **Gradle** (빌드 도구)

## 🚀 빠른 시작

### 1. 사전 요구사항

- Java 21 이상
- Docker & Docker Compose
- Git

### 2. 프로젝트 클론

```bash
git clone <repository-url>
cd dd-api
```

### 3. 환경 설정

#### Docker 환경변수 설정
```bash
cp .env.example .env
# .env 파일을 열어서 데이터베이스 및 RabbitMQ 설정을 수정하세요
```

#### Spring Boot 설정
```bash
cp application.properties.example src/main/resources/application.properties
# application.properties 파일을 열어서 설정을 수정하세요
```

### 4. 인프라 실행

```bash
# PostgreSQL & RabbitMQ 컨테이너 실행
docker-compose up -d

# 컨테이너 상태 확인
docker-compose ps
```

### 5. 애플리케이션 실행

```bash
# Gradle을 이용한 실행
./gradlew bootRun

# 또는 IDE에서 DdApiApplication.java 실행
```

## 📚 API 문서

애플리케이션 실행 후 다음 URL에서 API 문서를 확인할 수 있습니다:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## 🐳 Docker 구성

### 포트 정보
- **PostgreSQL**: 5432
- **RabbitMQ AMQP**: 5672
- **RabbitMQ Management**: 15672 (http://localhost:15672)
- **Spring Boot**: 8080

### 컨테이너 관리
```bash
# 컨테이너 시작
docker-compose up -d

# 컨테이너 중지
docker-compose down

# 로그 확인
docker-compose logs -f [service-name]

# 데이터베이스 접속
docker exec -it dd-api-postgres psql -U [username] -d ddapi
```

## 🔧 개발 환경 설정

### 1. IDE 설정
- Java 21 설정
- Lombok 플러그인 설치
- Spring Boot 지원 플러그인 설치

### 2. 코드 스타일
- JavaDoc을 사용한 문서화
- 인라인 주석 지양, Docstring 사용
- 일관된 람다 표현식 사용

### 3. 보안 설정
- CORS: 개발환경에서는 localhost 허용
- CSRF: API 서버 특성상 비활성화
- Session: Stateless (JWT 토큰 준비)

## 🔒 보안 고려사항

### 환경변수 관리
- `.env` 파일: 실제 환경변수 (Git 제외)
- `.env.example`: 환경변수 템플릿 (Git 포함)
- `application.properties`: 실제 설정 (Git 제외)
- `application.properties.example`: 설정 템플릿 (Git 포함)

### 접근 제한
- Swagger UI: 인증 없이 접근 가능 (개발용)
- 헬스체크: `/actuator/health` 공개
- 나머지 API: 인증 필요

## 📁 프로젝트 구조

```
dd-api/
├── src/main/java/just/ddingdong/
│   ├── config/              # 설정 클래스
│   │   ├── CorsConfig.java
│   │   └── SecurityConfig.java
│   └── DdApiApplication.java
├── src/main/resources/
│   └── application.properties
├── docker-compose.yml       # 인프라 컨테이너 정의
├── .env                     # 환경변수 (Git 제외)
├── .env.example             # 환경변수 템플릿
├── application.properties.example  # 설정 템플릿
└── README.md
```

## 🚦 상태 확인

### 헬스체크
```bash
curl http://localhost:8080/actuator/health
```

### 데이터베이스 연결 확인
```bash
docker exec dd-api-postgres psql -U [username] -d ddapi -c "SELECT version();"
```

### RabbitMQ 관리 UI
http://localhost:15672 (username/password는 .env 파일 참조)

## 🤝 기여하기

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 라이선스

이 프로젝트는 [MIT 라이선스](LICENSE)를 따릅니다.

## 📞 지원

문제가 발생하거나 질문이 있으시면 [Issues](../../issues)에 등록해주세요.