package com.abcbs.crrs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abcbs.crrs.entity.OptionPK;
import com.abcbs.crrs.entity.P09Option;
import com.abcbs.crrs.projections.ICRHelp;

public interface IOptionRepository extends JpaRepository<P09Option, OptionPK> {
	//For field validation
	@Query("SELECT p FROM P09Option p WHERE p.optId.optRecordType = :recordType AND p.optFieldNarr LIKE :likeValue")
	public List<P09Option> findByRecordTypeAndNarrLike(@Param("recordType") String recordType, @Param("likeValue") String likeValue);

	@Query("SELECT o.optFieldNarr FROM P09Option o WHERE o.optId.optRecordType = :recordType AND o.optFieldNarr LIKE :optNarr")
	public	List<String> findFieldNarrListByTypeAndNarrLike( @Param("recordType") String recordType, @Param("optNarr") String optNarr);
	
	//For Help
	@Query("SELECT p.optFieldNarr AS optFieldNarr FROM P09Option p WHERE p.optId.optRecordType = :recordType ORDER BY p.optFieldNarr")
	public List<ICRHelp> findByRecordType(@Param("recordType") String recordType);
}
