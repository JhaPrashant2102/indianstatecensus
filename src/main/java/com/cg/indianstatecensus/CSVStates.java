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

		public int getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(int serialNumber) {
			this.serialNumber = serialNumber;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public int getTin() {
			return tin;
		}

		public void setTin(int tin) {
			this.tin = tin;
		}

		public String getStateCode() {
			return stateCode;
		}

		public void setStateCode(String stateCode) {
			this.stateCode = stateCode;
		}

}
