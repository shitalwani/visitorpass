package com.visitorpass.visitorpass.repository;

import com.visitorpass.visitorpass.entity.VisitorTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorTypeEntity,Integer> {
    // VisitorTypeEntity findByVisitorTypeId(Integer visitorTypeId);
}
