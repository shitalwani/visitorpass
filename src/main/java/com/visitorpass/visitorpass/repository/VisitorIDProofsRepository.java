package com.visitorpass.visitorpass.repository;

import com.visitorpass.visitorpass.entity.VisitorIDProofs;
import com.visitorpass.visitorpass.entity.VisitorPassRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorIDProofsRepository extends JpaRepository<VisitorIDProofs,Integer> {
}
