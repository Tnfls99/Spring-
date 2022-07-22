package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    // @Column(name = "username") -> DB의 name에 해당하는 컬럼이름이 username일 경우 다음과 같이 명시할 수 있다.
    private String name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
