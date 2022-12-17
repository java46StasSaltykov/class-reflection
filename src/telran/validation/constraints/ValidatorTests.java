package telran.validation.constraints;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

class ValidatorTests {

	@Test
	void test() {
		Person p1 = new Person((double) 1234567899, "", "052-1234567J");
		List<String> listOfErrors = Validator.validate(p1);
		List<String> subList = new ArrayList<>();
		listOfErrors.stream().forEach(e -> {
			if (!e.isEmpty()) {
				subList.add(e);
			}
		});
		subList.stream().forEach(e -> {
			assertFalse(e.equals(""));
		});
		
		Person p2 = new Person((double) 123456789, "Stas", "052-1234567");
		listOfErrors = Validator.validate(p2);
		listOfErrors.stream().forEach(e -> {
			assertTrue(e.equals(""));
		});
	}

}
