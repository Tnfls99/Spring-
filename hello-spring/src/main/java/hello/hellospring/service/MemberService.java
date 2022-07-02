package hello.hellospring.service;

import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
// 서비스 로직 구현에는 서비스 관련 용어를 많이 사용해야 한다.
public class MemberService {
    //private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private final MemoryMemberRepository memberRepository;

    // memberRepository를 외부에서 주입하도록 만듦 -> deqendency injection = DI
    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원은 안된다.
        /* Optional<Member> result = memberRepository.findByName(member.getName());
        // Optional이기 때문에 값이 있다면 예외처리를 할 수 있음
        // Optional은 get으로 꺼내오는 것을 권장하지 않는다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        // 어차피 코드가 Optional이라는 것을 자동으로 알아채기 때문에 한줄로 코드 변형이 가능하다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
