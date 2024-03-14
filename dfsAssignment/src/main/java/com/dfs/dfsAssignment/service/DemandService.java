package com.dfs.dfsAssignment.service;

import com.dfs.dfsAssignment.Repository.DemandRepository;
import com.dfs.dfsAssignment.Repository.MemberDemandMappingRepository;
import com.dfs.dfsAssignment.Repository.MemberRepository;
import com.dfs.dfsAssignment.entity.Demand;
import com.dfs.dfsAssignment.entity.Member;
import com.dfs.dfsAssignment.entity.MemberDemandMapping;
import com.dfs.dfsAssignment.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DemandService {
    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberDemandMappingRepository memberDemandMappingRepository;
    @Autowired
    private MemberService memberService;

    public Demand saveDemand(Demand demand) {
        return demandRepository.save(demand);
    }

    public List<Demand> getDemandsByCriteria(Long id, String level, String city, String mgrName, String projectName, String status, String skills) {
        if (id != null) {
            return Collections.singletonList(demandRepository.findById(id).orElse(null));
        } else {
            return demandRepository.findByCriteria(level, city, mgrName, projectName, status, skills);
        }
    }

    public void updateDemandFulfillment(Long demandId, Long memberId) {
        // Implement logic to update demand fulfillment
    }

    public Optional<Demand> getDemandById(Long id) {
        return demandRepository.findById(id);
    }

    public void assignMemberToDemand(Long demandId) {
        // Fetch demand by ID
        Demand demand = demandRepository.findById(demandId)
                .orElseThrow(() -> new NotFoundException("Demand not found with ID: " + demandId));

        // Fetch eligible members for the demand
        List<Member> eligibleMembers = memberService.MembersForDemand(demandId);

        // If there are eligible members, assign the first member to the demand
        if (!eligibleMembers.isEmpty()) {
            MemberDemandMapping mdmap = new MemberDemandMapping();
            Member assignedMember = eligibleMembers.getFirst();
            demand.setStatus("Closed,fullfiled"); //  assigned member in Demand entity
            assignedMember.setStatus("Unavailable");
            // updates
            mdmap.setDemand(demand);
            mdmap.setMember(assignedMember);
            mdmap.setProjectName(demand.getProjectName());
            // Update demand status

            memberDemandMappingRepository.save(mdmap);
            memberRepository.save(assignedMember);
            demandRepository.save(demand); // Save the updated demand
        } else {
            // Handle scenario when no eligible members are found
            throw new NotFoundException("No eligible members found for demand with ID: " + demandId);
        }
    }
}
