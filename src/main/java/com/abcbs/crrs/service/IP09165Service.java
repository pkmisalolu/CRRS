package com.abcbs.crrs.service;

import java.util.List;

import com.abcbs.crrs.bindings.P09165Binding;
import com.abcbs.crrs.projections.IControlFileView;

public interface IP09165Service {
	public IControlFileView fetch(P09165Binding binding);
	public int updateWithRefundType(P09165Binding binding);
}
