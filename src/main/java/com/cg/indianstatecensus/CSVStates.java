package com.cg.indianstatecensus;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
	
		@CsvBindByName(column = "SrNo")
		private int serialNumber;

		@CsvBindByName(column = "State Name")
		private String state;

		@CsvBindByName(column = "TIN")
		private int tin;

		@CsvBindByName(column = "StateCode")
		private String stateCode;

}
