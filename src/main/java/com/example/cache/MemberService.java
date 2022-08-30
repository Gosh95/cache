package com.example.cache;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cache.MemberDto.CreateDto;
import com.example.cache.MemberDto.MemberDetailDto;
import com.example.cache.MemberDto.UpdateDto;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Transactional
	public MemberDetailDto joinMember(CreateDto createDto) {
		var member = Member.createMember(createDto.name(), createDto.age());

		return MemberMapper.toMemberDetailDto(memberRepository.save(member));
	}

	@Transactional(readOnly = true)
	public MemberDetailDto getMemberDetail(Long memberId) {
		var retrievedMember = findMemberById(memberId);

		return MemberMapper.toMemberDetailDto(retrievedMember);
	}

	@Transactional
	public MemberDetailDto editMemberInfo(Long memberId, UpdateDto updateDto) {
		var retrievedMember = findMemberById(memberId);
		retrievedMember.editInfo(updateDto.name(), updateDto.age());

		return MemberMapper.toMemberDetailDto(retrievedMember);
	}

	@Transactional
	public MemberDetailDto deleteMember(Long memberId) {
		var retrievedMember = findMemberById(memberId);
		memberRepository.delete(retrievedMember);

		return MemberMapper.toMemberDetailDto(retrievedMember);
	}

	private Member findMemberById(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("not found member"));
	}
}
