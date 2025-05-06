# 캠페인
- 상세 페이지
- 체험단 신청 페이지

# 마이 페이지
- 체험단 신청 목록

***

## 참고사항
- img 경로를 `application.properties`에서 설정하고 있습니다.   
  경로 변경 시 `application.properties`의 설정을 변경해 주세요.
  - 경로 : `C:/CampaignImg/`
- img 프록시를 `/images/sample.jpg` 형식으로 설정해 두었습니다.   
  필요시 `config` > `WebConfig.java` 파일에서 변경할 수 있습니다.

***

## 상세 페이지
- 상태(`ProgressStatus`)에 따라 화면이 달라지도록 구성하였습니다.
  - `waiting` : 신청 버튼 클릭x. 리뷰 기간 제외한 정보 화면에 보임.
  - `ongiong` : 신청할 수 있음. 리뷰 기간 제외한 정보 화면에 보임.
  - `reviewing` : 신청 버튼 클릭 x. 리뷰 작성 기간 화면에 보임.
  - `completed` : 신청 버튼 클릭 x. title과 description 제외한 정보 제거.
- 캠페인 페이지 데이터는 1~10까지 있습니다.   
  각 데이터별 상태는 `data.sql`을 참고해 주세요.
- 12시마다 상태를 갱신하는 코드를 추가해 보았습니다.   
  `scheduler` > `CampaignScheduler.java` 코드에서 확인할 수 있습니다.
- 유의사항, 기타 안내를 추가하였습니다.   
  추가하거나 수정할 내용 있다면 말씀해 주세요.


## 체험단 신청
- join 할 테이블이 많아서 연관 있는 테이블 entity를 전부 구현해 두었습니다.   
- `Application`, `Campaign`, `Users` 엔티티 구현
- 신청 화면에서 `Campaign`, `Users` 엔티티의 데이터를 불러오도록 만들었습니다.   
  추후 다른 서비스와 연결할 때 참고 부탁드립니다.
- 중복으로 신청할 수 없도록 하는 기능을 추가했습니다.
- `http://localhost:5173/campaign/apply/number` 링크로 접속 시도 시
  모집 중이지 않은 캠페인이면 캠페인 상세 페이지로 리다이렉트 되도록 하는 기능을 추가했습니다.


## 신청 목록
- 상태에 따라 버튼이 달라지도록 구성하였습니다.
  - `pending` : 신청 취소 버튼
  - `approved` : 리뷰 작성 버튼
  - `rejected` : 신청 취소 버튼
- `rejected` 상태일 경우 신청 정보가 회색으로 처리되도록 만들었습니다.
- 상태별 필터 기능을 추가했습니다.