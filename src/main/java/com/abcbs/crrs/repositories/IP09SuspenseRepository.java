package com.abcbs.crrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbs.crrs.entity.P09Suspense;
import com.abcbs.crrs.entity.SuspensePK;

public interface IP09SuspenseRepository extends JpaRepository<P09Suspense, SuspensePK>{

}
