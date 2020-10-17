package com.cg.indianstatecensus;

import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/IndiaStateCensusData.csv";
	@Test
	public void givenIndianCensusCsvFileReturnCorrectNumberOfRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfEntries = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, numOfEntries);
		} catch (CensusAnalyserException e) {
		}
	}

}
