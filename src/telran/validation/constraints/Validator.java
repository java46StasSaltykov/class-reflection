package telran.validation.constraints;

import java.lang.reflect.Field;
import java.util.*;
import telran.annotation.Pattern;

public class Validator {
	
	public static List<String> validate(Object obj) {
		List<String> list = new ArrayList<String>();
		list.add(validateMin(obj));
		list.add(validateMax(obj));
		list.add(validateNotEmpty(obj));
		list.add(validatePattern(obj));
		return list;
	}

	private static String validateMin(Object obj) {
		String res = "";
		Object fieldValue = null;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Min min = field.getAnnotation(Min.class);
			if (min != null) {
				try {
					fieldValue = field.get(obj);
				} catch (Exception e) {
					return min.message();
				}
				if (fieldValue instanceof String) {
					return ((String) fieldValue).length() >= min.value() ? res : "The field is a String that is shorter than needed."; 
				} else if (fieldValue instanceof Double) {
					return (Double) fieldValue >= min.value() ? res : "The field is a number that is less than needed.";
				}
			}
		}
		return res;
	}

	private static String validateMax(Object obj) {
		String res = "";
		Object fieldValue = null;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Max max = field.getAnnotation(Max.class);
			if (max != null) {
				try {
					fieldValue = field.get(obj);
				} catch (Exception e) {
					return max.message();
				}
				if (fieldValue instanceof String) {
					return ((String) fieldValue).length() <= max.value() ? res : "The field is a String that is longer than needed."; 
				} else if (fieldValue instanceof Double) {
					return (Double) fieldValue <= max.value() ? res : "The field is a number that is greater than needed.";
				}
			}
		}
		return res;
	}

	private static String validateNotEmpty(Object obj) {
		String res = "";
		Object fieldValue = null;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
			if (notEmpty != null) {
				try {
					fieldValue = field.get(obj);
				} catch (Exception e) {
					
				}
				if (fieldValue != null) {
					if (fieldValue instanceof String) {
						return ((String) fieldValue).isEmpty() ? notEmpty.message() : res;
					}
				}
			}
		}
		return res;
	}

	private static String validatePattern(Object obj) {
		String res = "";
		Object fieldValue = null;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Pattern pattern = field.getAnnotation(Pattern.class);
			if (pattern != null) {
				try {
					fieldValue = field.get(obj);
					String regEx = pattern.value();
					if (!((String) fieldValue).matches(regEx)) {
						res += pattern.message();
					}
				} catch (Exception e) {
					
				}
			}
		}
		return res;
	}

}
