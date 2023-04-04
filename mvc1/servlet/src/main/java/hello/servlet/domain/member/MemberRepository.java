package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    // static이기 때문에 하나만 만들어짐
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }
    private MemberRepository(){ // 싱글톤으로 만들 때는, 아무나 생성하지 못하게 프라이빗 생성자를 만듦

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }

}
