package cn.ranweilong.web.servlet;

import cn.ranweilong.domain.User;
import cn.ranweilong.service.UserService;
import cn.ranweilong.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置编码格式
        request.setCharacterEncoding("utf-8");
//        获取参数
        Map<String, String[]> map = request.getParameterMap();
//        封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        调用Service保存
        UserService service = new UserServiceImpl();
        service.addUser(user);

//        跳转到userListService
        response.sendRedirect(request.getContextPath()+"/userListServlet");//重定向


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
