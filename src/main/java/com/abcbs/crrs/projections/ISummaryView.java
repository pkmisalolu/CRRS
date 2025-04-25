package com.abcbs.crrs.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ISummaryView {

	LocalDate getSumProcessedDate();
	Integer getSumAdditionsCnt();
    BigDecimal getSumAdditionsAmt();
	Integer getSumDeletionsCnt();
	BigDecimal getSumDeletionsAmt();
	Integer getSumEndingCnt();
	BigDecimal getSumEndingAmt();
}
