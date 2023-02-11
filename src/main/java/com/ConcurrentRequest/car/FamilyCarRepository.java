package com.ConcurrentRequest.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyCarRepository extends JpaRepository<FamilyCar, Long>{
}
