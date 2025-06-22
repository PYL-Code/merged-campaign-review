# 클라우드 기반 MSA 구조 체험단 모집 및 리뷰 플랫폼 (프로토타입)

## 기술 스택
- Spring Boot 3.1.5
- JDK 17
- Gradle
- Spring Cloud Gateway (WebFlux 기반)
- Eureka Server
- H2 Database
- redis
- JavaScript (Frontend)
- Vue.js 3
- TypeScript
- Pinia (상태 관리)
- Tailwind

## 기능 소개
- 메인 화면 + 리뷰 리스트 + 리뷰 작성


## 📢 체험단 프로젝트 기능 설명
Vue 3 + Vite 기반으로 제작된 캠페인 신청 플랫폼 프로젝트입니다.   
campaign_pyl 모듈에서는 캠페인 상세 조회, 신청 등의 기능을 제공합니다.

### 📌 주요 기능
- 🔐 JWT 인증 로그인
- 📋 캠페인 목록/상세 조회
- 📝 캠페인 신청 및 신청자 정보 관리
- 📅 모집 기간, 리뷰 기간 처리
- 🚫 모집 상태(대기, 진행중, 종료)에 따른 상태 제어
- ⚠️ 비로그인 상태에서 신청 시 경고 및 로그인 페이지로 리다이렉트

### 🛠️ 기술 스택
|항목|기술|
|---|---|
|프레임워크|Vue 3 (<script setup>)|
|빌드 툴|Vite|
|라우팅|Vue Router|
|상태관리|(선택적) Vue's reactive APIs|
|인증 방식|JWT (로컬스토리지 기반)|
|통신 라이브러리|Axios|
|스타일링|Scoped CSS|

### 🗂️ 프로젝트 구조
```
frontend/
├── public/
├── src/
│   ├── assets/
│   ├── components/
│   ├── views/            # 각 페이지 컴포넌트
│   ├── router/           # vue-router 설정
│   ├── utils/            # JWT 관련 유틸 함수
│   └── App.vue
├── vite.config.js
└── package.json
```


### 📋 페이지 경로
링크를 통해 접속하지 않으면 상품 관리/주문 관리 페이지로 접근할 수 없습니다.   

#### 1. 브라우저에서 접속
기본 URL: http://localhost:5173   
또는 터미널에 표시된 URL로 접속
#### 2. 주요 화면
- 메인 페이지: http://localhost:5173/
- 로그인: http://localhost:5173/login
- 회원가입: http://localhost:5173/register
- 상품 관리: http://localhost:5173/products
- 주문 관리: http://localhost:5173/orders
- 게시판: http://localhost:5173/board
- 관리자 페이지: http://localhost:5173/admin


### ⚙️ 설치 및 실행
```bash
# 1. 프로젝트 클론
git clone https://github.com/your-username/campaign-pyl.git
cd frontend

# 2. 패키지 설치
npm install

# 3. 개발 서버 실행
npm run dev
```
💡 백엔드 API가 localhost:9876에서 동작하고 있어야 정상 작동합니다.   
