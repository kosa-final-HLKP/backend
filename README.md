- Spring Boot 설치
  
- MySQL 설치 및 스키마 제작

- Spring Boot의 Spring Initializr 에서 2차시 웹 페이지 쇼핑몰 책의 기본 Spring Initializr 설정을 기반으로 Initializr 설정완료

- spring boot 기능 구현 과정에서 Redis 사용 
https://pamyferret.tistory.com/9 (설치 참고 글)
  
- Spring Boot 의 application.properties 설정 완료 (Sping Boot - MySQL 연동 완료), 기본 엔티티 코드를 Spring Boot 로만 구성하였기 때문에 설치 MySQL과의 연동만 마치면 됨
  # MySQL Database Configuration
-spring.h2.console.enabled=true

-spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver 

-spring.datasource.username=root 

-spring.datasource.password=user

-spring.datasource.url=jdbc:mysql://localhost:3306/주소입력


.

- AI_BACK 연동 Flask 연동 코드 깃에 추가로 업로드 예정

- git push가 되지 않는 상황이여서 우선 공유 드라이브에 AI - BACK 연동 코드도 같이 첨부해두었습니다.
- 모델 파일에서 main.py와 같은 위치에 파일을 두면 됩니다.
- vscode에서 모델 실행 시, flask 를 vscode 내에서 설치해야합니다.
- flask 가상환경을 만든 다음에 모델은 가상환경에서, 연동 flask 코드는 로컬환경에서 실행시키면 됩니다.

- 모델 실행 시 사용해야 하는 명령어 / 가상환경 실행 후, 모델 파일 위치로 돌아가서 python main.py 로 실행 

- flask 연동 코드 실행 시 사용해야 하는 명령어 / 모델이 위치한 환경에서 python ai_back.py 로 실행 
  
