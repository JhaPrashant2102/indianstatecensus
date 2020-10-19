package com.cg.indianstatecensus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.cg.indianstatecensus.CensusAnalyserException.ExceptionType;
import com.opencsv.exceptions.CsvException;

public class CensusAnalyser {
	public int loadIndiaCensusData(String csvFilePath, boolean flag) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<IndiaCensusCSV> censusCsvIterator = null;
			try{
				censusCsvIterator = csvBuilder.getCsvFileIterator(reader,IndiaCensusCSV.class);
			}catch (CsvException e) {
				throw new CensusAnalyserException("incorrect class type", ExceptionType.INCORRECT_CLASS_TYPE);
			}
			if (flag) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath));
				String line = bufferedReader.readLine();
				boolean flagForHeader = true;
				while (line != null) {
					if (!line.contains(","))
						throw new CensusAnalyserException("Incorrect Delimiter", ExceptionType.INCORRECT_DELIMITER);
					if (flagForHeader) {
						String[] headers = line.split(",");
						if (!(headers[0].equals("State") && headers[1].equals("Population")
								&& headers[2].equals("AreaInSqKm") && headers[3].equals("DensityPerSqKm")))
							throw new CensusAnalyserException("Incorrect Headers", ExceptionType.INCORRECT_HEADER);
						flagForHeader = false;
					}
					line = bufferedReader.readLine();
				}
				bufferedReader.close();
			}
			return getCount(censusCsvIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadIndiaStateCodeData(String csvFilePath, boolean flag) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<CSVStates> stateCsvIterator = null;
			try {
				stateCsvIterator = csvBuilder.getCsvFileIterator(reader, CSVStates.class);
			} catch (CsvException e) {
				throw new CensusAnalyserException("incorrect class type", ExceptionType.INCORRECT_CLASS_TYPE);
			}
			if (flag) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath));
				String line = bufferedReader.readLine();
				boolean flagForHeader = true;
				while (line != null) {
					if (!line.contains(","))
						throw new CensusAnalyserException("Incorrect Delimiter", ExceptionType.INCORRECT_DELIMITER);
					if (flagForHeader) {
						String[] headers = line.split(",");
						if (!(headers[0].equals("SrNo") && headers[1].equals("State Name") && headers[2].equals("TIN")
								&& headers[3].equals("StateCode")))
							throw new CensusAnalyserException("Incorrect Headers", ExceptionType.INCORRECT_HEADER);
						flagForHeader = false;
					}
					line = bufferedReader.readLine();
				}
				bufferedReader.close();
			}
			return getCount(stateCsvIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int count = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return count;
	}
}
