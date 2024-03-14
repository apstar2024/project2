package com.dfs.dfsAssignment.service;

import com.dfs.dfsAssignment.Repository.DemandRepository;
import com.dfs.dfsAssignment.Repository.MemberRepository;
import com.dfs.dfsAssignment.entity.Demand;
import com.dfs.dfsAssignment.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DemandRepository demandRepository;

    public Member addNewMember(Member member) {
        // Set member status as AVAILABLE when adding a new member
        member.setStatus("Available");

        // Save the new member to the database
        return memberRepository.save(member);
    }

    public List<Member> MembersForDemand(Long id) {
        Optional<Demand> demand=demandRepository.findById(id);
        String temp=demand.get().getSkills();
        List<String>requiredSkills= Stream.of(temp.split(","))
                .map(String::trim)
                .toList();;

        for(String item:requiredSkills){
            System.out.println(item);
        }
        System.out.println(temp);
        Long numberOfRequiredSkills= (long) requiredSkills.size();
        return memberRepository.findAllBySkills(requiredSkills,numberOfRequiredSkills);
    }
}
