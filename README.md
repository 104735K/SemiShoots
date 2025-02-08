## ⚽️ [SEMI] 풋살 매치 사이트 Shoots 

<img width="1680" alt="Image" src="https://github.com/user-attachments/assets/7906b2bb-6b20-4562-ad97-57549525d4a0" />


## 💻 PROJECT INTRODUCTION
- 목적
  - 일회성 매치시스템과 커뮤니티를 함께 구현하여 취미 축구인들의 네트워크 구축
  - 기존 풋살장 예약 페이지의 복잡한 UI, 예약 페이지와 커뮤니티 분리로 인한 소통의 불편함을 해소하기 위해 웹 사이트를 간결하고 직관적으로 변경
  - 사용자에게 편의성을 제공함과 동시에 하나의 웹 사이트에 매치기능과 커뮤니티를 함께 구현해 취미 축구인들의 네트워크를 구축

- 기능
  - 원하는 장소, 날짜, 시간에 맞는 매치를 조회 및 신청
  - 자유게시판을 통해 자유로운 소통 및 중고게시판으로 쉽고 간편한 중고거래
  - 사용자 문의게시판, FAQ, 공지사항 등 정보 제공

## 🗓️ DEVELOPMENT PERIOD
2024.10 - 2025.11

## 👩🏻‍💻🧑🏻‍💻TEAM
|이름|역할|
|:------:|---------------|
|강성현 [조장]|메인페이지, 기업페이지, 사용자 마이페이지, 결제API, 리스너 사용 자동환불, 매출데이터 엑셀 다운, 화면 설계|
|김동휘|사용자 (로그인, 회원가입), 이메일 인증, 지도API|
|임현빈|자유게시판, 중고 게시판, 댓글 및 대댓글, pagination|
|최영수|고객편의(문의), 신고기능, 필터링|
|최주경|고객편의(공지사항, FAQ), 관리자 페이지|

## ⚙️ DEVELOPMENT ENVIRONMENT
- Programming Language : Java 17
- Framework : JSP/Servlet
- Database : Oracle11 
- Front : HTML/CSS, JavaScript, JSTL, Bootstrap
- Tooling/ DevOps : Eclipse, GitHub, Postman ..
- Environment : MacOS, Window10
- Etc : Figma, Notion

## 💡 FEATURE
> <h3>강성현 주요 기능</h3>
### 💳 결제 API 연동

- 아임포트 API를 활용하여 결제 및 환불 프로세스 구현

### 🔄 자동 환불

- 매치시간에 따른 매치 최소인원 미달로 인한 자동환불 처리
- WebListener + Timer객체 이용 각 경기 시간과 현재 시간을 비교하여 최소 인원 미달의 경우 해당 경기 환불 처리 API 호출

---
### [로직 기능 구현 과정]

### 💳 결제 및 자동환불
- 설계의 주안점
  - 결제 시스템의 API 호출 시 데이터 무결성
  - 자동으로 매치 조건에 따른 환불이 시행되어야 함
  - Spring의 Scheduler 대신 사용할 Timer 객체 활용
  - WebListener와 Timer의 사용 / Timer 단독 사용?
    - 웹 애플리케이션이 종료되거나 리로드되면 타이머가 중지되는 문제
    - 서버의 라이프 사이클에 종속적이지 않아 타이머 작업의 관리의 어려움이 있을 것으로 예상
  
- 설계
  - @WebListener + Timer 객체 사용
    - 애플리케이션의 생명 주기와 타이머 작업 연동
    - 서버 재시작이나 종료 시 자동으로 타이머 종료되므로 리소스 관리 용이
    - WebListener를 통해 애플리케이션의 초기화 및 종료 시점을 정확하게 제어
  - Listner : 웹 애플리케이션에서 특정 이벤트가 발생할 때 이를 처리, 서블릿 컨테이너에서 이벤트 감지, 해당 이벤트가 발생했을 때 특정 작업 수행하도록 정의
  


## 📑 DEMO
[화면설계 - 일부]
|                               메인 페이지                               |                                매치 리스트 페이지                               |                               매치 상세 페이지                               |
| :---------------------------------------------------------------------: | :---------------------------------------------------------------------: |:---------------------------------------------------------------------: |
| <img width="1680" alt="Image" src="https://github.com/user-attachments/assets/7906b2bb-6b20-4562-ad97-57549525d4a0" /> | <img width="1680" alt="Image" src="https://github.com/user-attachments/assets/635a6625-d5cb-4fbe-9f21-748d5835cbf7" />|<img width="1680" alt="Image" src="https://github.com/user-attachments/assets/834eab8c-45ee-4c8e-8b18-35717dfb0aae" />|

|                               기업 페이지                               |                                기업 매출 페이지                                 |                              기업 매치 페이지                               |
| :---------------------------------------------------------------------: | :---------------------------------------------------------------------: | :---------------------------------------------------------------------: |
| <img width="1678" alt="Image" src="https://github.com/user-attachments/assets/3ab1e693-5cb0-4a5b-9b0b-c9bc85f938f8" />| <img width="1659" alt="Image" src="https://github.com/user-attachments/assets/ad580ecb-f26f-45d5-953e-6816ea53e922" />| <img width="1658" alt="Image" src="https://github.com/user-attachments/assets/d7f2087e-8845-4667-ab87-0e96aaea6789" />|

|                               사용자 마이페이지                               |                                사용자 참여 매치                                 |                              사용자 플레이어 신고                               |
| :---------------------------------------------------------------------: | :---------------------------------------------------------------------: | :---------------------------------------------------------------------: |
| <img width="1680" alt="Image" src="https://github.com/user-attachments/assets/2bb83711-72d5-4efe-8c08-1f6530d30f57" />| <img width="1680" alt="Image" src="https://github.com/user-attachments/assets/1c6a29d7-e551-4632-a491-8a38aaa601b6" />| <img width="1672" alt="Image" src="https://github.com/user-attachments/assets/35a042e6-df3f-47b3-bddb-ac84947b0322" />|


## 📂 PROJECT STRUCTURE
```
src
    ├── main
    │   ├── java
    │   │   └── net
    │   │       ├── admin
    │   │       │   └── action
    │   │       │       ├── AdminApproveAction.java
    │   │       │       ├── AdminBusinessApprovalAction.java
    │   │       │       ├── ...
    │   │       ├── business
    │   │       │   └── action
    │   │       │       ├── BusinessCustomersAction.java
    │   │       │       ├── BusinessFrontController.java
    │   │       │       ├── ...
    │   │       ├── comment
    │   │       │   ├── action
    │   │       │   │   ├── CommentAddAction.java
    │   │       │   │   ├── CommentDeleteAction.java
    │   │       │   │   ├── ...
    │   │       │   └── db
    │   │       │       ├── CommentBean.java
    │   │       │       └── CommentDAO.java
    │   │       ├── core
    │   │       │   ├── Action.java
    │   │       │   └── ActionForward.java
    │   │       ├── customer
    │   │       │   └── action
    │   │       ├── faq
    │   │       │   ├── action
    │   │       │   └── db
    │   │       ├── filter
    │   │       ├── inquiry
    │   │       │   ├── action
    │   │       │   └── db
    │   │       ├── inquiryComment
    │   │       │   ├── action
    │   │       │   └── db
    │   │       ├── match
    │   │       │   ├── action
    │   │       │   └── db
    │   │       ├── notice
    │   │       │   ├── action
    │   │       │   └── db
    │   │       ├── pay
    │   │       │   ├── action
    │   │       │   ├── db
    │   │       │   └── listner
    │   │       │       ├── AppContextListener.java
    │   │       │       └── AutoRefundScheduler.java
    │   │       ├── post
    │   │       │   ├── action
    │   │       │   └── db
    │   │       ├── report
    │   │       │   ├── action
    │   │       │   └── db
    │   │       └── user
    │   │           ├── action
    │   │           └── db
    │   └── webapp
    │       ├── META-INF
    │       │   ├── MANIFEST.MF
    │       │   └── context.xml
    │       ├── WEB-INF
    │       │   ├── lib
    │       │   │   ├── activation-1.1.jar
    │       │   │   ├── commons-collections4-4.4.jar
    │       │   │   ├── commons-compress-1.21.jar
    │       │   │   ├── commons-io-2.11.0.jar
    │       │   │   ├── commons-math3-3.6.1.jar
    │       │   │   ├── cos2024.jar
    │       │   │   ├── gson-2.9.0.jar
    │       │   │   ├── jakarta.servlet.jsp.jstl-3.0.1.jar
    │       │   │   ├── jakarta.servlet.jsp.jstl-api-3.0.0.jar
    │       │   │   ├── log4j-api-2.18.0.jar
    │       │   │   ├── mail-1.4.7.jar
    │       │   │   ├── ojdbc6.jar
    │       │   │   ├── poi-5.2.3.jar
    │       │   │   ├── poi-ooxml-5.2.3.jar
    │       │   │   ├── poi-ooxml-lite-5.2.3.jar
    │       │   │   └── xmlbeans-5.1.1.jar
    │       │   ├── views
    │       │   │   ├── admin
    │       │   │   │   ├── adminBusinessApprovalList.jsp
    │       │   │   │   ├── adminBusinessList.jsp
    │       │   │   │   ├── ...
    │       │   │   ├── business
    │       │   │   ├── customer
    │       │   │   ├── faq
    │       │   │   ├── inquiry
    │       │   │   ├── match
    │       │   │   ├── notice
    │       │   │   ├── pay
    │       │   │   ├── post
    │       │   │   ├── report
    │       │   │   └── user
    │       │   └── web.xml
    │       ├── css
    │       ├── error
    │       ├── faqupload
    │       ├── img
    │       ├── index.jsp
    │       ├── inquiryupload
    │       ├── js
    │       ├── noticeupload
    │       ├── sql
    │       └── userupload
    ├── post.cld
    └── post_comment.cld

```
