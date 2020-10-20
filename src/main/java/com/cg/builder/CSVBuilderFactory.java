package com.cg.builder;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new OpenCsvBuilder();
	}
}
