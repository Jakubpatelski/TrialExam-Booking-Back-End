package com.example.examprep.Service;

import com.example.examprep.Entity.Member;
import com.example.examprep.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAll(){
        return memberRepository.findAll();
    }

    public void deleteById(int id){
        memberRepository.deleteById(id);
    }

 public Optional<Member> getById(int id){
    return memberRepository.findById(id);
}
    public Member addMember(Member member){
        return memberRepository.save(member);
    }

    public Member updateMember(int id, Member member){
        if (memberRepository.findById(id).isPresent()){
            return memberRepository.save(member);
        }
        return null;
    }








}
