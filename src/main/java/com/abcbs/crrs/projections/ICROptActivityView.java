package com.abcbs.crrs.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ICROptActivityView {
	
	    String getCrRefundType();
	    LocalDate getCrCntrlDate();
	    String getCrCntrlNbr();
	    LocalDate getCrEntryDate();
	    LocalDate getCrStatusDate();
	    String getCrStatusText();
	    String getCrPendFinAct();
	    BigDecimal getCrCntrldAmt();
	    BigDecimal getCrReceiptBal();
	    LocalDate getCrLetterDate();
	    String getCrLocationNbr();
	    String getCrLocationClerk();
	    String getCrGlAcctNbr();
	    String getCrCorp();
	    String getCrBankAcctNbr();
	    String getCrPatientLname();
	    String getCrPatientFname();
	    String getCrMbrIdNbr();
	    String getCrUserId();

}
