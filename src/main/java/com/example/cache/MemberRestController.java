package com.example.cache;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.MemberDto.CreateDto;
import com.example.cache.MemberDto.MemberDetailDto;
import com.example.cache.MemberDto.UpdateDto;

@RestController
@RequestMapping("/api/members")
public class MemberRestController {

	private final MemberService memberService;

	public MemberRestController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	public ResponseEntity<MemberDetailDto> joinMember(@RequestBody CreateDto createDto) {
		return ResponseEntity.ok(memberService.joinMember(createDto));
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<MemberDetailDto> getMemberDetail(@PathVariable Long memberId) {
		return ResponseEntity.ok(memberService.getMemberDetail(memberId));
	}

	@PutMapping("/{memberId}")
	public ResponseEntity<MemberDetailDto> editMemberInfo(
		@PathVariable Long memberId,
		@RequestBody UpdateDto updateDto
	) {
		return ResponseEntity.ok(memberService.editMemberInfo(memberId, updateDto));
	}

	@DeleteMapping("/{memberId}")
	public ResponseEntity<MemberDetailDto> deleteMember(@PathVariable Long memberId) {
		return ResponseEntity.ok(memberService.deleteMember(memberId));
	}
}
