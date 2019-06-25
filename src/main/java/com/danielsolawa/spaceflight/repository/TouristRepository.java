package com.danielsolawa.spaceflight.repository;

import com.danielsolawa.spaceflight.domain.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, Long> {
}
