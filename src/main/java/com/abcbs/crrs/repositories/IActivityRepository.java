package com.abcbs.crrs.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abcbs.crrs.entity.ActivityPK;
import com.abcbs.crrs.entity.P09Activity;
import com.abcbs.crrs.projections.IActivityView;

import jakarta.transaction.Transactional;

public interface IActivityRepository extends JpaRepository<P09Activity, ActivityPK> {

	//ACTCUR cursor
	@Query("SELECT a.aId.crRefundType AS crRefundType, a.aId.crCntrlDate AS crCntrlDate, a.aId.crCntrlNbr AS crCntrlNbr, " +
			"a.actActivityAmt AS actActivityAmt, a.actXrefNbr AS actXrefNbr, a.actArrsCode AS actArrsCode FROM P09Activity a " +
			"WHERE a.aId.crRefundType = :refType AND a.aId.crCntrlDate = :cntrlDate AND a.aId.crCntrlNbr = :cntrlNbr " +
			"AND a.aId.actActivity = 'RAA' AND a.actActivityAmt = :activityAmt AND a.actArrsCode = ' '")
	public	List<IActivityView> findRAAActDetails(@Param("refType") String refType, @Param("cntrlDate") LocalDate cntrlDate, @Param("cntrlNbr") String cntrlNbr, @Param("activityAmt") BigDecimal activityAmt);

	//doubt
	@Query("SELECT SUM(a.actActivityAmt) FROM P09Activity a WHERE a.aId.crRefundType = :refType AND a.aId.crCntrlDate = :cntrlDate " +
			"AND a.aId.crCntrlNbr = :cntrlNbr AND (     (a.aId.actActivityDate = :xrefDate AND a.aId.actActivity = :xrefNbr) " +
			"    OR (a.actXrefNbr LIKE :likeXrefNbr AND a.actXrefDate = :xrefDate) ) AND a.actProcessedInd <> 'P'")
	public	BigDecimal findXrefActSum(@Param("refType") String refType, @Param("cntrlDate") LocalDate cntrlDate, @Param("cntrlNbr") String cntrlNbr,
			@Param("xrefDate") LocalDate xrefDate, @Param("xrefNbr") String xrefNbr, @Param("likeXrefNbr") String likeXrefNbr);

	@Query("SELECT COUNT(a) FROM P09Activity a WHERE a.aId.crRefundType = :refundType AND a.aId.crCntrlDate = :cntrlDate " +
			"AND a.aId.crCntrlNbr = :cntrlNbr AND a.actProcessedInd = 'P'")
	public	long countProcessedActByControl( @Param("refundType") String refundType, @Param("cntrlDate") LocalDate cntrlDate, @Param("cntrlNbr") String cntrlNbr);

	@Modifying
	@Transactional
	@Query("UPDATE P09Activity a SET a.actProcessedInd = :processedInd WHERE a.aId.crRefundType = :refundType AND a.aId.crCntrlDate = :cntrlDate " +
			"AND a.aId.crCntrlNbr = :cntrlNbr AND a.aId.actActivity IN ('PRR', 'FRR') AND a.actProcessedInd = ' '")
	public int updateProcessedIndForPRRAndFRR(@Param("processedInd") String processedInd, @Param("refundType") String refundType, @Param("cntrlDate") LocalDate cntrlDate, @Param("cntrlNbr") String cntrlNbr);

	//doubt
	@Query("SELECT SUM(a.actActivityAmt) FROM P09Activity a WHERE a.aId.crRefundType = :refundType AND a.aId.crCntrlDate = :cntrlDate " +
			"AND a.aId.crCntrlNbr = :cntrlNbr AND a.aId.actActivityDate = :activityDate AND a.aId.actActivity = :activity AND a.actProcessedInd <> 'P'")
	public	BigDecimal sumUnprocessedActAmt(@Param("refundType") String refundType, @Param("cntrlDate") LocalDate cntrlDate, @Param("cntrlNbr") String cntrlNbr,
			@Param("activityDate") LocalDate activityDate, @Param("activity") String activity);

	@Query("SELECT a.actWorkingBal FROM P09Activity a WHERE a.aId.crRefundType = :refundType AND a.aId.crCntrlDate = :cntrlDate " +
			"AND a.aId.crCntrlNbr = :cntrlNbr AND a.aId.actTimestamp = (SELECT MAX(a2.aId.actTimestamp) FROM P09Activity a2 " +
			"WHERE a2.aId.crRefundType = :refundType AND a2.aId.crCntrlDate = :cntrlDate AND a2.aId.crCntrlNbr = :cntrlNbr " +
			"AND a2.actWorkingBal IS NOT NULL)")
	public List<BigDecimal> findLatestWorkingBalList(@Param("refundType") String refundType, @Param("cntrlDate") LocalDate cntrlDate,
			@Param("cntrlNbr") String cntrlNbr);

	@Modifying
	@Transactional
	@Query("UPDATE P09Activity a SET a.actArrsCode = 'D' WHERE a.aId.crRefundType = :refundType AND a.aId.crCntrlDate = :cntrlDate " +
	       "AND a.aId.crCntrlNbr = :cntrlNbr AND a.aId.actActivity = 'RAA' AND a.actActivityAmt = :activityAmt AND a.actXrefNbr = :xrefNbr")
	public int markArrsCodeAsD(@Param("refundType") String refundType, @Param("cntrlDate") LocalDate cntrlDate, @Param("cntrlNbr") String cntrlNbr,
	    @Param("activityAmt") BigDecimal activityAmt, @Param("xrefNbr") String xrefNbr);
	
	@Modifying
	@Transactional
	@Query("UPDATE P09Activity a SET a.actArrsCode = :arrsCode WHERE a.aId.crRefundType = :refundType AND a.aId.crCntrlDate = :cntrlDate " +
	       "AND a.aId.crCntrlNbr = :cntrlNbr AND a.aId.actActivity = 'EST'")
	public int updateArrsCodeForEST(@Param("arrsCode") String arrsCode, @Param("refundType") String refundType, @Param("cntrlDate") LocalDate cntrlDate, @Param("cntrlNbr") String cntrlNbr);
}
