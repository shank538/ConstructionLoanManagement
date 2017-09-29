package com.gremadex.constructionloanmanager.persistance.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.gremadex.constructionloanmanager.persistance.domain.Guideline;

import java.util.List;

public interface GuidelineDao extends CrudRepository<Guideline, Long> {

    @Query(value = "select addressid, projectName,startdate,enddate from guideline where projectName = ?1", nativeQuery = true)
    public List<Object> fetchGuideline(String projectName);
}
