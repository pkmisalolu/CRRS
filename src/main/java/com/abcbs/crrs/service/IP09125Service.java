package com.abcbs.crrs.service;

import java.util.List;

import com.abcbs.crrs.bindings.P09125Binding;
import com.abcbs.crrs.bindings.P09125Output;
import com.abcbs.crrs.projections.ICRSuperView;

public interface IP09125Service {

	public List<? extends ICRSuperView> fetch(P09125Binding binding) ;
	public P09125Output generateP09125Output(P09125Binding binding);
}
