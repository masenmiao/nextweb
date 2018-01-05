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

import reactor.core.publisher.Mono;
import sample.common.Person;

import java.util.concurrent.Callable;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.http.MediaType.APPLICATION_JSON;
@Component
public class EchoHandler {

	public Mono<ServerResponse> echo(ServerRequest request) {
		
		System.out.println("server receive:");
//		System.out.println("request :" +request.pathVariable("abc"));
		//param http://localhost:8080/echo?abc=aaa
//		System.out.println("request :" +request.queryParam("abc"));
		System.out.println("meno:"+request.bodyToMono(String.class).getClass().getName());
		request.bodyToMono(String.class).flatMap(v -> {System.out.println("abc:"+v);
		return Mono.just(v);})
		.subscribe(v -> System.out.println(v),
				v -> System.out.println(v),
				() ->  System.out.println("request.bodyToMono.complete"));
		//request.bodyToMono(String.class) stream的 结束未执行，和  MonoOnErrorResume 类有关
		
		Mono<String> testMono = Mono.just("Hello World Mono");
		System.out.println("testMono class: " + testMono.getClass().getName());
		testMono.flatMap(v -> {System.out.println("flatMap Mono:"+v);return Mono.just(v);})
		.subscribe(v -> System.out.println("Hello World Mono 1: "+v),
				v -> System.out.println("Hello World Mono 2: " +v),
				() ->  System.out.println("Hello World Mono 3"));
		
		testMono.flatMap(v -> {System.out.println("flatMap Mono2:"+v);return Mono.just(v);})
		.subscribe(v -> System.out.println("Hello World Mono 1: "+v),
				v -> System.out.println("Hello World Mono 2: " +v),
				() ->  System.out.println("Hello World Mono 3"));

		//怎么取值

//		request.bodyToMono(String.class).doOnEach(System.out::println);
		System.out.println("..............");
		
//		ServerResponse.ok().body(request.bodyToMono(String.class), String.class);
		Mono<ServerResponse> monoRsp =  ServerResponse.ok().body(Mono.just("Hello World Client"), String.class);
		System.out.println("monoRsp:"+monoRsp.getClass().getName());
		monoRsp.flatMap(v -> {System.out.println("monoRsp:"+ v);return Mono.just(v);})
		.subscribe(v -> System.out.println("monoRsp" + v));
		return monoRsp;
	}
	
	
	public Mono<ServerResponse> queryPerson(ServerRequest request) {
		String id = request.pathVariable("id");
		System.out.println("id :" +  id);
		WebClient webClient = WebClient.create("http://localhost:8080");
		 return webClient
		            .get()
		            .uri("/getPerson/" + id)
		            .accept(MediaType.APPLICATION_JSON)
		            .exchange()
		            .flatMap(response -> {
		            	System.out.println(response);
		            	return ServerResponse.ok().body(response.bodyToMono(Person.class),Person.class);
//		            	return ServerResponse.ok().body(fromObject(new Person(1,"李四")));
//		            	return 	ServerResponse.ok().body(fromObject("Hello World"));
		            }
		            );
	}
	
	
	public static void main(String[] args) {
		Callable<Void> call = () -> {System.out.println("hello");return null; };
		try {
			call.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
