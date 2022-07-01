package repository;

import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        // 저장하는 것 테스트
        repository.save(member);
        // 저장한 것의 아이디로 멤버 객체 불러옴
        Member result = repository.findById(member.getId()).get();
        // 불러온 것과 내가 만든 멤버와 같은 지 확인하는 테스트
        // System.out.println("result = " + (result == member)); 실제 테스트에서 이렇게 뽑아볼 수 없다~~~ assert 기능 사용하자
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); // assertj.core에 있는 Assertions, 영한님은 이게 더 편한 것이라고 함, member가 result랑 똑같아? 라고 물어보는 것 -> Assertions를 static으로 import 가능
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("boot");
        repository.save(member2);

        Member result = repository.findByName("spring").get();

        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("boot");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
