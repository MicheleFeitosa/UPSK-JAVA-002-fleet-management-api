package com.fleetmanagement.Api.repositories;

import com.fleetmanagement.Api.models.TrajectoriesModels;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrajectoriesRepository extends JpaRepository<TrajectoriesModels, Integer> {

    @Query(value = "FROM TrajectoriesModels t WHERE t.taxi.id = :taxiId")
    Page<TrajectoriesModels> findTrajectoriesByTaxiId(@Param("taxiId") String taxiId, Pageable pageable);
}