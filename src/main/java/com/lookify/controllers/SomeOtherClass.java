package com.lookify.controllers;

import org.springframework.stereotype.Component;

@Component
public class SomeOtherClass {

	public long generateRandomNumber() {
		return System.currentTimeMillis();
	}

	public static void maing(String[] args) {
		SomeOtherClass someOtherClass = new SomeOtherClass();

		System.out.println(someOtherClass.generateRandomNumber());
	}

}