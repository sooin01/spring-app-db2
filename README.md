# spring-app-db2

* 실행 명령어
  - java -jar spring-app-db2-0.0.1.jar &
  - jstack -l $! >> dump.log
  - sleep 1
  - jstack -l $! >> dump.log


- java -jar /opt/jar/spring-app-db2-0.0.1.jar &
- echo "첫번째" - $! >> pid.log

- java -jar /opt/jar/spring-app-db2-0.0.1.jar &
- echo second$! >> pid.log

- java -jar /opt/jar/spring-app-db2-0.0.1.jar &
- echo third$! >> pid.log