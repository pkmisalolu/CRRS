package com.abcbs.crrs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abcbs.crrs.entity.OptionPK;
import com.abcbs.crrs.entity.P09Option;

public interface IOptionRepository extends JpaRepository<P09Option, OptionPK> {
	//For field validation
	@Query("SELECT p FROM P09Option p WHERE p.optId.optRecordType = :recordType AND p.optFieldNarr LIKE :likeValue")
	public List<P09Option> findByRecordTypeAndNarrLike(@Param("recordType") String recordType, @Param("likeValue") String likeValue);

}
