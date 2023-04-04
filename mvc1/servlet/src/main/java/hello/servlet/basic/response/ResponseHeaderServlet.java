package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK);
        //[response-header]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        //[header 편의 메서드]
//        content(response);
//        cookie(response);
        redirect(response);

        //[message body]
        PrintWriter writer = response.getWriter();
        writer.write("하이");

    }

    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // Content-Length는 보통 생략
    }

    private void cookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600sec
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException{
        //status code 302
//        response.setStatus(HttpServletResponse.SC_FOUND); // 302
//        response.setHeader("Location","basic/hello-form.html");
        response.sendRedirect("basic/hello-form.html");
    }
}
