package com.gremadex.constructionloanmanager.persistance.repository;

import com.gremadex.constructionloanmanager.persistance.domain.Address;
import com.gremadex.constructionloanmanager.persistance.domain.ConstructionPhase;
import org.springframework.data.repository.CrudRepository;

public interface ConstructionPhaseDao extends CrudRepository<ConstructionPhase, Long> {


}
