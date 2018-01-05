/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.webflux;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import sample.common.Person;
import sample.service.PersonService;

/**
 * 注解方式对外提供restful服务
 * @author masen
 *
 */

@RestController
public class WelcomeController {
	
	@Resource
	PersonService personService;

	@GetMapping("/")
	public String welcome() {
		return "Hello World";
	}
	
	@PostMapping("/body")
	public String welcome2(@RequestBody String body) {
		System.out.println("body : " + body);
		return "Hello World";
	}
	
	@GetMapping("/getPerson/{id}")
	public Mono<Person> queryPerson(@PathVariable String id) {
		System.out.println("getPerson id : " + id);
		//阻塞
//		Mono<Person> mono= Mono.just(personService.getPerson(Integer.parseInt(id)));
		//非阻塞
		Mono<Person> mono= Mono.create(cityMonoSink -> 
		cityMonoSink.success(personService.getPerson(Integer.parseInt(id))));
		System.out.println("getPerson/{id} finished ");
		return mono;
	}

}
