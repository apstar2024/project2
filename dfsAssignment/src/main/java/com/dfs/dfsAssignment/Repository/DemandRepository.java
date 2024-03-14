package com.dfs.dfsAssignment.Repository;

import com.dfs.dfsAssignment.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandRepository extends JpaRepository<Demand, Long> {
    List<Demand> findByLevelAndCityAndMgrNameAndStatusAndSkills(String level, String city, String mgrName, String status, String skills);
    @Query("SELECT d FROM Demand d WHERE (:level IS NULL OR d.level = :level) " +
            "AND (:city IS NULL OR d.city = :city) " +
            "AND (:mgrName IS NULL OR d.mgrName = :mgrName) " +
            "AND (:projectName IS NULL OR d.projectName = :projectName) " +
            "AND (:status IS NULL OR d.status = :status) " +
            "AND (:skills IS NULL OR d.skills LIKE %:skills%)")
    List<Demand> findByCriteria(@Param("level") String level,
                                @Param("city") String city,
                                @Param("mgrName") String mgrName,
                                @Param("projectName") String projectName,
                                @Param("status") String status,
                                @Param("skills") String skills);
}