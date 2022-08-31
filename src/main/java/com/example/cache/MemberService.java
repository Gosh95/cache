package com.example.cache;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cache.MemberDto.CreateDto;
import com.example.cache.MemberDto.MemberDetailDto;
import com.example.cache.MemberDto.UpdateDto;

@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final Logger log = LoggerFactory.getLogger(getClass());

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Transactional
	public MemberDetailDto joinMember(CreateDto createDto) {
		var member = Member.createMember(createDto.name(), createDto.age());

		return MemberMapper.toMemberDetailDto(memberRepository.save(member));
	}

	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "member", key = "#memberId")
	public MemberDetailDto getMemberDetail(Long memberId) {
		var retrievedMember = findMemberById(memberId);
		log.info("캐시가 적용되면 서비스를 호출하지 않는다.");

		return MemberMapper.toMemberDetailDto(retrievedMember);
	}

	@Transactional
	@CachePut(cacheNames = "member", key = "#memberId")
	public MemberDetailDto editMemberInfo(Long memberId, UpdateDto updateDto) {
		var retrievedMember = findMemberById(memberId);
		retrievedMember.editInfo(updateDto.name(), updateDto.age());

		return MemberMapper.toMemberDetailDto(retrievedMember);
	}

	@Transactional
	@CacheEvict(cacheNames = "member", key = "#memberId")
	public MemberDetailDto deleteMember(Long memberId) {
		var retrievedMember = findMemberById(memberId);
		memberRepository.delete(retrievedMember);

		return MemberMapper.toMemberDetailDto(retrievedMember);
	}

	private Member findMemberById(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("not found member"));
	}
}
