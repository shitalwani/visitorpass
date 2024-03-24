package com.visitorpass.visitorpass.repository;

import com.visitorpass.visitorpass.entity.VisitorPassRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorPassRequestRepository extends JpaRepository<VisitorPassRequestEntity,Integer> {
    List<VisitorPassRequestEntity> findByLocation(String location);
}
