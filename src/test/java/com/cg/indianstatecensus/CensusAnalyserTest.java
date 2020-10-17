package com.cg.indianstatecensus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/IndiaStateCensusData.csv";
	
	@Test
	public void givenIndiaCensusCsvFileReturnCorrectNumberOfRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfEntries = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, numOfEntries);
		} catch (CensusAnalyserException e) {
		}
	}
	@Test
	public void givenWrongIndiaCensusFileShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			//ExpectedException exceptionRule =  ExpectedException.none();
			//exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		}catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

}
