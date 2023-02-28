# Skills

---

***이 페이지에서는 프로젝트에 왜 이러한 기술 또는 기능을 도입했는지에 대한 나름의 설명을 적고자 한다(까먹을까봐)***

## JNDI 를 통한 DataSource 설정

스프링 설정 관련 책을 보다보면 대부분 코드 내부에 DataSource 관련 설정을 입력하는 것을 볼 수 있었다. <br>
이러한 설정이 보안에 취약하거나 DataSource 관련 설정을 변경해야할 때 다시 컴파일해야 하는 문제가 발생하지 않을까 하는 마음에 다른 방법을 찾아보게 되었다. <br>
그 과정에서 Java Name and Directory Interface 를 알게 되었고 이를 적용해보고자 하였다.

## final keyword 를 통한 readonly parameter

* Reference Type 에 final 을 붙이면, 객체의 field 는 수정할 수 있지만, Reference 변수가 가리키는 객체의 주소는 불변함
* 메소드 내에서 객체의 상태가 변하는 것을 막을 수 있음
