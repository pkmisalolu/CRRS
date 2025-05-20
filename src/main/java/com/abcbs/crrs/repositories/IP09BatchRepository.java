package com.abcbs.crrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbs.crrs.entity.BatchPK;
import com.abcbs.crrs.entity.P09Batch;

public interface IP09BatchRepository extends JpaRepository<P09Batch, BatchPK> {

}
