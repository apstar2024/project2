package com.dfs.dfsAssignment.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Map;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eid;
    private String firstName;
    private String lastName;
    private LocalDate doj;
    private String level;
    private String location;
    private int overallExp;
    private String status;

    @ElementCollection
    @MapKeyColumn(name = "skill")
    @Column(name = "experience")
    private Map<String, Integer> skills;

    // Getters and Setters

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOverallExp() {
        return overallExp;
    }

    public void setOverallExp(int overallExp) {
        this.overallExp = overallExp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }
}
