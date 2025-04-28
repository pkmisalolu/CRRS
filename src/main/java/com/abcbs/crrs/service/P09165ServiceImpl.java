package com.abcbs.crrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbs.crrs.bindings.P09165Binding;
import com.abcbs.crrs.projections.IControlFileView;
import com.abcbs.crrs.repositories.IP09ControlRepository;

@Service
public class P09165ServiceImpl implements IP09165Service{
	
	@Autowired
	private IP09ControlRepository repo;

	public IControlFileView fetch(P09165Binding binding) {
		// TODO Auto-generated method stub
		IControlFileView list=repo.fecthWithRefundType(binding.getRefundType());
		return list;
	}

	@Override
	public int updateWithRefundType(P09165Binding binding) {
		// TODO Auto-generated method stub
		return repo.updateWithRefundType(binding.getRefundType(),binding.getRefundCount(),binding.getRefundAmount());
	}
}
