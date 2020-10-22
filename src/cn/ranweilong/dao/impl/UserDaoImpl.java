package cn.ranweilong.dao.impl;

import cn.ranweilong.dao.UserDao;
import cn.ranweilong.domain.User;
import cn.ranweilong.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public int findTotalCount(Map<String, String[]> condition) {
//        定义模板初始化sql
        String sql = "select count(*) from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
//        遍历map
        Set<String> keyset = condition.keySet();
        ArrayList<Object> params = new ArrayList<>();
        for (String key: keyset) {
//            排除分页条件参数
            if ("currentPage".equals(key) ||"rows".equals(key)){
                continue;
            }
//            获取value
           String value =  condition.get(key)[0];
//           判断value是否有值
            if (value!=null && !"".equals(value) ){
//                有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
//        遍历map
        Set<String> keyset = condition.keySet();
        ArrayList<Object> params = new ArrayList<>();
        for (String key: keyset) {
//            排除分页条件参数
            if ("currentPage".equals(key) ||"rows".equals(key)){
                continue;
            }
//            获取value
            String value =  condition.get(key)[0];
//           判断value是否有值
            if (value!=null && !"".equals(value) ){
//                有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }

//        添加分页查询
        sb.append(" limit ?,? ");
//        添加分页查询参数
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
