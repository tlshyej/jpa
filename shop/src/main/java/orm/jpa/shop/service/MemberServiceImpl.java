package orm.jpa.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orm.jpa.shop.entity.Member;
import orm.jpa.shop.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member signup(Member member) {
        validateNewMember(member);
        memberRepository.save(member);
        return member;
    }

    private void validateNewMember(Member member) {
        Optional<Member> findMember = memberRepository.findByMemberName(member.getMemberName());
        if (findMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    public Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
