package com.abcbs.crrs.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcbs.crrs.bindings.P09125Binding;
import com.abcbs.crrs.entity.P09Option;
import com.abcbs.crrs.repositories.IOptionRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class P09125LocationValidator implements ConstraintValidator<P09125ValidLocCom, P09125Binding> {

	@Autowired
	private IOptionRepository optionRepo;

	@Override
	public boolean isValid(P09125Binding request, ConstraintValidatorContext context) {

		String locNum = request.getCrLocationNbr();
		String clerk = request.getCrLocationClerk();


		if (locNum == null && clerk == null) return true;

		if(locNum != null && !locNum.isBlank() && clerk != null && !clerk.isBlank()) {
			List<P09Option> list4 = optionRepo.findByRecordTypeAndNarrLike("09", locNum.trim()+clerk.trim()+"     %");
			if(list4.size()!=0)
				return true;
		}else if(locNum != null && !locNum.isBlank()){
			List<P09Option> list4 = optionRepo.findByRecordTypeAndNarrLike("09", locNum.trim()+"%");
			if(list4.size()!=0)
				return true;
		}
		else if(clerk != null && !clerk.isBlank()){
			return true;
		}



		return false;
	}

}
