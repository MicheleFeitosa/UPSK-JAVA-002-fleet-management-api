package com.fleetmanagement.Api.repositories;

import com.fleetmanagement.Api.models.TrajectoriesModels;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajectoriesRepository extends JpaRepository<TrajectoriesModels, Integer> {

    @Query(value = "FROM TrajectoriesModels t WHERE t.taxi.id = :taxiId")
    Page<TrajectoriesModels> findTrajectoriesByTaxiId(@Param("taxiId") String taxiId, Pageable pageable);

    @Query(value = "SELECT t.taxi.id, t.latitude, t.longitude, t.date " +
            "FROM TrajectoriesModels t " +
            "WHERE t.id = (SELECT MAX(j.id) FROM TrajectoriesModels j WHERE j.taxi.id = t.taxi.id)"
    )
    Page<TrajectoriesModels> findLastLocation( Pageable pageable);
}