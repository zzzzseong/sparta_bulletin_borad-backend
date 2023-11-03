# Sparta bulletin board backend
내일배움캠프 Spring 3기 5주차 개인과제 스파르타 익명 게시판 서버

---
# Use-Case Diagram
![sparta_bulletin_borad_usecase.png](image%2FREADME%2Fsparta_bulletin_borad_usecase.png)

<br>

# Functional Requirements
- ✅  게시글 작성 기능
    - 사용자는 `제목`, `작성자명`, `비밀번호`, `작성 내용`, `작성일`을 가진 게시글을 저장할 수 있다.
    - 사용자는 저장된 게시글의 정보를 반환 받아 확인할 수 있다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외한다.


- ✅  선택한 게시글 조회 기능
    - 사용자는 선택한 게시글의 정보를 조회할 수 있다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외한다.


- ✅  게시글 목록 조회 기능
    - 사용자는 등록된 게시글 전체를 작성일 기준 내림차순으로 조회할 수 있다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외한다.

    
- ✅  선택한 게시글 수정 기능
    - 사용자는 선택한 게시글의 `제목`, `작성자명`, `작성 내용`을 수정할 수 있다.
        - 게시글 수정을 위해서는 `비밀번호`를 입력해야한다.
        - 게시글의 `비밀번호`와 입력한 `비밀번호`가 일치할 경우에만 수정이 가능하다.
    - 사용자는 수정된 게시글의 정보를 반환 받아 확인할 수 있다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외한다.


- ✅  선택한 게시글 삭제 기능
    - 사용자는 선택한 게시글을 삭제할 수 있다.
        - 게시글 수정을 위해서는 `비밀번호`를 입력해야한다.
        - 게시글의 `비밀번호`와 입력한 `비밀번호`가 일치할 경우에만 수정이 가능하다.


<br>

# API Specification
| Domain | Feature     |  Method  | URL                         | Request                                                                           | Response                                                                                             | Exception | Description |
|:------:|:------------|:--------:|-----------------------------|-----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|-----------|-------------|
|  Post  | 게시글 작성      |  `POST`  | /api/post                   | { title: String<br/>author: String<br/>password: String<br/>description: String } | { title: String<br/>author: String<br/>description: String<br/>createdAt: String }                   |           |             |
|  Post  | 게시글 목록 조회   |  `GET`   | /api/post                   |                                                                                   | { posts: [{<br/> postId: Long<br/>title: String<br/>author: String<br/>createdAt: String<br/>}, ...] |           |             |
|  Post  | 게시글 단건 조회   |  `GET`   | /api/post/{postId}          |                                                                                   | { title: String<br/>author: String<br/>description: String<br/>createdAt: String }                   |           |             |
|  Post  | 게시글 수정      |  `PUT`   | /api/post/{postId}          | { title: String<br/>author: String<br/>description: String }                      | { title: String<br/>author: String<br/>description: String<br/>createdAt: String }                   |           |             |
|  Post  | 게시글 삭제      | `DELETE` | /api/post/{postId}          |                                                                                   | true / false                                                                                         |           |             |
|  Post  | 게시글 비밀번호 검증 |  `POST`  | /api/post/password/{postId} | { password: String }                                                              | true / false                                                                                         |           |             |

<br>

# Entity Relationship Diagram
![sparta_bulletin_borad_erd.png](image%2FREADME%2Fsparta_bulletin_borad_erd.png)