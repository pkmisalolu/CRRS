package com.abcbs.crrs.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcbs.crrs.entity.P09Option;
import com.abcbs.crrs.repositories.IOptionRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class P09125FieldValidator implements ConstraintValidator<P09125Field, String> {
	
	private String fieldName;
	
	@Autowired
	private IOptionRepository optionRepo;

    @Override
    public void initialize(P09125Field constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		switch (fieldName) {
        case "crRefundType":
        	// skip validation for null and blank
        	if (value == null || value.isBlank()) return true;
        	
        	List<P09Option> list = optionRepo.findByRecordTypeAndNarrLike("01", value.trim()+"     %");
        	if(list.size()!=0)
        		return true;
        case "crReceiptType":
        	// skip validation for null and blank
        	if (value == null || value.isBlank()) return true;
        	
        	List<P09Option> list1 = optionRepo.findByRecordTypeAndNarrLike("02", value.trim()+"     %");
        	if(list1.size()!=0)
        		return true;
        case "crStatusText":
        	// skip validation for null and blank
        	if (value == null || value.isBlank()) return true;
        	
        	List<P09Option> list2 = optionRepo.findByRecordTypeAndNarrLike("03", value.trim()+"     %");
        	if(list2.size()!=0)
        		return true;
        case "crClaimType":
        	// skip validation for null and blank
        	if (value == null || value.isBlank()) return true;
        	
        	List<P09Option> list3 = optionRepo.findByRecordTypeAndNarrLike("04", value.trim()+"     %");
        	if(list3.size()!=0)
        		return true;
			/*
			 * case "crLocationNbr": // skip validation for null and blank if (value == null
			 * || value.isBlank()) return true;
			 * 
			 * List<P09Option> list4 = optionRepo.findByRecordTypeAndNarrLike("09",
			 * value.trim()+"     %"); if(list4.size()!=0) return true;
			 */
        case "actActivity":
        	// skip validation for null and blank
        	if (value == null || value.isBlank()) return true;
        	
        	List<P09Option> list5 = optionRepo.findByRecordTypeAndNarrLike("08", value.trim()+"     %");
        	if(list5.size()!=0)
        		return true;
		}
		
			
		return false;
	}

	

}
