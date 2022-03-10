# spring-boot-mail-filtering


This Project's Goal,Imap Mails Listening in a Spring Boot Project

---



## Technologies

- Spring Boot
- Lombok
- Spring Boot Dev Tools
- SpringDoc OpenAPI
- Javax Mail
- Apache Commons(Optional)

---

## Installation

  - First,define necessary dependencies

```java

    <dependency>
            <groupId>org.springframework.integration</groupId>
            <artifactId>spring-integration-test</artifactId>
            <scope>test</scope>
    </dependency>

   <dependency>
        <groupId>org.springframework.integration</groupId>
        <artifactId>spring-integration-feed</artifactId>
    </dependency>

    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>

    <dependency>
            <groupId>org.springframework.integration</groupId>
            <artifactId>spring-integration-mail</artifactId>
    </dependency>
```
- Second,configuring Imap or other protocols connection config


![image](https://user-images.githubusercontent.com/80245013/157603060-60564d7b-97bf-4ba9-bb5e-58e9e7fcee5f.png)

If we using "IMAPS" protocol configuring store settings

![image](https://user-images.githubusercontent.com/80245013/157603662-ab8674cb-8c38-45d8-9812-0ac9a37950ef.png)

---

- Third If connection success,We access mail Inbox
![image](https://user-images.githubusercontent.com/80245013/157603379-469b047d-f13e-487f-843a-947692d4ada6.png)



[Muharrem Koç](https://github.com/muharremkoc)

