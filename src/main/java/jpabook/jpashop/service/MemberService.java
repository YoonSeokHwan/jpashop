package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /**
        회원가입
    */
    @Transactional
    public Long Join(Member member){
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

        private void validateDuplicateMember(Member member){
            //exception
            List<Member> findMembers =  memberRepository.findByName(member.getName());

            if(!findMembers.isEmpty()){
                throw new IllegalStateException("존재하는 이름");
            }
        }

    // 회원전체 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
