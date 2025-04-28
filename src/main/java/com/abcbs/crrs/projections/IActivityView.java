package com.abcbs.crrs.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IActivityView {
	
	String getCrRefundType();
    LocalDate getCrCntrlDate();
    String getCrCntrlNbr();
    BigDecimal getActActivityAmt();
    String getActXrefNbr();
    String getActArrsCode();

}
