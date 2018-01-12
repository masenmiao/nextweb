package sample.webflux;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.corp.common.data.User;
import cn.corp.service.spe.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	@Resource
	UserService userService;

	// 调用一个restTemplate.getForObject eureka 服务
	@RequestMapping(value = "/api/users")  //api/users/ 也路由到这(id为空时)
	public Flux<User> queryUserAll() {
		// 理论上web 服务需要访问一个后台服务，这里简化为直接访问数据库
		// return WebClient.builder().baseUrl("http://" +
		// SERVICE_NAME).filter(lbFunction).build()
		// .get().uri("/user").accept(MediaType.APPLICATION_JSON).exchange().flatMapMany(response
		// -> {
		// return response.bodyToFlux(User.class);
		// });

		System.out.println("queryUserAll");
		return this.queryUserById(null);
	}

	//返回前端table 数据，需要是一个 数组结构
	@RequestMapping(value = "/api/users/{id}")
	public Flux<User> queryUserById(@PathVariable String id) {
		// 理论上web 服务需要访问一个后台服务，这里简化为直接访问数据库
		System.out.println("queryUserById");
		List<User> users = null;
		
		if(StringUtils.isEmpty(id)){
			users = (List<User>) userService.getUserAll();
		}else{
			User user = userService.getUserById(id);
			if(user != null){
				users = new ArrayList<>();
				users.add(user);
			}
		}
		if(users != null){
			return Flux.fromStream(users.stream());
		}else{
			return null;
		}
		
	}
	
	//返回 弹出页面的 单个数据结构
	@RequestMapping(value = "/api/users/detail/{id}")
	public Mono<User> queryUserDetail(@PathVariable String id) {
		// 理论上web 服务需要访问一个后台服务，这里简化为直接访问数据库
		System.out.println("queryUserDetail");
		User user = userService.getUserById(id);
		return Mono.just(user);
	}

}
