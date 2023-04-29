package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor // 리포지토리에서도 가능함 (스프링부트에서 PersistenceContext -> Autowired 가능 -> em 생성자 인젝션)
public class MemberRepository {
    private final EntityManager em;
    /*
    @PersistenceContext
    private EntityManager em; // em injection

    public MemberRepository(EntityManager em){
        this.em = em;
    }
    */

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // jpql, from의 대상이 Entity 객체임, Member.class는 반환 타입
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class) // jpql
                .setParameter("name", name) // 윗줄에 :name에 파라미터 name이 바인딩 됨
                .getResultList();
    }
}
