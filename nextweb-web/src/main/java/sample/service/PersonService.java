package sample.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import sample.common.Person;

@Service
public class PersonService {

	public Person getPerson(int id){
//		Mono<Person> personMono = this.repository.getPerson(personId);
		System.out.println("PersonService start.");
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person person = new Person();
		person.setId(1);
		person.setName("zhangsan");
		System.out.println("PersonService finished.");
		return person;
	}
}
