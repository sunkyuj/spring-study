package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service // 얘가 있어야 스프링이 MemberService가 서비스구나 하고 알아먹음. 그래서 MemberService를 스프링 컨테이너에 등록해줌
@Transactional // jpa는 데이터 저장&변경 시 항상 트랜잭션이 있어야함
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) { // dependency injection
        this.memberRepository = memberRepository;
    }
    
    /* 회원가입 */
    public Long join(Member member) {
        // 동명이인 허용 안함
        validateDuplicateMember(member); // 중복 회원 검증, 컨트롤t 눌러서 extract method 눌러서 메소드로 추출해버림
        memberRepository.save(member);
        return member.getId();


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> { // 옵셔널이라 이거 쓸수있음. 이미 존재하면 에러 throw
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
