package com.abcbs.crrs.bindings;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class P09165Binding {
	private String refundAction;
	private String refundType;
	private Integer refundCount;
	private BigDecimal refundAmount;
}
