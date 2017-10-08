package com.gremadex.constructionloanmanager.persistance.repository;

import com.gremadex.constructionloanmanager.persistance.domain.Address;
import com.gremadex.constructionloanmanager.persistance.domain.ConstructionPhase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConstructionPhaseDao extends CrudRepository<ConstructionPhase, Long> {

@Query(value = "select id, phasename, masterreferenceid, constructionphasenumber, status, startdate, enddate, constructioncost, revisedconstructioncost from constructionphase where phasename = ?1", nativeQuery = true)
    public List<Object> fetchConstructionPhase(String phaseName);

}
