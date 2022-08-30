package com.example.cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "member_id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "name", length = 30, nullable = false)
	private String name;

	@Column(name = "age", nullable = false)
	private int age;

	protected Member() {
	}

	private Member(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public static Member createMember(String name, int age) {
		return new Member(name, age);
	}

	public void editInfo(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
