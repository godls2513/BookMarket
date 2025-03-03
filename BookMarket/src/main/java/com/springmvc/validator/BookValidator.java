package com.springmvc.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springmvc.domain.Book;

import org.springframework.beans.factory.annotation.Autowired;

public class BookValidator implements Validator {
	@Autowired
	private javax.validation.Validator beanValidator;
	
	private Set<Validator> springValidators;
	
	public BookValidator() {
		springValidators = new HashSet<Validator>();
	}

	public void setSpringValidators(Set<Validator> springValidators) {
		this.springValidators = springValidators;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Bean Validation 설정
		Set<ConstraintViolation<Object>> violations = beanValidator.validate(target);
		
		for (ConstraintViolation<Object> violation : violations) {
			// 오류 발생 필드 저장
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage(); // 오류 발생 메세지 저장
			// 오류가 발생한 필드와 메시지를 Error 객체에 저장
			errors.rejectValue(propertyPath, "", message);
		}
		
		for (Validator vlidator : springValidators) {
			vlidator.validate(target, errors); // 발생한 오류 정보를 전달
			
		}
	}
	
	

}
