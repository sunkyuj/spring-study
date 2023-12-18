package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

         // 세션 생성 (서버 -> 클라)
        MockHttpServletResponse response = new MockHttpServletResponse(); // HttpServletResponse는 인터페이스라 MockHttpServletResponse를 사용
        Member member = new Member();
        sessionManager.createSession(member, response);

        // 요청에 응답 쿠키 저장 (클라 -> 서버)
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies()); // mySessionId=UUID 담겨있음
        // 세션 조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);
        //세션 만료
        sessionManager.expire(request);
         Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();

    }
}
