package com.abcbs.crrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbs.crrs.entity.CheckControl;
import com.abcbs.crrs.entity.CheckControlPK;

public interface ICheckControlRepository extends JpaRepository<CheckControl, CheckControlPK> {

}
