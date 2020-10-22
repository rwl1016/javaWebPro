package cn.ranweilong.dao.impl;

import cn.ranweilong.dao.UserDao;
import cn.ranweilong.domain.User;
import cn.ranweilong.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
/*
查询所有数据
 */
    @Override
    public List<User> findAll() {
//        使用jdbc来操作数据库
        //1.定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

/*
登录判断
 */
    @Override
    public User findUseraByUsernameAndPassword(String username,String password){
        try {
            String sql  = "select * from user where username = ? and password =?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /*
        插入数据
    */
    @Override
    public void add(User user) {
//        定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
//        执行sql
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());

    }
    /*
    *删除数据
    * */
    @Override
    public void delete(int id) {
//        定义sql
        String sql = "delete from user where id=?";
//        执行sql
        template.update(sql,id);
    }

    @Override
    public User findById(int id) {
//        编写sql语句
        String sql = "select * from user where id= ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void update(User user) {
//        编写sql
        String sql = "update user set name =?,gender =?,age=?,address=?,qq=?,email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());

    }

    @Override
    public int findTotalCount() {
        String sql = "select count(*) from user";

        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<User> findByPage(int start, int rows) {
        String sql = "select * from user limit ? ,?";

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),start,rows);
    }
}
