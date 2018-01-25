package sample.dao;

import java.util.List;

import cn.corp.common.data.User;


public interface UserDAO{
	
	List<User> findAll();
	User findById(String id);
	
}
