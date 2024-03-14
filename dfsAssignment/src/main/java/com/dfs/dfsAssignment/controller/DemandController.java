package com.dfs.dfsAssignment.controller;

import com.dfs.dfsAssignment.entity.Demand;
import com.dfs.dfsAssignment.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demands")
public class DemandController {
    @Autowired
    private DemandService demandService;

    @PostMapping
    public Demand saveDemand(@RequestBody Demand demand) {
        return demandService.saveDemand(demand);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demand> getDemandById(@PathVariable Long id) {
        Optional<Demand> demand = demandService.getDemandById(id);

        return demand.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/criteria")
    public List<Demand> getDemandsByCriteria(@RequestParam(required = false) Long id,
                                             @RequestParam(required = false) String level,
                                             @RequestParam(required = false) String city,
                                             @RequestParam(required = false) String mgrName,
                                             @RequestParam(required = false) String projectName,
                                             @RequestParam(required = false) String status,
                                             @RequestParam(required = false) String skills) {
        return demandService.getDemandsByCriteria(id, level, city, mgrName, projectName, status, skills);
    }

    @PutMapping("/{demandId}/assignMember")
    public ResponseEntity<String> assignMemberToDemand(@PathVariable Long demandId) {
        // Update demand fulfillment by assigning a member to the demand
        demandService.assignMemberToDemand(demandId);
        return ResponseEntity.ok("Member assigned to demand successfully.");
    }

}
