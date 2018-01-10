package cn.corp.service.spe;

import java.util.List;

import cn.corp.common.data.User;

public interface UserService {

	public List<User> getUserAll();

	public User getUserById(String id);
}
