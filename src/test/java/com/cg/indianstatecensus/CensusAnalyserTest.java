package com.cg.indianstatecensus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/IndiaStateCensusData.csv";
	private static final String STATE_CENSUS_CSV_FILE = "./src/test/IndiaStateCode.csv";
	private static final String DELIMITER_CHECK_FILE = "./src/test/DelimiterCheckFile.csv";
	private static final String HEADER_CHECK_FILE = "./src/test/HeaderCheckFile.csv";
	
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
	
	@Test
	public void givenWrongClassTypeShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule =  ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(STATE_CENSUS_CSV_FILE);
		}catch(CensusAnalyserException e) {
			System.out.println(e.getMessage());
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_CLASS_TYPE, e.type);
		}
	}
	
	@Test
	public void givenIncorrectDelimiterShouldThrowCustomException(){
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(DELIMITER_CHECK_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}
	
	@Test
	public void givenIncorrectHeaderShouldThrowCustomException(){
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(HEADER_CHECK_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_HEADER, e.type);
		}
	}
}
