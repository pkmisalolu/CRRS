package com.abcbs.crrs.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.abcbs.crrs.entity.CashReceiptPK;
import com.abcbs.crrs.entity.P09CashReceipt;
import com.abcbs.crrs.projections.ICRSuperView;



public interface IP09CashReceiptRepository extends JpaRepository<P09CashReceipt, CashReceiptPK> {

	//FULLKEY cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType  FROM P09CashReceipt c WHERE c.crId.crRefundType = :refundType AND c.crId.crCntrlDate = :controlDate " +
			"AND c.crId.crCntrlNbr >= :minControlNbr AND c.crId.crCntrlNbr <= :maxControlNbr ORDER BY c.crId.crCntrlNbr")
	public List<ICRSuperView> findCashReceipts( @Param("refundType") String refundType,@Param("controlDate") LocalDate controlDate,
			@Param("minControlNbr") String minControlVal, @Param("maxControlNbr") String maxControlVal) ;

	//FULLKEY_DESC cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType  FROM P09CashReceipt c WHERE c.crId.crRefundType = :refundType AND c.crId.crCntrlDate = :controlDate " +
			"AND c.crId.crCntrlNbr >= :minControlNbr AND c.crId.crCntrlNbr <= :maxControlNbr ORDER BY c.crId.crCntrlNbr DESC")
	public List<ICRSuperView> findCashReceiptsOrderByNbr( @Param("refundType") String refundType,@Param("controlDate") LocalDate controlDate,
			@Param("minControlNbr") String minControlVal, @Param("maxControlNbr") String maxControlVal);

	//RECEQSTATEQ cursor
	/*
	 * @Query("SELECT new com.abcbs.crrs.bindings.CashReceiptQT2Binding(c.crId.crRefundType, c.crId.crCntrlDate, c.crId.crCntrlNbr, c.crStatusText, c.crCntrldAmt, c.crReceiptType)   FROM P09CashReceipt c WHERE c.crId.crRefundType = :refundType AND "
	 * +
	 * "c.crReceiptType = :receiptType AND c.crStatusText = :status ORDER BY c.crId.crCntrlDate , c.crId.crCntrlNbr "
	 * ) public List<CashReceiptQT2Binding>
	 * findCashReceiptsByDtCntlNbr( @Param("refundType") String
	 * refundType, @Param("receiptType") String receiptType, @Param("status") String
	 * status) ;
	 */
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType   FROM P09CashReceipt c WHERE c.crId.crRefundType = :refundType AND "
			+ "c.crReceiptType = :receiptType AND c.crStatusText = :status ORDER BY c.crId.crCntrlDate , c.crId.crCntrlNbr ")
	public List<ICRSuperView> findCashReceiptsByDtCntlNbr( @Param("refundType") String refundType, @Param("receiptType") String receiptType, @Param("status") String status) ;

	//RECEQSTATEQ_DESC cursor
	@Query("SELECT  c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType FROM P09CashReceipt c WHERE c.crId.crRefundType = :refundType AND "
			+"c.crReceiptType = :receiptType AND c.crStatusText = :status ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr ") 
	public List<ICRSuperView> findCashReceiptsByDtCntlNbrDesc( @Param("refundType") String refundType, @Param("receiptType") String receiptType, @Param("status") String status) ;


	//CHKNUMEQ cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crCheckNbr = :checkNum ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndCheckNum( @Param("refType") String refType, @Param("checkNum") String checkNum) ;

	//CHKNUMEQ_DESC cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crCheckNbr = :checkNum ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndCheckNumDesc( @Param("refType") String refType, @Param("checkNum") String checkNum) ;

	//CHKNUMBTW cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crCheckNbr >= :chkNumLv " +
			"AND c.crCheckNbr <= :chkNumHv ORDER BY c.crId.crCntrlDate, c.crCheckNbr, c.crId.crCntrlNbr")
	public List<ICRSuperView> fetchCashReceipts(@Param("refType") String refType, @Param("chkNumLv") String chkNumLv, @Param("chkNumHv") String chkNumHv) ;

	//CHKNUMBTW_DESC cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crCheckNbr >= :chkNumLv " +
			"AND c.crCheckNbr <= :chkNumHv ORDER BY c.crId.crCntrlDate DESC, c.crCheckNbr, c.crId.crCntrlNbr")
	public List<ICRSuperView> fetchCashReceiptsByCntrlDtDesc(@Param("refType") String refType, @Param("chkNumLv") String chkNumLv, @Param("chkNumHv") String chkNumHv) ;

	//CDATEBTW cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType FROM P09CashReceipt c "
			+ "WHERE c.crId.crRefundType = :refType AND c.crId.crCntrlDate = :controlDateStart  " +
			"ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public List<ICRSuperView> findByRefTypeAndCntrlDtRange(@Param("refType") String refType, @Param("controlDateStart") LocalDate controlDateStart) ;

	//CDATEBTW_DESC cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType FROM P09CashReceipt c "
			+ "WHERE c.crId.crRefundType = :refType AND c.crId.crCntrlDate = :controlDateStart  " +
			"ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public List<ICRSuperView> findByRefTypeAndCntrlDtRangeDesc(@Param("refType") String refType, @Param("controlDateStart") LocalDate controlDateStart) ;

	//EDATEBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crEntryDate = :entryDateStart " +
			" ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public List<ICRSuperView> fetchByRefTypeAndEntryDtRange( @Param("refType") String refType, @Param("entryDateStart") LocalDate entryDateStart) ;

	//EDATEBTW_DESC cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crEntryDate = :entryDateStart " +
			" ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public List<ICRSuperView> fetchByRefTypeAndEntryDtRangeDesc( @Param("refType") String refType, @Param("entryDateStart") LocalDate entryDateStart) ;

	//LOCBOTHEQ cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crStatusText = :statusText "
			+ "AND c.crLocationNbr = :locationNbr AND c.crLocationClerk = :locationClerk ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndLoc(@Param("refType") String refType,@Param("statusText") String statusText,
			@Param("locationNbr") String locationNbr,@Param("locationClerk") String locationClerk) ;

	//LOCBOTHEQ_DESC cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crStatusText = :statusText "
			+ "AND c.crLocationNbr = :locationNbr AND c.crLocationClerk = :locationClerk ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndLocDesc(@Param("refType") String refType,@Param("statusText") String statusText,
			@Param("locationNbr") String locationNbr,@Param("locationClerk") String locationClerk) ;

	//LOCNUMEQ cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crStatusText = :statusText " +
			"AND c.crLocationNbr = :locationNbr ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByRefTypeStatusAndLoc(@Param("refType") String refType, @Param("statusText") String statusText, @Param("locationNbr") String locationNbr) ;

	//LOCNUMEQ_DESC cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crStatusText = :statusText " +
			"AND c.crLocationNbr = :locationNbr ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByRefTypeStatusAndLocDesc(@Param("refType") String refType, @Param("statusText") String statusText, @Param("locationNbr") String locationNbr) ;

	//LOCCLERKEQ cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crStatusText = :statusText " +
			"AND c.crLocationClerk = :locationClerk ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByRefTypeStatusAndClerk(@Param("refType") String refType, @Param("statusText") String statusText, @Param("locationClerk") String locationClerk) ;

	//LOCCLERKEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crStatusText = :statusText " +
			"AND c.crLocationClerk = :locationClerk ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByRefTypeStatusAndClerkDesc(@Param("refType") String refType, @Param("statusText") String statusText, @Param("locationClerk") String locationClerk) ;

	//PROVNUMEQ cursor  
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crProviderNbr = :providerNbr " +
			"ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndProv(@Param("refType") String refType, @Param("providerNbr") String providerNbr) ;

	//PROVNUMEQ_DESC cursor  
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crProviderNbr = :providerNbr " +
			"ORDER BY c.crId.crCntrlDate Desc, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndProvDesc(@Param("refType") String refType, @Param("providerNbr") String providerNbr) ;

	//PROVNUMBTW cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crProviderNbr >= :providerLow " +
			"AND c.crProviderNbr <= :providerHigh ORDER BY c.crProviderNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndProvRange(@Param("refType") String refType, @Param("providerLow") String providerLow, @Param("providerHigh") String providerHigh) ;

	//PROVNUMBTW_DESC cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crProviderNbr >= :providerLow " +
			"AND c.crProviderNbr <= :providerHigh ORDER BY c.crProviderNbr, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndProvRangeDesc(@Param("refType") String refType, @Param("providerLow") String providerLow, @Param("providerHigh") String providerHigh) ;

	//NPIEQ cursor
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.nationalIdNbr = :npi " +
			"ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndNpi(@Param("refType") String refType, @Param("npi") String npi) ;

	//NPIEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.nationalIdNbr = :npi " +
			"ORDER BY c.crId.crCntrlDate Desc, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndNpiDesc(@Param("refType") String refType, @Param("npi") String npi) ;

	//NPIBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.nationalIdNbr >= :npiLow AND c.nationalIdNbr <= :npiHigh " +
			"ORDER BY c.nationalIdNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndNpiRange(@Param("refType") String refType, @Param("npiLow") String npiLow, @Param("npiHigh") String npiHigh) ;

	//NPIBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
			"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.nationalIdNbr >= :npiLow AND c.nationalIdNbr <= :npiHigh " +
			"ORDER BY c.nationalIdNbr, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndNpiRangeDesc(@Param("refType") String refType, @Param("npiLow") String npiLow, @Param("npiHigh") String npiHigh) ;

	//MBRNUMEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crMbrIdNbr = :mbrIdNbr " +
			"ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndMemberId( @Param("refType") String refType,  @Param("mbrIdNbr") String mbrIdNbr) ;

	//MBRNUMEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crMbrIdNbr = :mbrIdNbr " +
			"ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndMemberIdDesc( @Param("refType") String refType,  @Param("mbrIdNbr") String mbrIdNbr) ;

	//MBRNUMBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crMbrIdNbr >= :mbrLow AND c.crMbrIdNbr <= :mbrHigh ORDER BY c.crMbrIdNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndMbrRange( @Param("refType") String refType, @Param("mbrLow") String mbrLow, @Param("mbrHigh") String mbrHigh) ;

	//MBRNUMBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crMbrIdNbr >= :mbrLow AND c.crMbrIdNbr <= :mbrHigh ORDER BY c.crMbrIdNbr, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndMbrRangeDesc( @Param("refType") String refType, @Param("mbrLow") String mbrLow, @Param("mbrHigh") String mbrHigh) ;

	//TAXNUMEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crTaxIdNbr = :taxIdNbr " +
			"ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndTaxId( @Param("refType") String refType, @Param("taxIdNbr") String taxIdNbr) ;

	//TAXNUMEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crTaxIdNbr = :taxIdNbr " +
			"ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndTaxIdDesc( @Param("refType") String refType, @Param("taxIdNbr") String taxIdNbr) ;

	//TAXNUMBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crTaxIdNbr >= :taxIdLow AND c.crTaxIdNbr <= :taxIdHigh ORDER BY c.crTaxIdNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndTaxIdRange( @Param("refType") String refType, @Param("taxIdLow") String taxIdLow, @Param("taxIdHigh") String taxIdHigh) ;

	//TAXNUMBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crTaxIdNbr >= :taxIdLow AND c.crTaxIdNbr <= :taxIdHigh ORDER BY c.crTaxIdNbr, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndTaxIdRangeDesc( @Param("refType") String refType, @Param("taxIdLow") String taxIdLow, @Param("taxIdHigh") String taxIdHigh) ;

	//REMNAMEEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crRemittorName = :remName ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndRemName( @Param("refType") String refType, @Param("remName") String remName) ;

	//REMNAMEEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crRemittorName = :remName ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndRemNameDesc( @Param("refType") String refType, @Param("remName") String remName) ;

	//REMNAMEBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crRemittorName >= :remNameLow " +
			"AND c.crRemittorName <= :remNameHigh ORDER BY c.crRemittorName, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndRemRange( @Param("refType") String refType,@Param("remNameLow") String remNameLow,@Param("remNameHigh") String remNameHigh) ;

	//REMNAMEBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crRemittorName >= :remNameLow " +
			"AND c.crRemittorName <= :remNameHigh ORDER BY c.crRemittorName, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndRemRangeDesc( @Param("refType") String refType,@Param("remNameLow") String remNameLow,@Param("remNameHigh") String remNameHigh) ;

	//PATLNAMEEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crPatientLname = :patLname ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndPatLname(@Param("refType") String refType, @Param("patLname") String patLname) ;

	//PATLNAMEEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crPatientLname = :patLname ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndPatLnameDesc(@Param("refType") String refType, @Param("patLname") String patLname) ;

	//PATLNAMEBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crPatientLname >= :patLnameLow AND c.crPatientLname <= :patLnameHigh ORDER BY c.crPatientLname, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndPatLnameRange(@Param("refType") String refType,@Param("patLnameLow") String patLnameLow, @Param("patLnameHigh") String patLnameHigh) ;

	//PATLNAMEBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crPatientLname >= :patLnameLow AND c.crPatientLname <= :patLnameHigh ORDER BY c.crPatientLname, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndPatLnameRangeDesc(@Param("refType") String refType,@Param("patLnameLow") String patLnameLow, @Param("patLnameHigh") String patLnameHigh) ;

	//PATBNAMEEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crPatientLname = :patLname AND c.crPatientFname >= :patFnameLow AND c.crPatientFname <= :patFnameHigh " +
			"ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndFullNameRange(@Param("refType") String refType, @Param("patLname") String patLname,@Param("patFnameLow") String patFnameLow,@Param("patFnameHigh") String patFnameHigh) ;

	//PATBNAMEEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crPatientLname = :patLname AND c.crPatientFname >= :patFnameLow AND c.crPatientFname <= :patFnameHigh " +
			"ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndFullNameRangeDesc(@Param("refType") String refType, @Param("patLname") String patLname,@Param("patFnameLow") String patFnameLow,@Param("patFnameHigh") String patFnameHigh) ;

	//PATBNAMEBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crPatientLname >= :patLnameLow AND c.crPatientLname <= :patLnameHigh AND c.crPatientFname >= :patFnameLow " +
			"AND c.crPatientFname <= :patFnameHigh ORDER BY c.crPatientLname, c.crPatientFname, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndFullNameBwn( @Param("refType") String refType,@Param("patLnameLow") String patLnameLow,
			@Param("patLnameHigh") String patLnameHigh,@Param("patFnameLow") String patFnameLow, @Param("patFnameHigh") String patFnameHigh) ;


	//PATBNAMEBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crPatientLname >= :patLnameLow AND c.crPatientLname <= :patLnameHigh AND c.crPatientFname >= :patFnameLow " +
			"AND c.crPatientFname <= :patFnameHigh ORDER BY c.crPatientLname, c.crPatientFname, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeAndFullNameBwnDesc( @Param("refType") String refType,@Param("patLnameLow") String patLnameLow,
			@Param("patLnameHigh") String patLnameHigh,@Param("patFnameLow") String patFnameLow, @Param("patFnameHigh") String patFnameHigh) ;

	//PROVSTATEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crStatusText = :status AND c.crProviderNbr = :providerNbr ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndProv(@Param("refType") String refType, @Param("status") String status, @Param("providerNbr") String providerNbr) ;

	//PROVSTATEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crStatusText = :status AND c.crProviderNbr = :providerNbr ORDER BY c.crId.crCntrlDate Desc, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndProvDesc(@Param("refType") String refType, @Param("status") String status, @Param("providerNbr") String providerNbr) ;

	//PROVSTATBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crStatusText = :status AND c.crProviderNbr >= :provLow AND c.crProviderNbr <= :provHigh " +
			"ORDER BY c.crProviderNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndProvRange(@Param("refType") String refType, @Param("status") String status, @Param("provLow") String provLow, @Param("provHigh") String provHigh) ;

	//PROVSTATBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crStatusText = :status AND c.crProviderNbr >= :provLow AND c.crProviderNbr <= :provHigh " +
			"ORDER BY c.crProviderNbr, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndProvRangeDesc(@Param("refType") String refType, @Param("status") String status, @Param("provLow") String provLow, @Param("provHigh") String provHigh) ;

	//NPISTATEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.nationalIdNbr = :npi ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatAndNpi(@Param("refType") String refType, @Param("status") String status, @Param("npi") String npi) ;

	//NPISTATEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.nationalIdNbr = :npi ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatAndNpiDesc(@Param("refType") String refType, @Param("status") String status, @Param("npi") String npi) ;

	//NPISTATBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.nationalIdNbr >= :npiLow AND c.nationalIdNbr <= :npiHigh " +
			"ORDER BY c.nationalIdNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatAndNpiRange(@Param("refType") String refType, @Param("status") String status, @Param("npiLow") String npiLow, @Param("npiHigh") String npiHigh) ;

	//NPISTATBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.nationalIdNbr >= :npiLow AND c.nationalIdNbr <= :npiHigh " +
			"ORDER BY c.nationalIdNbr, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatAndNpiRangeDesc(@Param("refType") String refType, @Param("status") String status, @Param("npiLow") String npiLow, @Param("npiHigh") String npiHigh) ;

	//MBRSTATEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.crMbrIdNbr = :mbrIdNbr ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndMbrId(@Param("refType") String refType, @Param("status") String status, @Param("mbrIdNbr") String mbrIdNbr) ;

	//MBRSTATEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.crMbrIdNbr = :mbrIdNbr ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndMbrIdDesc(@Param("refType") String refType, @Param("status") String status, @Param("mbrIdNbr") String mbrIdNbr) ;

	//MBRSTATBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.crMbrIdNbr >= :mbrLow " +
			"AND c.crMbrIdNbr <= :mbrHigh ORDER BY c.crMbrIdNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndMbrRange(@Param("refType") String refType, @Param("status") String status, @Param("mbrLow") String mbrLow, @Param("mbrHigh") String mbrHigh) ;

	//MBRSTATBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.crMbrIdNbr >= :mbrLow " +
			"AND c.crMbrIdNbr <= :mbrHigh ORDER BY c.crMbrIdNbr, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> findByRefTypeStatusAndMbrRangeDesc(@Param("refType") String refType, @Param("status") String status, @Param("mbrLow") String mbrLow, @Param("mbrHigh") String mbrHigh) ;

	//PROVRAA Joins
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND " +
			"c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr WHERE c.crId.crRefundType = :refType " +
			"AND c.crStatusText = :status AND c.crProviderNbr = :providerNbr " +
			"AND a.aId.actActivity = :activity AND a.actProcessedInd <> 'P' ORDER BY c.crId.crRefundType, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public List<ICRSuperView> fetchCRWithAct(@Param("refType") String refType, @Param("status") String status, @Param("providerNbr") String providerNbr, @Param("activity") String activity) ;

	//PROVRAA_DESC 
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND " +
			"c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr WHERE c.crId.crRefundType = :refType " +
			"AND c.crStatusText = :status AND c.crProviderNbr = :providerNbr " +
			"AND a.aId.actActivity = :activity AND a.actProcessedInd <> 'P' ORDER BY c.crId.crRefundType, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public List<ICRSuperView> fetchCRWithActDesc(@Param("refType") String refType, @Param("status") String status, @Param("providerNbr") String providerNbr, @Param("activity") String activity) ;

	//PROVRAABTW
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c "
			+ "JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.crProviderNbr >= :provNumLow AND c.crProviderNbr <= :provNumHigh AND a.aId.actActivity = :activity "
			+ "AND a.actProcessedInd <> 'P' ORDER BY c.crProviderNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public List<ICRSuperView> fetchCRByProvRange( @Param("refType") String refType,@Param("status") String status, @Param("provNumLow") String provNumLow, @Param("provNumHigh") String provNumHigh, @Param("activity") String activity) ;

	/*
	 * //PROVRAABTW_DESC
	 * 
	 * @Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, "
	 * +"c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crProviderNbr AS crProviderNbr FROM P09CashReceipt c "
	 * +
	 * "JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr "
	 * +
	 * "WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.crProviderNbr >= :provNumLow AND c.crProviderNbr <= :provNumHigh AND a.aId.actActivity = :activity "
	 * +
	 * "AND a.actProcessedInd <> 'P' ORDER BY c.crProviderNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr"
	 * ) public List<ICRView10> fetchCRByProvRangeDesc( @Param("refType") String
	 * refType,@Param("status") String status, @Param("provNumLow") String
	 * provNumLow, @Param("provNumHigh") String provNumHigh, @Param("activity")
	 * String activity) ;
	 */
	//NPIRAA
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND " +
			"c.crId.crCntrlNbr = a.aId.crCntrlNbr WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.nationalIdNbr = :npi " +
			"AND a.aId.actActivity = :activity AND a.actProcessedInd <> 'P' ORDER BY c.crId.crRefundType, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchCRByNpiAct(@Param("refType") String refType,@Param("status") String status,@Param("npi") String nationalIdNbr,@Param("activity") String activity) ;

	//NPIRAA_DESC
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND " +
			"c.crId.crCntrlNbr = a.aId.crCntrlNbr WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.nationalIdNbr = :npi " +
			"AND a.aId.actActivity = :activity AND a.actProcessedInd <> 'P' ORDER BY c.crId.crRefundType, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchCRByNpiActDesc(@Param("refType") String refType,@Param("status") String status,@Param("npi") String nationalIdNbr,@Param("activity") String activity) ;

	//NPIRAABTW
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND " +
			"c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr WHERE c.crId.crRefundType = :refType " +
			"AND c.crStatusText = :status AND c.nationalIdNbr >= :npiLow AND c.nationalIdNbr <= :npiHigh AND a.aId.actActivity = :activity " +
			"AND a.actProcessedInd <> 'P' ORDER BY c.nationalIdNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchCRByNpiRangeAct( @Param("refType") String refType,@Param("status") String status,@Param("npiLow") String npiLow,@Param("npiHigh") String npiHigh,@Param("activity") String activity) ;

	/*
	 * @Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, "
	 * +
	 * "c.crCntrldAmt AS crCntrldAmt, c.nationalIdNbr AS nationalIdNbr FROM P09CashReceipt c JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND "
	 * +
	 * "c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr WHERE c.crId.crRefundType = :refType "
	 * +
	 * "AND c.crStatusText = :status AND c.nationalIdNbr >= :npiLow AND c.nationalIdNbr <= :npiHigh AND a.aId.actActivity = :activity "
	 * +
	 * "AND a.actProcessedInd <> 'P' ORDER BY c.nationalIdNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr"
	 * ) public List<ICRView12> fetchCRByNpiRangeActDesc( @Param("refType") String
	 * refType,@Param("status") String status,@Param("npiLow") String
	 * npiLow,@Param("npiHigh") String npiHigh,@Param("activity") String activity) ;
	 */

	//MBRRAA cursor
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON " +
			"c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.crMbrIdNbr = :mbrNum AND a.aId.actActivity = :activity " +
			"AND a.actProcessedInd <> 'P' ORDER BY c.crId.crRefundType, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchCRByMbrAct(@Param("refType") String refType,@Param("status") String status,@Param("mbrNum") String mbrNum,@Param("activity") String activity) ;

	//MBRRAA_DESC cursor
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON " +
			"c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr " +
			"WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status AND c.crMbrIdNbr = :mbrNum AND a.aId.actActivity = :activity " +
			"AND a.actProcessedInd <> 'P' ORDER BY c.crId.crRefundType, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchCRByMbrActDesc(@Param("refType") String refType,@Param("status") String status,@Param("mbrNum") String mbrNum,@Param("activity") String activity) ;

	//MBRRAABTW
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND " +
			"c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr WHERE c.crId.crRefundType = :refType " +
			"AND c.crStatusText = :status AND c.crMbrIdNbr >= :mbrLow AND c.crMbrIdNbr <= :mbrHigh AND a.aId.actActivity = :activity " +
			"AND a.actProcessedInd <> 'P' ORDER BY c.crMbrIdNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchCRByMbrRangeAct(@Param("refType") String refType,@Param("status") String status,@Param("mbrLow") String mbrLow,@Param("mbrHigh") String mbrHigh, @Param("activity") String activity) ;

	//******************************MBRRAABTW_DESC***************************************************

	//CLAIMTYPEEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status " +
			"AND c.crClaimType = :claimType ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByClaimType(@Param("refType") String refType,@Param("status") String status,@Param("claimType") String claimType) ;

	//CLAIMTYPEEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crStatusText = :status " +
			"AND c.crClaimType = :claimType ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByClaimTypeDesc(@Param("refType") String refType,@Param("status") String status,@Param("claimType") String claimType) ;

	//CLAIMXREFEQ
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON " +
			"c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr " +
			"WHERE c.crId.crRefundType = :refType AND c.crClaimType = :claimType AND a.actXrefNbr = :xrefNbr ORDER BY c.crId.crRefundType, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByClaimTypeAndXref( @Param("refType") String refType,@Param("claimType") String claimType, @Param("xrefNbr") String xrefNbr) ;

	//CLAIMXREFEQ_DESC
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c JOIN P09Activity a ON " +
			"c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr " +
			"WHERE c.crId.crRefundType = :refType AND c.crClaimType = :claimType AND a.actXrefNbr = :xrefNbr ORDER BY c.crId.crRefundType, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByClaimTypeAndXrefDesc( @Param("refType") String refType,@Param("claimType") String claimType, @Param("xrefNbr") String xrefNbr) ;

	//CLAIMXREFBTW
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr " +
			"WHERE c.crId.crRefundType = :refType AND c.crClaimType = :claimType AND a.actXrefNbr >= :xrefLow AND a.actXrefNbr <= :xrefHigh " +
			"ORDER BY a.actXrefNbr, c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByClaimTypeXrefRange(@Param("refType") String refType, @Param("claimType") String claimType,@Param("xrefLow") String xrefLow,@Param("xrefHigh") String xrefHigh) ;

	//CLAIMXREFBTW_DESC
	@Query("SELECT DISTINCT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c " +
			"JOIN P09Activity a ON c.crId.crRefundType = a.aId.crRefundType AND c.crId.crCntrlDate = a.aId.crCntrlDate AND c.crId.crCntrlNbr = a.aId.crCntrlNbr " +
			"WHERE c.crId.crRefundType = :refType AND c.crClaimType = :claimType AND a.actXrefNbr >= :xrefLow AND a.actXrefNbr <= :xrefHigh " +
			"ORDER BY a.actXrefNbr, c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByClaimTypeXrefRangeDesc(@Param("refType") String refType, @Param("claimType") String claimType,@Param("xrefLow") String xrefLow,@Param("xrefHigh") String xrefHigh) ;

	//LOCATIONEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crLocationNbr = :locNbr " +
			"AND c.crLocationClerk = :locClerk ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByLocAndClerk(@Param("locNbr") String locNbr,@Param("locClerk") String locClerk) ;

	//LOCATIONEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType FROM P09CashReceipt c WHERE c.crLocationNbr = :locNbr " +
			"AND c.crLocationClerk = :locClerk ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByLocAndClerkDesc(@Param("locNbr") String locNbr,@Param("locClerk") String locClerk) ;

	//CHKNUMONLYEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crCheckNbr = :checkNum ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByChkNum(@Param("refType") String refType, @Param("checkNum") String checkNum) ;

	//CHKNUMONLYEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
			"AND c.crCheckNbr = :checkNum ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByChkNumDesc(@Param("refType") String refType, @Param("checkNum") String checkNum) ;

	//CHKNUMONLYBTW
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crCheckNbr = :checkNum " +
			"AND c.crCheckNbr >= :checkNumLow AND c.crCheckNbr <= :checkNumHigh ORDER BY c.crId.crCntrlDate, c.crCheckNbr, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchChkDetailsInRange(@Param("refType") String refType,@Param("checkNum") String checkNum,@Param("checkNumLow") String checkNumLow,@Param("checkNumHigh") String checkNumHigh) ;

	//CHKNUMONLYBTW_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crCheckNbr = :checkNum " +
			"AND c.crCheckNbr >= :checkNumLow AND c.crCheckNbr <= :checkNumHigh ORDER BY c.crId.crCntrlDate DESC, c.crCheckNbr, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchChkDetailsInRangeDesc(@Param("refType") String refType,@Param("checkNum") String checkNum,@Param("checkNumLow") String checkNumLow,@Param("checkNumHigh") String checkNumHigh) ;

	//CHKNUMAMTEQ
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
		       "AND c.crCheckNbr = :checkNum AND c.crCheckAmt = :checkAmt ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByChkNumAndAmt(@Param("refType") String refType,@Param("checkNum") String checkNum,@Param("checkAmt") BigDecimal checkAmt) ;

	//CHKNUMAMTEQ_DESC
	@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType "
			+ " FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType " +
		       "AND c.crCheckNbr = :checkNum AND c.crCheckAmt = :checkAmt ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
	public	List<ICRSuperView> fetchByChkNumAndAmtDesc(@Param("refType") String refType,@Param("checkNum") String checkNum,@Param("checkAmt") BigDecimal checkAmt) ;

	//CDATEBTW cursor
		@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType FROM P09CashReceipt c "
				+ "WHERE c.crId.crRefundType = :refType AND c.crId.crCntrlDate >= :controlDateStart AND c.crId.crCntrlDate <= :controlDateEnd " +
				"ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
		public List<ICRSuperView> findByRefTypeAndCntrlDtRange(@Param("refType") String refType, @Param("controlDateStart") LocalDate controlDateStart, @Param("controlDateEnd") LocalDate controlDateEnd) ;

		//CDATEBTW_DESC cursor
		@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType FROM P09CashReceipt c "
				+ "WHERE c.crId.crRefundType = :refType AND c.crId.crCntrlDate >= :controlDateStart AND c.crId.crCntrlDate <= :controlDateEnd " +
				"ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
		public List<ICRSuperView> findByRefTypeAndCntrlDtRangeDesc(@Param("refType") String refType, @Param("controlDateStart") LocalDate controlDateStart, @Param("controlDateEnd") LocalDate controlDateEnd) ;

		//EDATEBTW
		@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
				"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crEntryDate >= :entryDateStart " +
				"AND c.crEntryDate <= :entryDateEnd ORDER BY c.crId.crCntrlDate, c.crId.crCntrlNbr")
		public List<ICRSuperView> fetchByRefTypeAndEntryDtRange( @Param("refType") String refType, @Param("entryDateStart") LocalDate entryDateStart, @Param("entryDateEnd") LocalDate entryDateEnd) ;

		//EDATEBTW_DESC cursor
		@Query("SELECT c.crId.crRefundType AS crRefundType, c.crId.crCntrlDate AS crCntrlDate, c.crId.crCntrlNbr AS crCntrlNbr, c.crStatusText AS crStatusText, c.crCntrldAmt AS crCntrldAmt, c.crReceiptType AS crReceiptType " +
				"FROM P09CashReceipt c WHERE c.crId.crRefundType = :refType AND c.crEntryDate >= :entryDateStart " +
				"AND c.crEntryDate <= :entryDateEnd ORDER BY c.crId.crCntrlDate DESC, c.crId.crCntrlNbr")
		public List<ICRSuperView> fetchByRefTypeAndEntryDtRangeDesc( @Param("refType") String refType, @Param("entryDateStart") LocalDate entryDateStart, @Param("entryDateEnd") LocalDate entryDateEnd) ;

}

