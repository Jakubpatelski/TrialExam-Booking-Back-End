package com.example.examprep.Controller;

import com.example.examprep.Entity.Member;
import com.example.examprep.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
   public List<Member> getAll(){
        return memberService.getAll();
    }




    @GetMapping("/{id}")
    public ResponseEntity<Member> findMemberById(@PathVariable int id){
        Optional<Member> member = memberService.getById(id);
        if (member.isPresent()){
            return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Member editMember(@PathVariable int id, @RequestBody Member member){
       return memberService.updateMember(id, member);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable int id) {
        try {
            memberService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }







}


