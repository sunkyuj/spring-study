package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // JPA의 내용 변경이나 모든 것들은 기본적으로 트랜잭션 안에서 일어나야함
@RequiredArgsConstructor // final 붙은 필드만 가지고 생성자 만들어줌 (생성자로 memberRepository 주입할 수 있어서 테스트시 mock repository 넣기 편함)
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    * 회원가입
    * */
    @Transactional // readOnly = false (default)
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    /*
    * 회원 전체 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

}
