package com.github.dojotags.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.dojotags.test.app.config.AppConfig;
import com.github.dojotags.test.form.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class ValidatorTest {

	private static final Logger logger = LoggerFactory
			.getLogger(ValidatorTest.class);
	
	@Autowired
	private Validator validator;

	@Autowired
	private MessageSource messageSource;
	
	@Test
	public void testValidate() throws Exception {
		
		
		
		assertNotNull(validator);
		assertNotNull(messageSource);
		logger.debug("Default locale {}", Locale.getDefault());
		String message = messageSource.getMessage("com.github.dojotags.test.web.validation.name", null, Locale.getDefault());
		assertNotNull(message);
		
		logger.debug(message);
		
		
		Person p = new Person();
//		p.setFirstName("George");	
		Set<ConstraintViolation<Person>> errors = validator.validate(p);
		assertFalse(errors.isEmpty());
		for (ConstraintViolation<Person> error : errors) {
			logger.debug("{} {}", error.getPropertyPath(), error.getMessage());
		}
	}

}
