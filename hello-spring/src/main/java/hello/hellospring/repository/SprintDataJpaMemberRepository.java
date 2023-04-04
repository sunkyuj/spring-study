package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SprintDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // findAll, save 이런건 공통 클래스로 기본 제공
    @Override
    Optional<Member> findByName(String name); // 근데 이녀석은 통용되는 녀석이 아님. 공통 클래스로 제공할 수 없기 때문에 따로 만듦.
}
