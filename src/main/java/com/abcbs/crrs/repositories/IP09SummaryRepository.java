package com.abcbs.crrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbs.crrs.entity.P09Summary;
import com.abcbs.crrs.entity.SummaryPK;

public interface IP09SummaryRepository extends JpaRepository<P09Summary, SummaryPK> {

}
