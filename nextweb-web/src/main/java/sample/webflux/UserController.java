package sample.webflux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.corp.common.data.User;
import cn.corp.service.spe.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	//使用自动生成sql map 的dao 实现
	@Resource(name="userServiceImpl2")
	UserService userService;
	
	@Resource(name = "asynFutureExecutor")
	private Executor asynFutureExecutor;

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
//		User user = userService.getUserById(id);
		System.out.println("queryUserDetail");
		// 修改为非阻塞方式异步调用, 20180125
		CompletableFuture<User> future = CompletableFuture.supplyAsync(() -> {
		    return userService.getUserById(id);
		}, asynFutureExecutor);
		return Mono.fromFuture(future);
	}
	
	@RequestMapping(value = "/api/users/detail2/{id}")
	public Mono<User> queryUserDetail2(@PathVariable String id) {
		// 理论上web 服务需要访问一个后台服务，这里简化为直接访问数据库
//		User user = userService.getUserById(id);
		System.out.println("queryUserDetail2");
		// 使用 异步回调的方式,由异步回调sink 决定什么时候完成，注意:sink->{} 执行这种也是异步
		Mono mono = Mono.create(sink -> {
			User user = userService.getUserById(id);
			this.logger.info("user :{}",user);
		    sink.success(user);
		});
		this.logger.info("finished callback");
		return mono;
	}

}
