package telran.validation.constraints;

import telran.annotation.Pattern;

public class Person {

	@Min(value = 1) @Max(value = 999999999) @NotEmpty
	private Double id;
	
	@Pattern("[A-Z][a-z]{1,10}") @NotEmpty
	private String name;
	
	@Min(10) @Max(14) @Pattern("^((\\+|00)?972\\-?|0)(([23489]|[57]\\d)\\-?\\d{7})$")
	private String phoneNumber;
	
	public Person(Double id, String name, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
}
