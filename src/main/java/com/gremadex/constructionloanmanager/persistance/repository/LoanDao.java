package com.gremadex.constructionloanmanager.persistance.repository;

import com.gremadex.constructionloanmanager.persistance.domain.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanDao extends CrudRepository<Loan, Long> {
}
