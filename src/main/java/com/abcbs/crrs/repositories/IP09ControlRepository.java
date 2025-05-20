package com.abcbs.crrs.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abcbs.crrs.entity.ControlPK;
import com.abcbs.crrs.entity.P09Control;
import com.abcbs.crrs.projections.IControlFileView;

import jakarta.transaction.Transactional;

public interface IP09ControlRepository extends JpaRepository<P09Control, ControlPK> {
	
	@Query("SELECT c.cntrlToDate AS cntrlToDate FROM P09Control c WHERE c.controlId.cntrlRefundType = :refundType AND c.controlId.cntrlOpenInd = 'O'")
	public	List<LocalDate> findOpenControlToDate(@Param("refundType") String refundType);

	
	@Query("SELECT c.controlId.cntrlOpenInd AS cntrlOpenInd, c.controlId.cntrlFromDate AS cntrlFromDate, c.cntrlToDate AS cntrlToDate, c.cntrlReceiptCnt AS cntrlReceiptCnt, c.cntrlReceiptAmt AS cntrlReceiptAmt " +
		       "FROM P09Control c WHERE c.controlId.cntrlRefundType = :refundType AND c.controlId.cntrlOpenInd = 'O'")
	public	IControlFileView fecthWithRefundType(@Param("refundType") String refundType);

	@Modifying
	@Transactional
	@Query("UPDATE P09Control pc SET pc.cntrlReceiptCnt = :refundCount,pc.cntrlReceiptAmt = :refundAmount WHERE pc.controlId.cntrlRefundType = :refundType AND pc.controlId.cntrlOpenInd = 'O' ")
	int updateWithRefundType(@Param("refundType") String refundType,@Param("refundCount") Integer refundCount,@Param("refundAmount") BigDecimal refundAmount);

}
