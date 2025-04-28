package com.abcbs.crrs.service;

import java.util.List;

import com.abcbs.crrs.bindings.P09160Binding;
import com.abcbs.crrs.projections.ICRHelp;

public interface IP09160Service {
	public List<ICRHelp> help(P09160Binding binding);
}
