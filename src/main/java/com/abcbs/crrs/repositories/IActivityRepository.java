package com.abcbs.crrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbs.crrs.entity.ActivityPK;
import com.abcbs.crrs.entity.P09Activity;

public interface IActivityRepository extends JpaRepository<P09Activity, ActivityPK> {

	
}
