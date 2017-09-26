package com.gremadex.constructionloanmanager.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import com.gremadex.constructionloanmanager.persistance.domain.Guideline;

public interface GuidelineDao extends CrudRepository<Guideline, Long> {
}
