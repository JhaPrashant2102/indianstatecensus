package com.cg.indianstatecensus;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new OpenCsvBuilder();
	}
}
