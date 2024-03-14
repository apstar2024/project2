package com.dfs.dfsAssignment.Repository;

import com.dfs.dfsAssignment.entity.MemberDemandMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDemandMappingRepository extends JpaRepository<MemberDemandMapping, Long> {
    //custom query methods here
}
