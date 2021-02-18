package Dao;

import com.chen.User;

import java.util.List;

/*
用户的持久层接口
 */
public interface IUserDao {
    /*查询所有操作*/
    List<User> findAll();
    /*保存*/
    void saveUser(User user);
    /*更新*/
    void upDateUser(User user);
    /*删除*/
    void deleteUser(Integer userId);
}
