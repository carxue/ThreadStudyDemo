package com.thread.xue.finalfield;

import com.thread.xue.weakreference.Car;

public class TestFinal {
	private final Integer age;
	private final String name;
	private final Car car;

	public TestFinal(Integer age, String name,Car car) {
		this.age = age;
		this.name = name;
		this.car = car;
	}

	public Integer getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
	
	
	public Car getCar() {
		return car;
	}

	@Override
	public String toString() {
		return "TestFinal [age=" + age + ", name=" + name + ", car=" + car.getPrice()+":"+car.getColour() + "]";
	}

	public static void main(String[] args) {
		Car car = new Car(12.1,"red");
		TestFinal tf = new TestFinal(65,"nima",car);
		System.out.println(tf);
		car.setColour("blue");
		System.out.println(tf);
		
	}

}
