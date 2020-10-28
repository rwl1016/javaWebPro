package cn.ranweilong.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//       强制转换
        HttpServletRequest request = (HttpServletRequest) req;

        //获取资源请求路径

        String uri = request.getRequestURI();
//        判断是否包含登录相关资源路径,要注意排除掉css/js/图片/验证码等资源
        if (uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/checkCodeServlet")){
//            包含，用户就是想登录
            chain.doFilter(req, resp);

        }else{
//            不包含，需要验证用户是否登录
//            从session中获取user
            Object user = request.getSession().getAttribute("user");
            if (user != null){
                chain.doFilter(req,resp);
            }else{
                request.setAttribute("login_msg","你尚未登录，你登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }


        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
