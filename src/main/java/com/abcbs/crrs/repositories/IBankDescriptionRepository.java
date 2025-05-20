package com.abcbs.crrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbs.crrs.entity.BankDescription;

public interface IBankDescriptionRepository extends JpaRepository<BankDescription, String> {

}
