package com.example.citizen.repository;

import com.example.citizen.model.Citizen;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen,Integer> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "passport")
    List<Citizen> findAll();
}
