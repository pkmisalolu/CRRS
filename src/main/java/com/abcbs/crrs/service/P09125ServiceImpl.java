/**
 * Developer: Dharani Parimella
 * Description: This class handles Cash receipt query screen logic
 */
package com.abcbs.crrs.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbs.crrs.bindings.P09125Binding;
import com.abcbs.crrs.bindings.P09125Op;
import com.abcbs.crrs.bindings.P09125Output;
import com.abcbs.crrs.config.AppConfigProperties;
import com.abcbs.crrs.config.P09125MessageConstants;
import com.abcbs.crrs.exceptionHandling.InValidActivityException;
import com.abcbs.crrs.exceptionHandling.InValidLocationException;
import com.abcbs.crrs.exceptionHandling.InValidQueryException;
import com.abcbs.crrs.exceptionHandling.InvalidStartAfterException;
import com.abcbs.crrs.exceptionHandling.NoDataSelectedException;
import com.abcbs.crrs.exceptionHandling.StatusClosedException;
import com.abcbs.crrs.projections.ICRSuperView;
import com.abcbs.crrs.repositories.IP09CashReceiptRepository;

@Service
public class P09125ServiceImpl implements IP09125Service {

	@Autowired
	private IP09CashReceiptRepository repo;

	private String sortOrder;

	private Map<String,String> messages;

	@Autowired
	public P09125ServiceImpl(AppConfigProperties props) {
		messages = props.getMessages();
	}

	public List<ICRSuperView> fetch(P09125Binding binding) { 
		String ref = "0", rec = "0" ,cdate="0", cnum="0", status="0", remName="0", patLname="0", patFname="0", checkNum="0",
				provNum="0", mbrNum="0", taxNum="0",loc="0", eDate="0", days="0", npi="0", act="0", claim="0",xRef="0", chkAmt="0";

		String condition="";
		List<ICRSuperView> list =null;

		Boolean inValidQuery, refRecStat, refLocStat, refChkNum, refProvNum, refNpi, refMbrNum, refTaxNum, refRemName, refPatName,
		refCdate, refEdate, refProvStat, refNpiStat, refMbrStat, refProvRaa, refNpiRaa, refMbrRaa, refClaimType, refClaimXref,
		location, refCheckNumOnly, refCheckNumAmt;

		inValidQuery = refRecStat= refLocStat= refChkNum= refProvNum= refNpi= refMbrNum= refTaxNum= refRemName= refPatName=
				refCdate= refEdate= refProvStat= refNpiStat= refMbrStat= refProvRaa= refNpiRaa= refMbrRaa= refClaimType= refClaimXref=
				location= refCheckNumOnly= refCheckNumAmt = false;


		if (binding.getCrRefundType() != null && !binding.getCrRefundType().isBlank()) {
			ref = "1";
		} if(binding.getCrReceiptType() != null && !binding.getCrReceiptType().isBlank()) {
			rec = "1";
		} if(binding.getCrCntrlDate() != null ) {
			cdate = "1";
		} if(binding.getCrCntrlNbr() != null && !binding.getCrCntrlNbr().isBlank()) {
			cnum = "1";
		} if(binding.getCrStatusText() != null && !binding.getCrStatusText().isBlank()) {
			status = "1";
		} if(binding.getCrRemittorName() != null && !binding.getCrRemittorName().isBlank()) {
			remName = "1";
		} if(binding.getCrPatientLname() != null && !binding.getCrPatientLname().isBlank()) {
			patLname = "1";
		} if(binding.getCrPatientFname() != null && !binding.getCrPatientFname().isBlank()) {
			patFname = "1";
		} if(binding.getCrCheckNbr() != null && !binding.getCrCheckNbr().isBlank()) {
			checkNum = "1";
		} if(binding.getCrProviderNbr() != null && !binding.getCrProviderNbr().isBlank()) {
			provNum = "1";
		} if(binding.getCrMbrIdNbr() != null && !binding.getCrMbrIdNbr().isBlank()) {
			mbrNum = "1";
		} if(binding.getCrTaxIdNbr() != null && !binding.getCrTaxIdNbr().isBlank()) {
			taxNum = "1";
		} if((binding.getCrLocationNbr() != null && !binding.getCrLocationNbr().isBlank())||(binding.getCrLocationClerk() != null && !binding.getCrLocationClerk().isBlank())) {
			loc = "1";
		} if((binding.getCrEntryDate() != null )) {
			eDate = "1";
		} if((binding.getDays() != null )) {
			days = "1";
		} if((binding.getNationalIdNbr() != null && !binding.getNationalIdNbr().isBlank())) {
			npi = "1";
		} if((binding.getActActivity() != null && !binding.getActActivity().isBlank())) {
			act = "1";
		} if((binding.getCrClaimType() != null && !binding.getCrClaimType().isBlank())) {
			claim = "1";
		} if((binding.getActXrefNbr() != null && !binding.getActXrefNbr().isBlank())) {
			xRef = "1";
		} if((binding.getCrCheckAmt() != null )) {
			chkAmt  = "1";
		}

		condition = ref+rec+cdate+cnum+status+remName+patLname+patFname+checkNum+provNum+mbrNum+taxNum+loc+eDate
				+days+npi+act+claim+xRef+chkAmt;

		//setting flags to execute appropriate query
		if("10000000000000000000".equals(condition) || "11000000000000000000".equals(condition) || "10100000000001100000".equals(condition)
				|| "10100000000001000000".equals(condition) || "10001000000000000000".equals(condition)) {

			inValidQuery = true;

		}else if("11001000000000000000".equals(condition)) {
			refRecStat = true;
		}else if("10001000000010000000".equals(condition)) {
			refLocStat = true;
		}else if("10000000100000000000".equals(condition)) {
			refChkNum = true;
		}else if("10000000010000000000".equals(condition)) {
			refProvNum = true;
		}else if("10000000000000010000".equals(condition)) {
			refNpi = true;
		}else if("10000000001000000000".equals(condition)) {
			refMbrNum = true;
		}else if("10000000000100000000".equals(condition)) {
			refTaxNum = true;
		}else if("10000100000000000000".equals(condition)) {
			refRemName = true;
		}else if("10000010000000000000".equals(condition) || "10000011000000000000".equals(condition)) {
			refPatName = true;
		}else if("10100000000000000000".equals(condition) || "10100000000000100000".equals(condition)) {
			refCdate = true;
		}else if("10000000000001000000".equals(condition) || "10000000000001100000".equals(condition)) {
			refEdate = true;
		}else if("10001000010000000000".equals(condition)) {
			refProvStat = true;
		}else if("10001000000000010000".equals(condition)) {
			refNpiStat = true;
		}else if("10001000001000000000".equals(condition)) {
			refMbrStat = true;
		}else if("10001000010000001000".equals(condition)) {
			refProvRaa = true;
		}else if("10001000000000011000".equals(condition)) {
			refNpiRaa = true;
		}else if("10001000001000001000".equals(condition)) {
			refMbrRaa = true;
		}else if("10001000000000000100".equals(condition)) {
			refClaimType = true;
		}else if("10000000000000000110".equals(condition)) {
			refClaimXref = true;
		}else if("00000000000010000000".equals(condition)) {
			location = true;
		}else if("10000000100000000000".equals(condition)) {
			refCheckNumOnly = true;
		}else if("10000000100000000001".equals(condition)) {
			refCheckNumAmt = true;
		}

		//executing the query
		if(inValidQuery) {
			throw  new InValidQueryException(messages.get(P09125MessageConstants.INVALID_QUERY));
		}
		else if(refRecStat) {
			//checking if status is closed
			if("CLOSED".equals(binding.getCrStatusText()))
				throw new StatusClosedException(messages.get(P09125MessageConstants.CLOSED_STATUS));
			else {
				sortOrder = "CONTROL DATE / NBR";
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findCashReceiptsByDtCntlNbrDesc(binding.getCrRefundType(), binding.getCrReceiptType(), binding.getCrStatusText());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findCashReceiptsByDtCntlNbr(binding.getCrRefundType(), binding.getCrReceiptType(), binding.getCrStatusText());
					//return new ArrayList<CashReceiptQT2Binding>(list);

				}
			}
		}else if(refChkNum) { //doubt
			sortOrder = "CONTROL DATE / NBR";
			//checking whether check number is containing any generics like '%' or '_'
			if(!binding.getCrCheckNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndCheckNumDesc(binding.getCrRefundType(), binding.getCrCheckNbr());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndCheckNum(binding.getCrRefundType(), binding.getCrCheckNbr());

				}
			}else {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndCheckNumDesc(binding.getCrRefundType(), binding.getCrCheckNbr().substring(0, binding.getCrCheckNbr().indexOf("_")));

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndCheckNum(binding.getCrRefundType(), binding.getCrCheckNbr().substring(0, binding.getCrCheckNbr().indexOf("_")));

				}
			}
		}else if(refCdate) {
			sortOrder = "CONTROL DATE / NBR";
			LocalDate date = null;
			//if user enters days
			if("1".equals(days)) {
				date = binding.getCrCntrlDate().plusDays(binding.getDays());
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndCntrlDtRangeDesc(binding.getCrRefundType(), binding.getCrCntrlDate(), date);

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndCntrlDtRange(binding.getCrRefundType(), binding.getCrCntrlDate(), date);
				}
			}
			//Here we have ignored between logic in Cobol instead we have taken "=" and user doesn't enters days.
			else {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndCntrlDtRangeDesc(binding.getCrRefundType(), binding.getCrCntrlDate());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndCntrlDtRange(binding.getCrRefundType(), binding.getCrCntrlDate());
				}
			}
		}else if(refEdate) {
			sortOrder = "CONTROL DATE / NBR";
			LocalDate date = null;
			//if user enters days
			if("1".equals(days)) {
				date = binding.getCrEntryDate().plusDays(binding.getDays());
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.fetchByRefTypeAndEntryDtRangeDesc(binding.getCrRefundType(), binding.getCrEntryDate(), date);

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.fetchByRefTypeAndEntryDtRange(binding.getCrRefundType(), binding.getCrEntryDate(), date);
				}//if user doesn't enter a date
			}else {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.fetchByRefTypeAndEntryDtRangeDesc(binding.getCrRefundType(), binding.getCrEntryDate());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.fetchByRefTypeAndEntryDtRange(binding.getCrRefundType(), binding.getCrEntryDate());
				}
			}
		}else if(refLocStat) {			
			sortOrder = "CONTROL DATE / NBR";
			if((binding.getCrLocationNbr() !=null)  && (binding.getCrLocationClerk() !=null) &&  (!binding.getCrLocationNbr().isBlank()) && (!binding.getCrLocationClerk().isBlank())) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeStatusAndLocDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrLocationNbr(), binding.getCrLocationClerk());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeStatusAndLoc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrLocationNbr(), binding.getCrLocationClerk());

				}
			}else if((binding.getCrLocationNbr() !=null) &&  (!binding.getCrLocationNbr().isBlank())) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.fetchByRefTypeStatusAndLocDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrLocationNbr());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.fetchByRefTypeStatusAndLoc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrLocationNbr());

				}
			}else if((binding.getCrLocationClerk() !=null) && (!binding.getCrLocationClerk().isBlank())) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.fetchByRefTypeStatusAndClerkDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrLocationClerk());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.fetchByRefTypeStatusAndClerk(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrLocationClerk());

				}
			}				
		}else if(refProvNum) {
			sortOrder = "CONTROL DATE / NBR";
			//checking whether provider number is containing any generics like '%' or '_'
			if(!binding.getCrProviderNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndProvDesc(binding.getCrRefundType(), binding.getCrProviderNbr());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndProv(binding.getCrRefundType(), binding.getCrProviderNbr());

				}
			}
		}else if(refNpi) {
			sortOrder = "CONTROL DATE / NPI";
			if(!binding.getNationalIdNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndNpiDesc(binding.getCrRefundType(), binding.getNationalIdNbr());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndNpi(binding.getCrRefundType(), binding.getNationalIdNbr());

				}
			}
		}else if(refMbrNum) {
			sortOrder = "CONTROL DATE / NBR";
			//checking whether member number is containing any generics like '%' or '_'
			if(!binding.getCrMbrIdNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndMemberIdDesc(binding.getCrRefundType(), binding.getCrMbrIdNbr());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndMemberId(binding.getCrRefundType(), binding.getCrMbrIdNbr());

				}
			}	
		}else if(refTaxNum) {
			sortOrder = "CONTROL DATE / NBR";
			if(!binding.getCrTaxIdNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndTaxIdDesc(binding.getCrRefundType(), binding.getCrTaxIdNbr());

				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndTaxId(binding.getCrRefundType(), binding.getCrTaxIdNbr());

				}
			}
		}else if(refRemName) {
			sortOrder = "CONTROL DATE / NBR";
			if(!binding.getCrRemittorName().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndRemNameDesc(binding.getCrRefundType(), binding.getCrRemittorName());
				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndRemName(binding.getCrRefundType(), binding.getCrRemittorName());
				}
			}
		}else if(refPatName) {//need to do in the last
			sortOrder = "CONTROL DATE / NBR";
			//if lastname and firstname has entered
			if("1".equals(patLname) && "1".equals(patFname)) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndFullNameRangeDesc(binding.getCrRefundType(), binding.getCrPatientLname(), binding.getCrPatientFname());
				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndFullNameRange(binding.getCrRefundType(), binding.getCrPatientLname(), binding.getCrPatientFname());
				}
				//If only Lastname has entered.
			}else if("1".equals(patLname) && "0".equals(patFname)) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndPatLnameDesc(binding.getCrRefundType(), binding.getCrPatientLname());
				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeAndPatLname(binding.getCrRefundType(), binding.getCrPatientLname());
				}
			}
		}else if(refProvStat) {
			sortOrder = "CONTROL DATE / NBR";
			if(!binding.getCrProviderNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeStatusAndProvDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrProviderNbr());
				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeStatusAndProv(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrProviderNbr());
				}
			}
		}else if(refMbrStat) {
			sortOrder = "CONTROL DATE / NBR";
			if(!binding.getCrMbrIdNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeStatusAndMbrIdDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrMbrIdNbr());
				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeStatusAndMbrId(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrMbrIdNbr());
				}
			}
		}else if(refProvRaa) {
			//checking if status is closed
			if("CLOSED".equals(binding.getCrStatusText()))
				throw new StatusClosedException(messages.get(P09125MessageConstants.CLOSED_STATUS));
			else if(!("RAA".equals(binding.getActActivity()))){
				throw new InValidActivityException(messages.get(P09125MessageConstants.RAA_ACTIVITY));
			}else {
				sortOrder = "CONTROL DATE / NBR";
				if(!binding.getCrProviderNbr().matches(".*[%_].*")) {
					if("D".equals(binding.getSortDateOrder())) {
						list = repo.fetchCRWithActDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrProviderNbr(), binding.getActActivity());
					}else if("A".equals(binding.getSortDateOrder())) {
						list = repo.fetchCRWithAct(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrProviderNbr(), binding.getActActivity());
					}
				}
			}
		}else if(refClaimType) {
			sortOrder = "CONTROL DATE / NBR";
			if("D".equals(binding.getSortDateOrder())) {
				list = repo.fetchByClaimTypeDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrClaimType());
			}else if("A".equals(binding.getSortDateOrder())) {
				list = repo.fetchByClaimType(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrClaimType());
			}
		}else if(refClaimXref) {
			sortOrder = "CONTROL DATE / NBR";
			if(!binding.getActXrefNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.fetchByClaimTypeAndXrefDesc(binding.getCrRefundType(), binding.getCrClaimType(), binding.getActXrefNbr());
				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.fetchByClaimTypeAndXref(binding.getCrRefundType(), binding.getCrClaimType(), binding.getActXrefNbr());
				}
			}
		}else if(location) {
			sortOrder = "CONTROL DATE / NBR";
			if((binding.getCrLocationNbr() !=null)  && (binding.getCrLocationClerk() !=null) &&  (!binding.getCrLocationNbr().isBlank()) && (!binding.getCrLocationClerk().isBlank())) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.fetchByLocAndClerkDesc(binding.getCrLocationNbr(), binding.getCrLocationClerk());
				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.fetchByLocAndClerk(binding.getCrLocationNbr(), binding.getCrLocationClerk());				
				}
			}else {
				throw new InValidLocationException(messages.get(P09125MessageConstants.INVALID_LOCATION));
			}
		}else if(refCheckNumOnly) {
			sortOrder = "CONTROL DATE / NBR";
			if(!binding.getCrCheckNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.fetchByChkNumDesc(binding.getCrRefundType(), binding.getCrCheckNbr());
				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.fetchByChkNum(binding.getCrRefundType(), binding.getCrCheckNbr());
				}
			}
		}else if(refCheckNumAmt) {
			if("D".equals(binding.getSortDateOrder())) {
				list = repo.fetchByChkNumAndAmtDesc(binding.getCrRefundType(), binding.getCrCheckNbr(), binding.getCrCheckAmt());
			}else if("A".equals(binding.getSortDateOrder())) {
				list = repo.fetchByChkNumAndAmt(binding.getCrRefundType(), binding.getCrCheckNbr(), binding.getCrCheckAmt());
			}
		}else if(refNpiStat) {
			sortOrder = "CONTROL DATE / NBR";
			if(!binding.getNationalIdNbr().matches(".*[%_].*")) {
				if("D".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeStatAndNpiDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getNationalIdNbr());
				}else if("A".equals(binding.getSortDateOrder())) {
					list = repo.findByRefTypeStatAndNpi(binding.getCrRefundType(), binding.getCrStatusText(), binding.getNationalIdNbr());
				}
			}
		}else if(refNpiRaa) {
			//checking if status is closed
			if("CLOSED".equals(binding.getCrStatusText()))
				throw new StatusClosedException(messages.get(P09125MessageConstants.CLOSED_STATUS));
			else if(!("RAA".equals(binding.getActActivity()))){
				throw new InValidActivityException(messages.get(P09125MessageConstants.RAA_ACTIVITY));
			}else {
				sortOrder = "CONTROL DATE / NBR";
				if(!binding.getNationalIdNbr().matches(".*[%_].*")) {
					if("D".equals(binding.getSortDateOrder())) {
						list = repo.fetchCRByNpiActDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getNationalIdNbr(), binding.getActActivity());
					}else if("A".equals(binding.getSortDateOrder())) {
						list = repo.fetchCRByNpiAct(binding.getCrRefundType(), binding.getCrStatusText(), binding.getNationalIdNbr(), binding.getActActivity());
					}
				}
			}
		}else if(refMbrRaa) {
			//checking if status is closed
			if("CLOSED".equals(binding.getCrStatusText()))
				throw new StatusClosedException(messages.get(P09125MessageConstants.CLOSED_STATUS));
			else if(!("RAA".equals(binding.getActActivity()))){
				throw new InValidActivityException(messages.get(P09125MessageConstants.RAA_ACTIVITY));
			}else {
				sortOrder = "CONTROL DATE / NBR";
				if(!binding.getCrMbrIdNbr().matches(".*[%_].*")) {
					if("D".equals(binding.getSortDateOrder())) {
						list = repo.fetchCRByMbrActDesc(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrMbrIdNbr(), binding.getActActivity());
					}else if("A".equals(binding.getSortDateOrder())) {
						list = repo.fetchCRByMbrAct(binding.getCrRefundType(), binding.getCrStatusText(), binding.getCrMbrIdNbr(), binding.getActActivity());
					}
				}
			}
		}//if only refund type, cdate, cnum entered
		else if("1".equals(ref) && "1".equals(cdate) && "1".equals(cnum)) {
			sortOrder = "CONTROL NUMBER";
			if("D".equals(binding.getSortDateOrder())) {

				list = repo.findCashReceiptsOrderByNbr(binding.getCrRefundType(),binding.getCrCntrlDate(), getCnumLv(binding.getCrCntrlNbr()), getCnumLv(binding.getCrCntrlNbr()));

			}else if("A".equals(binding.getSortDateOrder())) {

				list = repo.findCashReceipts(binding.getCrRefundType(),binding.getCrCntrlDate(), getCnumLv(binding.getCrCntrlNbr()), getCnumLv(binding.getCrCntrlNbr()));

			}
		}//remaining combination throws exception.
		else {
			throw new InValidQueryException(messages.get(P09125MessageConstants.INVALID_QUERY));
		}
		return list;
	}

	public String getCnumLv(String ControlNum) {
		String lv ="";
		String hv ="";
		if(" ".equals(ControlNum.substring(3)) && "".equals(ControlNum.substring(3))){
			lv = ControlNum;
			ControlNum = ControlNum+"Z";
			hv = ControlNum;
		}else {
			lv = ControlNum; 
		}
		return lv;
	}
	public String getCnumHv(String ControlNum) {
		String lv ="";
		String hv ="";
		if(" ".equals(ControlNum.substring(3)) && "".equals(ControlNum.substring(3))){
			lv = ControlNum;
			ControlNum = ControlNum+"Z";
			hv = ControlNum;
		}else {
			hv = ControlNum;
		}
		return hv;
	}

	public P09125Output generateP09125Output(P09125Binding binding){
		List<? extends ICRSuperView> crList =  fetch(binding);
		P09125Output out = new P09125Output();
		List<P09125Op> list = new ArrayList<P09125Op>();
		BigDecimal[] total = {BigDecimal.ZERO};

		if(crList.size()==0)
			throw new NoDataSelectedException(messages.get(P09125MessageConstants.NO_DATA));

		if(binding.getStartAfter() != null) {
			if(crList.size()<binding.getStartAfter())
				throw new InvalidStartAfterException(messages.get(P09125MessageConstants.INVALID_STARTAFTER));
			
			crList = crList.subList(binding.getStartAfter(), crList.size());
		}

		crList.forEach(record->{ 
			total[0] = total[0].add(record.getCrCntrldAmt()); 
			P09125Op op = new P09125Op();
			BeanUtils.copyProperties(record, op); 
			list.add(op);
		});

		//BeanUtils.copyProperties(crList, list);
		out.setTotalCnt(list.size());
		out.setList(list);
		out.setSortedBy(sortOrder);
		out.setTotalAmt(total[0] );
		out.setFirstRec(list.get(0));
		out.setLastRec(list.get(list.size()-1));

		return out;

	}
}
