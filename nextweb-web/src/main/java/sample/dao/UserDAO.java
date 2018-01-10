package sample.dao;

import java.util.List;

import cn.corp.common.data.User;


//未使用动态Sql
public interface UserDAO {
	
	List<User> findAll();
	User findById(String id);
	
}
