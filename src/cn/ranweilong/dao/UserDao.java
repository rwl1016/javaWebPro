package cn.ranweilong.dao;

import cn.ranweilong.domain.User;

import java.util.List;
import java.util.Map;

//用户操作的dao
public interface UserDao {


    public List<User> findAll();

    User findUseraByUsernameAndPassword(String username, String password);

    void add(User user);

    void delete(int parseInt);

    User findById(int parseInt);

    void update(User user);

    int findTotalCount(Map<String, String[]> condition);//查询总记录数

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);//分页查询每页记录
}
