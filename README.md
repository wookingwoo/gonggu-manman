## What is MUNG-$SA

- 누구나 쉽고 빠르게 공동구매장 개설, 참여가 가능한 커뮤니티 서비스
- 필요한 만큼의 물건을 보다 저렴한 가격으로 구매할 수 있는 사용자 중심의 공동구매 플랫폼 구현
- 불필요한 자원의 낭비를 줄이고 환경보존에 도움
- 1인가구의 소비 트렌드 반영

## 시연 영상
https://youtu.be/aKcDV3a9i5k

## 실행 환경 / 개발환경

- IDE: Android Studio
- Language: Java
- Emulator API Level: 29 or 30
- minSdk: 21
- targetSdk: 31

## 화면 설명

### 로그인

- 앱 접속 초기 화면
- 사용자가 입력한 Id / password가 firebase에 저장된 정보와 일치 시 메인 화면으로 이동
- 회원가입 버튼 클릭 시 회원가입 페이지로 이동

![로그인](https://user-images.githubusercontent.com/39684946/143779621-9b642ccc-c288-4ed4-b2f9-49e85961cba0.png)

### 회원가입

- 사용자가 사용하고자 하는 이메일 및 페스워드 등록
- 사용자의 기본 정보 입력
- 개인 정보 사용 동의 여부 확인

![회원가입](https://user-images.githubusercontent.com/39684946/143779631-89d8a6d8-d716-4f40-9442-12ec2b06f6b7.png)

### 홈화면

- 메인화면. 상단에 검색 기능 및 카테고리 분류
- 사용자에게 추천할 만한 상품들을 Recomendation에 표시

![홈화면](https://user-images.githubusercontent.com/39684946/143779526-978ce3aa-0500-4d63-9dff-be72a7d704f6.png)

### 검색화면

- RecyclerView 사용.
- firebase에서 데이터 받아와 현재 공구 모집 상품 나열
- 지역 품목 별 카테고리화 검색 기능 제공

![검색화면](https://user-images.githubusercontent.com/39684946/143779536-416e7010-450d-40a5-a740-cfc5c8684c36.png)

### 공구장터 생성

- 사용자가 직접 공동구매를 모집하기 위해 게시글 제시 기능 제공
- 모집인원 설정 및 품목 카테고리화, 지역 설정 가능
- 공동 구매 물품의 간략한 설명과 사진 업로드 기능 제공

![공구장터 생성](https://user-images.githubusercontent.com/39684946/143779570-305c3a3c-f623-4ce2-8513-d78fd2d9d683.png)

### 회원정보

- 현재 로그인한 사용자 자신의 정보 확인 페이지
- 로그아웃 기능 제공
- 로그아웃 후 페이지 재접근 시 안내메세지

![회원정보](https://user-images.githubusercontent.com/39684946/143779578-39a04844-7b22-4550-877b-cb8ec0bb781e.png)

### 상세 공구방

- 사용자가 메인화면 또는 검색화면에서 클릭한 상품의 상세 내용 기재
- 어떤 유저가 상품을 올렸는지, 상품에 대한 자세한 소개 확인 가능
- 공구 참여 버튼 클릭 시 현재 공구 인원 카운트 증가. (참여 페이지 구현x)

![상세 공구방](https://user-images.githubusercontent.com/39684946/143779598-6c3c0b9a-ece9-4f8c-95b1-1a066edb7294.png)

## 백엔드

- Firebase - cloud FireStore 사용

![백엔드](https://user-images.githubusercontent.com/39684946/143779673-fad637c5-bb3e-4442-8938-6efa9c1179d4.png)
