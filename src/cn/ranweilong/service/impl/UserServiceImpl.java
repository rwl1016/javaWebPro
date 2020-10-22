package cn.ranweilong.service.impl;

import cn.ranweilong.dao.UserDao;
import cn.ranweilong.dao.impl.UserDaoImpl;
import cn.ranweilong.domain.PageBean;
import cn.ranweilong.domain.User;
import cn.ranweilong.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
//        调用dao完成查询
        return dao.findAll();
    }

    @Override
    public void addUser(User user) {

        dao.add(user);


    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserByid(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length>0){
            //        遍历数组
            for (String id : ids) {
            //        调用dao删除
                dao.delete(Integer.parseInt(id));
            }
        }

    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <=0){
            currentPage = 1;
        }


//        创建空的pageBean
        PageBean<User> pb = new PageBean<User>();
//        设置参数
        pb.setCurrentPage(currentPage);
        pb.setRow(rows);
//         调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
//        调用dao查询List集合
        int start = (currentPage-1) * rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);
//计算总页码
        int totalPage  = totalCount % rows  == 0 ? totalCount/rows :totalCount/rows +1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    public User login(User user){
        return  dao.findUseraByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
