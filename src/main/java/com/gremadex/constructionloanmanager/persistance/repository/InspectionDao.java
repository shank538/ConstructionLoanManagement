package com.gremadex.constructionloanmanager.persistance.repository;

import com.gremadex.constructionloanmanager.persistance.domain.Inspection;
import org.springframework.data.repository.CrudRepository;

public interface InspectionDao  extends CrudRepository<Inspection, Long> {
}
