package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class) 해준거랑 비슷하게 동작
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /// test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
