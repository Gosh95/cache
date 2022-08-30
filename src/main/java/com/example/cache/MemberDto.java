package com.example.cache;

public record MemberDto() {

	public record CreateDto(String name, int age) {
	}

	public record UpdateDto(String name, int age) {
	}

	public record MemberDetailDto(Long memberId, String name, int age) {
	}
}
