package com.abcbs.crrs.projections;

import java.time.LocalDate;

public interface ICRActivityView {

	String getCrRefundType();
	LocalDate getCrCntrlDate();
	String getCrCntrlNbr();
	String getCrClaimType();

}
