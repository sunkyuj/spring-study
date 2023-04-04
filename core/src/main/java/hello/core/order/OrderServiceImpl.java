package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 이 롬복 덕에 생성자 자동으로 만들어줌. final 붙은 필드 모아서 생성자 자동으로 만듦
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* 수정자 주입 하려면 final 없애야함
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    */

    /* lombok 있어서 생성자 알아서 만들어줌. 이놈 없앨 수 있음*/
    @Autowired // 생성자 딱 하나만 있으면 Autowired 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy)  {

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    /// test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
