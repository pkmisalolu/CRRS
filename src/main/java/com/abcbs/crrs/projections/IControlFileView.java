package com.abcbs.crrs.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IControlFileView {
	String getCntrlOpenInd();
	LocalDate getCntrlFromDate();
	LocalDate getCntrlToDate();
	Integer getCntrlReceiptCnt();
	BigDecimal getCntrlReceiptAmt();
}
