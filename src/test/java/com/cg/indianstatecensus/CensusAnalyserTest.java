package com.cg.indianstatecensus;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/resource/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/IndiaStateCensusData.csv";
	private static final String STATE_CODE_CSV_FILE = "./src/resource/IndiaStateCode.csv";
	private static final String DELIMITER_CHECK_FILE = "./src/resource/DelimiterCheckFile.csv";
	private static final String HEADER_CHECK_FILE = "./src/resource/HeaderCheckFile.csv";
	private static final String DELIMITER_STATE_CODE_CHECK_FILE = "./src/resource/StateCodeDelimiterCheckFile.csv";
	private static final String HEADER_STATE_CODE_CHECK_FILE = "./src/resource/StateCodeHeaderCheckFile.csv";
	
	@Test
	public void givenIndiaCensusCsvFileReturnCorrectNumberOfRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfEntries = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,false);
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
			censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH,false);
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
			censusAnalyser.loadIndiaCensusData(STATE_CODE_CSV_FILE,false);
		}catch(CensusAnalyserException e) {
			System.out.println(e.getMessage());
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_CLASS_TYPE, e.type);
		}
	}
	
	@Test
	public void givenIncorrectDelimiterShouldThrowCustomException(){
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(DELIMITER_CHECK_FILE,false);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}
	
	@Test
	public void givenIncorrectHeaderShouldThrowCustomException(){
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(HEADER_CHECK_FILE,true);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_HEADER, e.type);
		}
	}
	
	@Test
	public void givenIndiaStateCodeCsvFileReturnCorrectNumberOfRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfEntries = censusAnalyser.loadIndiaStateCodeData(STATE_CODE_CSV_FILE,false);
			Assert.assertEquals(37, numOfEntries);
		} catch (CensusAnalyserException e) {
		}
	}
	
	@Test
	public void givenWrongIndiaStateCodeCsvFileShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			//ExpectedException exceptionRule =  ExpectedException.none();
			//exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCodeData(WRONG_CSV_FILE_PATH,false);
		}catch(CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}
	
	@Test
	public void givenWrongClassTypeInStateCodeCsvFileShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule =  ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCodeData(INDIA_CENSUS_CSV_FILE_PATH,false);
		}catch(CensusAnalyserException e) {
			System.out.println(e.getMessage());
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_CLASS_TYPE, e.type);
		}
	}
	
	@Test
	public void givenIncorrectDelimiterInStateCodeFileShouldThrowCustomException(){
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaStateCodeData(DELIMITER_STATE_CODE_CHECK_FILE,false);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}
	
	@Test
	public void givenIncorrectHeaderInStateCodeFileShouldThrowCustomException(){
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaStateCodeData(HEADER_STATE_CODE_CHECK_FILE,true);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_HEADER, e.type);
		}
	}
	
	@Test
	public void givenCSVFileWhenConverteToStateWiseSortedJsonFormatShouldMatchFirstAndLastElement(){
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			String sortedJsonData = censusAnalyser.stateWiseSortedCensusDataInJsonFormat(INDIA_CENSUS_CSV_FILE_PATH);
			IndiaCensusCSV sortedData[] = new Gson().fromJson(sortedJsonData,IndiaCensusCSV[].class);
			List<IndiaCensusCSV> sortedList = Arrays.stream(sortedData).collect(Collectors.toList());
			String check = sortedList.get(0).getStateName()+" "+sortedList.get(sortedList.size()-1).getStateName();
			Assert.assertEquals("Andhra Pradesh West Bengal", check);
		} catch (CensusAnalyserException e) {
		}
	}
	
}
