package com.abcbs.crrs.projections;

import java.time.LocalDate;

public interface ICRLocationView {

	String getCrLocationNbr();
    String getCrLocationClerk();
    LocalDate getCrLocationDate();
    
}
