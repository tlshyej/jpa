package orm.jpa.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import orm.jpa.shop.entity.Address;
import orm.jpa.shop.entity.Member;
import orm.jpa.shop.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;

    @Test
    void signup() {

        Member member = memberService.signup(Member.builder()
                                                    .memberName("JPA")
                                                    .address(Address.builder()
                                                            .zipcode("우편번호")
                                                            .city("도시")
                                                            .street("도로명")
                                                            .build())
                                                    .build());

        Assertions.assertEquals(member, memberRepository.findById(member.getMemberId()).orElse(null));
    }

    @Test
    void signupWithDuplicate() {

        Member member1 = memberService.signup(Member.builder()
                                                    .memberName("JPA")
                                                    .build());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            Member member2 = memberService.signup(Member.builder()
                                                        .memberName("JPA")
                                                        .build());
        });
    }

    @Test
    void findByMemberId() {
        Member member = memberService.signup(Member.builder()
                                                    .memberName("JPA")
                                                    .build());

        Member findMember = memberService.findByMemberId(member.getMemberId());

        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findMembers() {
        Member member1 = memberService.signup(Member.builder()
                                                    .memberName("JPA")
                                                    .build());

        Member member2 = memberService.signup(Member.builder()
                                                    .memberName("JPA1")
                                                    .build());

        org.assertj.core.api.Assertions.assertThat(memberService.findMembers().size()).isEqualTo(2);
    }

}