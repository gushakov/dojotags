package com.github.dojotags.junit;

import static junit.framework.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.github.dojotags.test.web.mvvm.Page1View;
import com.github.dojotags.widgets.Input;

public class ReflectionTest {
	
	@Test
	public void viewModel() throws Exception {
		Page1View view = new Page1View();
		Input firstName1 = new Input();
		firstName1.setValue("George");
		view.setFirstName(firstName1);
		Field field = view.getClass().getDeclaredField("firstName");
		field.setAccessible(true);
		Input firstName2 = (Input) field.get(view);
		assertNotNull(firstName2);
		assertEquals("George", firstName2.getValue());
		Method method = view.getClass().getMethod("onEnter");
		assertNotNull(method);
		method.invoke(view);
	}
	
}
