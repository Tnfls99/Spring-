package hello.hellospring.Repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); //key = member id, value = member
    private static long sequence = 0L; // key generator
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long Id) {
        return Optional.ofNullable(store.get(Id)); // Optional 사용해서 null이어도 감싸서 반환 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // java lambda -> 찾아보기~~~
                .filter(member -> member.getName().equals(name)) // 슨환하면서 받아온 이름과 똑같은 객체만 반환된다.
                .findAny(); // 이름이 같은 객체가 찾아지면 반환하고 종료
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(store.values()); // 데이터를 map으로 저장하니까 리스트 생성해서 돌려주기
    }

    public void clearStore() {
        store.clear();
    }
}
