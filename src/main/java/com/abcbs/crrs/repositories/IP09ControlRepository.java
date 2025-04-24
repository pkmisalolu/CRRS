package com.abcbs.crrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbs.crrs.entity.ControlPK;
import com.abcbs.crrs.entity.P09Control;

public interface IP09ControlRepository extends JpaRepository<P09Control, ControlPK> {

}
