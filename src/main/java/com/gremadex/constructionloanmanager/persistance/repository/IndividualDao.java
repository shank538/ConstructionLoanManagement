package com.gremadex.constructionloanmanager.persistance.repository;

import com.gremadex.constructionloanmanager.persistance.domain.Individual;
import org.springframework.data.repository.CrudRepository;

public interface IndividualDao extends CrudRepository<Individual, Long> {
}
