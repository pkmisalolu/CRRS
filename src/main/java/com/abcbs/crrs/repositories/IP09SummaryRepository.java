package com.abcbs.crrs.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abcbs.crrs.entity.P09Summary;
import com.abcbs.crrs.entity.SummaryPK;
import com.abcbs.crrs.projections.ISummaryView;

import jakarta.transaction.Transactional;

public interface IP09SummaryRepository extends JpaRepository<P09Summary, SummaryPK> {
	
	@Query("SELECT s.sumProcessedDate AS sumProcessedDate, s.sumAdditionsCnt AS sumAdditionsCnt, s.sumAdditionsAmt AS sumAdditionsAmt, s.sumDeletionsCnt AS sumDeletionsCnt, s.sumDeletionsAmt AS sumDeletionsAmt, " +
		       "s.sumEndingCnt AS sumEndingCnt, s.sumEndingAmt AS sumEndingAmt FROM P09Summary s WHERE s.sId.sumLocationID = :locationNbr " +
		       "AND s.sId.sumClerkId = :clerkId")
	public	List<ISummaryView> findSummaryDetailsByLocationAndClerk(@Param("locationNbr") String locationNbr, @Param("clerkId") String clerkId);

	@Modifying
	@Transactional
	@Query("UPDATE P09Summary s SET s.sumProcessedDate = :processedDate, s.sumDeletionsCnt = :deletionsCnt, s.sumDeletionsAmt = :deletionsAmt, " +
	       "s.sumEndingCnt = :endingCnt, s.sumEndingAmt = :endingAmt WHERE s.sId.sumLocationID = :locationId AND s.sId.sumClerkId = :clerkId")
	public int updateSummaryByLocationAndClerk(@Param("processedDate") LocalDate processedDate, @Param("deletionsCnt") Integer deletionsCnt,
	    @Param("deletionsAmt") BigDecimal deletionsAmt, @Param("endingCnt") Integer endingCnt, @Param("endingAmt") BigDecimal endingAmt,
	    @Param("locationId") String locationId, @Param("clerkId") String clerkId);
	
	@Modifying
	@Transactional
	@Query("UPDATE P09Summary s SET s.sumProcessedDate = :processedDate, s.sumAdditionsCnt = :additionsCnt, s.sumAdditionsAmt = :additionsAmt, " +
	       "s.sumEndingCnt = :endingCnt, s.sumEndingAmt = :endingAmt WHERE s.sId.sumLocationID = :locationId AND s.sId.sumClerkId = :clerkId")
	public int updateSummaryAdditionsByLocationAndClerk(@Param("processedDate") LocalDate processedDate, @Param("additionsCnt") Integer additionsCnt,
	    @Param("additionsAmt") BigDecimal additionsAmt, @Param("endingCnt") Integer endingCnt, @Param("endingAmt") BigDecimal endingAmt,
	    @Param("locationId") String locationId, @Param("clerkId") String clerkId);


}
