package sample.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.corp.common.data.User;
import cn.corp.service.spe.UserService;
import sample.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	
	@Resource
	private UserDAO userDao;

	/**
	 *  默认的key生成策略是通过KeyGenerator生成的(如果不指定key=的话)
	 *  下面是通过Spring的EL表达式来指定我们的key
	 */

	@Override
	public List<User> getUserAll() {
		return userDao.findAll();
	}

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

}
