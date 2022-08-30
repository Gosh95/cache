package com.example.cache;

import com.example.cache.MemberDto.MemberDetailDto;

public class MemberMapper {

	private MemberMapper() {
	}

	public static MemberDetailDto toMemberDetailDto(Member member) {
		return new MemberDetailDto(member.getId(), member.getName(), member.getAge());
	}
}
