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

## 합치면서 수정한 내용
- vite.config.ts 파일을 수정하였습니다.   
  ```typescript
  '/api/mypage': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        secure: false,
      },
  ```
- main.ts 파일을 수정했습니다.   
  기본 포트번호 설정 부분을 삭제하였습니다.   
  해당 부분에 주석 처리로 표시해 두었습니다.