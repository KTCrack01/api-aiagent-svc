# GPT API Backend

Spring Boot를 사용한 OpenAI API 연동 백엔드 서버입니다.

## 기능

- **메시지 처리**: `/api/messages/send` 엔드포인트로 일반 메시지 처리
- **ChatGPT 연동**: `/api/chat` 엔드포인트로 OpenAI GPT API 호출
- **모델 선택**: 다양한 GPT 모델 지원 (gpt-4o-mini, gpt-4o, gpt-4-turbo, gpt-3.5-turbo)
- **파라미터 조정**: maxTokens, temperature 등 모델 파라미터 조정 가능
- **모델 조회**: `/api/models` 엔드포인트로 사용 가능한 모델 목록 조회

## 설정

### 1. OpenAI API 키 설정

**방법 1: 환경변수 사용 (권장)**
```bash
# Windows PowerShell
$env:OPENAI_API_KEY="your-openai-api-key-here"

# Linux/Mac
export OPENAI_API_KEY="your-openai-api-key-here"
```

**방법 2: application.properties에 직접 설정 (개발용)**
```properties
# src/main/resources/application.properties
openai.api.key="your-openai-api-key-here"
```

### 2. 애플리케이션 실행

```bash
# 빌드
./gradlew build

# 실행
./gradlew bootRun
```

서버는 `http://localhost:3001`에서 실행됩니다.

## API 엔드포인트

### 1. 메시지 전송
```
POST /api/messages/send
Content-Type: application/json

{
  "message": "사용자 메시지",
  "userId": "사용자 ID"
}
```

응답:
```json
{
  "message": "사용자 메시지",
  "response": "처리된 응답",
  "success": true,
  "error": null
}
```

### 2. ChatGPT 프롬프트 처리
```
POST /api/chat
Content-Type: application/json

{
  "prompt": "ChatGPT에 보낼 프롬프트",
  "userId": "사용자 ID",
  "model": "gpt-4o-mini",
  "maxTokens": 1000,
  "temperature": 0.7
}
```

응답:
```json
{
  "prompt": "ChatGPT에 보낸 프롬프트",
  "response": "ChatGPT 응답",
  "success": true,
  "error": null
}
```

### 3. 사용 가능한 모델 조회
```
GET /api/models
```

응답:
```json
{
  "models": ["gpt-4o-mini", "gpt-4o", "gpt-4-turbo", "gpt-3.5-turbo"],
  "defaultModel": "gpt-4o-mini"
}
```

## 프로젝트 구조

```
src/main/java/com/example/demo/
├── DemoApplication.java          # 메인 애플리케이션 클래스
├── controller/
│   └── MessageController.java    # API 엔드포인트 컨트롤러
├── service/
│   └── OpenAIService.java        # OpenAI API 호출 서비스
└── dto/
    ├── MessageRequest.java       # 메시지 요청 DTO
    ├── MessageResponse.java      # 메시지 응답 DTO
    ├── ChatRequest.java          # ChatGPT 요청 DTO
    └── ChatResponse.java         # ChatGPT 응답 DTO
```

## 프론트엔드 연동

프론트엔드에서는 다음 환경 변수를 설정하세요:

```javascript
// .env.local 또는 환경 변수
NEXT_PUBLIC_API_BASE_URL=http://localhost:3001
```

## CORS 설정

백엔드는 모든 오리진에서의 요청을 허용하도록 설정되어 있습니다. 프로덕션 환경에서는 적절한 CORS 설정을 추가하세요.

## 로깅

애플리케이션은 DEBUG 레벨로 로깅됩니다. 로그를 확인하려면:

```bash
./gradlew bootRun --debug
```
