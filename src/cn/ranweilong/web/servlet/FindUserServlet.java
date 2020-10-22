package cn.ranweilong.web.servlet;

import cn.ranweilong.domain.User;
import cn.ranweilong.service.UserService;
import cn.ranweilong.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*获取id*/
        String id = request.getParameter("id");
//        调用service查询
        UserService service = new UserServiceImpl();
        User user = service.findUserByid(id);


//       将user存入request
          request.setAttribute("user",user);
//          转发到updata.jsp中
        request.getRequestDispatcher("/update.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
