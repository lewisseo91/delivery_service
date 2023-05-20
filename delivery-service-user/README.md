## 요구사항 목록 정리

1. 회원가입 API 구현
   - 필요한 정보 : ID, PW, name
   - 유효성 체크
     - 대문자, 소문자, 숫자, 특수문자 중 3종류 이상 12자리 이상 문자열

2. 로그인 API 구현
   - IN : ID, PW
   - OUT : ACCESS TOKEN 이 포함된 response

3. JWT 인증 구현
   - 토큰 발급
   - 유효한 토큰 확인
     - 토큰 만료 확인
     - 이메일 주소와 같은 형식 확인
   - login 시도 후 payload 발급
   - 만료 후 토큰 재발급

### 기타

1. DB 사용
2. MVC 기반 App 설계
3. 예외 케이스 AMAP
4. JWT 적용
5. Test Case 작성
6. API 명세서 