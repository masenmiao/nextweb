package sample.webflux;



import java.util.List;


import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.corp.common.data.User;
import cn.corp.service.spe.UserService;
import reactor.core.publisher.Flux;



@RestController
public class UserController {

	@Resource
	UserService userService;
	

	// 调用一个restTemplate.getForObject eureka 服务
	@RequestMapping(value = "/api/users")
	public Flux<User> queryUserAll() {
		//	理论上web 服务需要访问一个后台服务，这里简化为直接访问数据库
//		return WebClient.builder().baseUrl("http://" + SERVICE_NAME).filter(lbFunction).build()
//		.get().uri("/user").accept(MediaType.APPLICATION_JSON).exchange().flatMapMany(response -> {
//			return response.bodyToFlux(User.class);
//		});
		
		List<User> users = (List<User>) userService.getUserAll();
		
		return Flux.fromStream(users.stream());
	}

//	@RequestMapping(value = "/api/users/id")
//	public Flux<User> queryUserAll() {
//		//	理论上web 服务需要访问一个后台服务，这里简化为直接访问数据库
////		return WebClient.builder().baseUrl("http://" + SERVICE_NAME).filter(lbFunction).build()
////		.get().uri("/user").accept(MediaType.APPLICATION_JSON).exchange().flatMapMany(response -> {
////			return response.bodyToFlux(User.class);
////		});
//		
//		List<User> users = (List<User>) userService.getUserById(id);
//		
//		return Flux.fromStream(users.stream());
//	}
	
	
}
