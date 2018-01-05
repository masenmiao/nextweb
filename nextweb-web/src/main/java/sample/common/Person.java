package sample.common;

public class Person {

	public Person() {
		// TODO Auto-generated constructor stub
	}
	public Person(int id,String name) {
		this.id=id;
		this.name=name;
	}
	int id;
	String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private void toSTRING() {
		// TODO Auto-generated method stub
		System.out.println("Person : " + this.getId() + " : " + this.getName());
	}
}
