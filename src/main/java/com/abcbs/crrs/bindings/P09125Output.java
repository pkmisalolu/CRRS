package com.abcbs.crrs.bindings;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class P09125Output implements Serializable {
	
	private String sortedBy;
	private P09125Op firstRec;
	private P09125Op lastRec;
	private Integer totalCnt;
	private BigDecimal totalAmt;
	private List<P09125Op> list;

}
