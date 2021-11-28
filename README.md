# gonggu-manman

1. 앱소개
- 누구나 쉽고 빠르게 공동구매장 개설, 참여가 가능한 커뮤니티 서비스
- 필요한 만큼의 물건을 보다 저렴한 가격으로 구매할 수 있는
사용자 중심의 공동구매 플랫폼 구현
- 불필요한 자원의 낭비를 줄이고 환경보존에 도움
- 1인가구의 소비 트렌드 반영

1. 실행 환경 / 개발환경
- java
- 애뮬레이터 API레벨 :  29 or 30
- 지원되는 안드로이드 최소 버전

    - minSdk 21
    - targetSdk 31

1. 화면 설명 (스샷)
- 로그인

    앱 접속 초기 화면

    사용자가 입력한 Id / password가 firebase에 저장된 정보와 일치 시 메인 화면으로 이동

    회원가입 버튼 클릭 시 회원가입 페이지로 이동

    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0de6fa03-1677-4d3c-8c4f-1b99b6f45dfd/Untitled.png)


- 회원가입

    사용자가 사용하고자 하는 이메일 및 페스워드 등록

    사용자의 기본 정보 입력

    개인 정보 사용 동의 여부 확인

    ![회원가입.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a7ec40a8-0209-4215-8cdb-842301766991/회원가입.png)


- 홈화면

    메인화면. 상단에 검색 기능 및 카테고리 분류

    사용자에게 추천할 만한 상품들을 Recomendation에 표시

    ![홈화면.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4b9ae6f5-7f13-4496-96fb-53a3accf6760/홈화면.png)


- 검색화면

    RecyclerView 사용.

    firebase에서 데이터 받아와 현재 공구 모집 상품 나열

    지역 품목 별 카테고리화 검색 기능 제공

    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0c756ea9-bc4a-4f78-8777-68b8c211622b/Untitled.png)


- 공구장터 생성

    사용자가 직접 공동구매를 모집하기 위해 게시글 제시 기능 제공

    모집인원 설정 및 품목 카테고리화, 지역 설정 가능

    공동 구매 물품의 간략한 설명과 사진 업로드 기능 제공

    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1afdf241-342f-4cdd-bd31-cb19adafa291/Untitled.png)


- 회원정보

    현재 로그인한 사용자 자신의 정보 확인 페이지

    로그아웃 기능 제공

    로그아웃 후 페이지 재접근 시 안내메세지

    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/eb1e057e-6e0a-45d1-a368-a449126c3594/Untitled.png)


- 상세 공구방

    사용자가 메인화면 또는 검색화면에서 클릭한 상품의 상세 내용 기재

    어떤 유저가 상품을 올렸는지, 상품에 대한 자세한 소개 확인 가능

    공구 참여 버튼 클릭 시 현재 공구 인원 카운트 증가. (참여 페이지 구현x)

    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a3b31df6-3dc4-4132-8d73-dcfffc7b3f03/Untitled.png)


4. 백엔드

Firebase - cloud FireStore 사용

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5334237e-d71f-41de-9006-65f6d316b4a5/Untitled.png)