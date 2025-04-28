package com.abcbs.crrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbs.crrs.bindings.P09160Binding;
import com.abcbs.crrs.projections.ICRHelp;
import com.abcbs.crrs.repositories.IOptionRepository;

@Service
public class P09160ServiceImpl implements IP09160Service{
	
	@Autowired
	private IOptionRepository optionRepo;
	
	@Override
	public List<ICRHelp> help(P09160Binding binding) {
		// TODO Auto-generated method stub
		System.out.println(binding.getRecordType());
		List<ICRHelp> helpList=optionRepo.findByRecordType(binding.getRecordType());
		return helpList;
	}
}
