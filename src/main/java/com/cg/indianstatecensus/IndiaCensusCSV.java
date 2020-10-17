package com.cg.indianstatecensus;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {
	@CsvBindByName(column = "State")
	private String stateName;

	@CsvBindByName(column = "Population")
	private long population;

	@CsvBindByName(column = "AreaInSqKm")
	private int area;

	@CsvBindByName(column = "DensityPerSqKm")
	private int density;

}
