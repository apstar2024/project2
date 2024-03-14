package com.dfs.dfsAssignment.Repository;

import com.dfs.dfsAssignment.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

@Query("SELECT m FROM Member m WHERE KEY(m.skills) IN :requiredSkills GROUP BY m.id HAVING COUNT(DISTINCT KEY(m.skills)) = :numberOfRequiredSkills")
    List<Member> findAllBySkills(
        @Param("requiredSkills") List<String> requiredSkills,
        @Param("numberOfRequiredSkills") Long numberOfRequiredSkills
);
}
