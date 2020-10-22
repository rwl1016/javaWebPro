package cn.ranweilong.service;

import cn.ranweilong.domain.PageBean;
import cn.ranweilong.domain.User;

import java.util.ArrayList;
import java.util.List;

/*
*用户管理的业务接口
* */
public interface UserService {
        /*
        * 查询所有用户信息
        * */
    public List<User> findAll();
//保存user
    void addUser(User user);
//根据id删除
    void deleteUser(String id);
//根据id查询
    User findUserByid(String id);

    void updateUser(User user);

    void delSelectedUser(String[] ids);

    PageBean<User> findUserByPage(String currentPage, String rows);
}
