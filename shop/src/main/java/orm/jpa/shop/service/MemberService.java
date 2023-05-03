package orm.jpa.shop.service;

import org.springframework.stereotype.Service;
import orm.jpa.shop.entity.Member;

import java.util.List;

public interface MemberService {

    Member signup(Member member);
    Member findByMemberId(Long memberId);
    List<Member> findMembers();

}
