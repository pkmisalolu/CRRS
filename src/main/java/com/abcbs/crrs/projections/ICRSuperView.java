package com.abcbs.crrs.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ICRSuperView {

	
	  BigDecimal getCrCntrldAmt(); 
	  String getCrRefundType(); 
	  LocalDate getCrCntrlDate(); 
	  String getCrCntrlNbr();
	  String getCrStatusText();
	  String getCrReceiptType();
	 
	 
}
