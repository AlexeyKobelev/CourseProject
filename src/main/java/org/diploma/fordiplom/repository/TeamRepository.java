package org.diploma.fordiplom.repository;

import org.diploma.fordiplom.entity.ProjectEntity;
import org.diploma.fordiplom.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity,Long> {
    @Query("SELECT t FROM TeamEntity t JOIN t.emails u WHERE u.email = :email")
    List<TeamEntity> findByEmail(@Param("email") String email);
}
