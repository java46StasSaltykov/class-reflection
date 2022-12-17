package telran.annotation.examples;

import telran.annotation.Id;
import telran.annotation.Pattern;


public class Person {

	@Id
	private long id;
	@Pattern("[A-Z][a-z]{1,10}")
	private String name;
	
	public Person(long id, String name) {
		this.id = id;
		this.name = name;
	}
}
