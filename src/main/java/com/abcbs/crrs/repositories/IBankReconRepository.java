package com.abcbs.crrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbs.crrs.entity.BankRecon;
import com.abcbs.crrs.entity.BankReconPK;

public interface IBankReconRepository extends JpaRepository<BankRecon, BankReconPK> {

}
