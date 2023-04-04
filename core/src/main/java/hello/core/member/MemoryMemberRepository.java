package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{


    private static Map<Long, Member> store = new HashMap<>(); // 동시성 문제 때문에 실무에서는 컨커런트 해시맵 써야함
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
