package com.dfs.dfsAssignment.controller;

import com.dfs.dfsAssignment.entity.Member;
import com.dfs.dfsAssignment.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public Member addNewMember(@RequestBody Member member) {
        return memberService.addNewMember(member);
    }

    // to get members with particular demand id
    @GetMapping("demand/{id}")
    public List<Member>getMembersForDemand(@PathVariable Long id){
        return memberService.MembersForDemand(id);
    }
}
