# Trouble Shooting

---

## 2023.02.02

### 1. Lombok 과 MapStruct 사용 시 Annotation Processor 관련 이슈

***Situation***

* Lombok 을 통해 작성한 객체 간의 Mapping 을 위해 MapStruct 로 컴파일 도중 다음과 같은 메시지가 나타남

```java: No property named "id" exists in source parameter(s). Type "UserDTO" has no properties.```

***Cause***

* annotation processor 는 작성한 순서대로 실행됨. 따라서 Lombok Annotation Processor 가 MapStruct Annotation processor 보다 먼저 정의되어야 함
* 객체에 적용된 Lombok 어노테이션에 따라 생성되는 implementation 이 다름
```xml
    <path>
      <groupId>org.projectlombok</groupId> <!-- IMPORTANT - LOMBOK BEFORE MAPSTRUCT -->
      <artifactId>lombok</artifactId>
      <version>${lombok.version}</version>
    </path>
    <path>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok-mapstruct-binding</artifactId>
      <version>${lombok-mapstruct.version}</version>
    </path>
    <path>
      <groupId>org.mapstruct</groupId>
      <artifactId>mapstruct-processor</artifactId>
      <version>${org.mapstruct.version}</version>
    </path>
  ```


### 2. ModelAttribute 바인딩 관련 이슈

***Situation***

* 회원가입 POST 를 받을 때, UserDTO 의 int id 의 값이 null 로 입력받음 
  Failed to convert value of type 'null' to required type 'int' <br> 
*   org.springframework.core.convert.ConversionFailedException 발생

***Cause***

* UserDTO 의 lombok 어노테이션 관련 이슈
* @NoArgsConstructor 의 부재로 인한 문제
* 객체 바인딩에는 setter 와 default constructor 가 필요하기 때문 (Java Bean)
* @Builder 와 @NoArgsConstructor 를 같이 사용하려면, @AllConstructor 가 필요

```java
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor    
public class UserDTO {

  private int id;
  private String name;
  private String password;
  private String email;
  private String description;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;

}

```

### 3. 정적 데이터 접근을 위한 설정 변경

***Situation***

* jsp 에서 css, js 파일을 불러오지 못함

***Cause***

* 모든 Request 를 DispatcherServlet 을 통해 처리했기 때문
* WebMvcConfigurer 의 configureDefaultServletHandling 을 통해 정적 데이터에 대한 접근을
  DispatcherServlet 을 거치지 않고 접근할 수 있도록 함
  
```java
@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
```

