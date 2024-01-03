package com.fleetmanagement.Api.repositories;

import com.fleetmanagement.Api.models.TaxisModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxisRepository extends JpaRepository<TaxisModels,Integer> {

}
