package com.abcbs.crrs.bindings;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class P09125Op {

	private String crRefundType;
	private LocalDate crCntrlDate;
	private String crCntrlNbr;
}
