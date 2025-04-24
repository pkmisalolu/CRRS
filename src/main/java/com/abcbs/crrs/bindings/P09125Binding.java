package com.abcbs.crrs.bindings;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.abcbs.crrs.validator.P09125Field;
import com.abcbs.crrs.validator.P09125ValidLocCom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@P09125ValidLocCom
public class P09125Binding {
	
	@P09125Field(fieldName = "crRefundType", message = "INVALID REFUND TYPE - FOR VALID CODES PRESS HELP ")
	private String crRefundType;
	private LocalDate crCntrlDate;
	private String crCntrlNbr;
	@P09125Field(fieldName = "crReceiptType", message = "INVALID RECEIPT TYPE - FOR VALID CODES PRESS HELP ")
	private String crReceiptType;
	@P09125Field(fieldName = "crStatusText", message = "INVALID STATUS - FOR VALID CODES PRESS HELP ")
	private String crStatusText;
	private BigDecimal crCheckAmt;
	private String crCheckNbr;
	private String crRemittorName;
	private String crPatientLname;
	private String crPatientFname;
	private String crProviderNbr;
	private String crMbrIdNbr;
	private String crTaxIdNbr;
	//@P09125Field(fieldName = "crLocationNbr", message = "INVALID LOCATION CODE - FOR VALID CODES PRESS HELP ")
	private String crLocationNbr;
	private String crLocationClerk;
	@P09125Field(fieldName = "crClaimType", message = "INVALID CLAIM-TYPE - FOR VALID CODES PRESS HELP ")
	private String crClaimType;
	private LocalDate crEntryDate;
	private String actXrefNbr;
	//check 
	@P09125Field(fieldName = "actActivity", message = "INVALID ACTIVITY CODE - FOR VALID CODES PRESS HELP ")
	private String actActivity;
	private String nationalIdNbr;
	
	private String sortDateOrder;
	private Integer days;
	//Check
	private Integer startAfter;

}
