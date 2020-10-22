package cn.ranweilong.web.servlet;

import cn.ranweilong.domain.PageBean;
import cn.ranweilong.domain.User;
import cn.ranweilong.service.UserService;
import cn.ranweilong.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.IllegalFormatFlagsException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
//        获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        if(currentPage == null || "".equals(currentPage)){

            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows = "5";
        }
//获取条件查询的条件
        Map<String, String[]> condition = request.getParameterMap();

//        调用service查询
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage,rows,condition);
        System.out.println(pb);//控制台打印数据
//      将pageBean存入request
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);
        //转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
