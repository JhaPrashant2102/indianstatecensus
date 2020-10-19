package com.cg.indianstatecensus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
	public int loadIndiaCensusData(String csvFilePath, boolean flag) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			Iterator<IndiaCensusCSV> censusCsvIterator = 	new OpenCsvBuilder().getCsvFileIterator(reader, IndiaCensusCSV.class);
			if (flag) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath));
				String line = bufferedReader.readLine();
				boolean flagForHeader = true;
				while (line != null) {
					if (!line.contains(","))
						throw new CensusAnalyserException("Incorrect Delimiter",
								CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER);
					if (flagForHeader) {
						String[] headers = line.split(",");
						if (!(headers[0].equals("State") && headers[1].equals("Population")
								&& headers[2].equals("AreaInSqKm") && headers[3].equals("DensityPerSqKm")))
							throw new CensusAnalyserException("Incorrect Headers",
									CensusAnalyserException.ExceptionType.INCORRECT_HEADER);
						flagForHeader = false;
					}
					line = bufferedReader.readLine();
				}
				bufferedReader.close();
			}
			return getCount(censusCsvIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadIndiaStateCodeData(String csvFilePath, boolean flag) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			Iterator<CSVStates> stateCsvIterator = new OpenCsvBuilder().getCsvFileIterator(reader, CSVStates.class);
			if (flag) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath));
				String line = bufferedReader.readLine();
				boolean flagForHeader = true;
				while (line != null) {
					if (!line.contains(","))
						throw new CensusAnalyserException("Incorrect Delimiter",
								CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER);
					if (flagForHeader) {
						String[] headers = line.split(",");
						if (!(headers[0].equals("SrNo") && headers[1].equals("State Name") && headers[2].equals("TIN")
								&& headers[3].equals("StateCode")))
							throw new CensusAnalyserException("Incorrect Headers",
									CensusAnalyserException.ExceptionType.INCORRECT_HEADER);
						flagForHeader = false;
					}
					line = bufferedReader.readLine();
				}
				bufferedReader.close();
			}
			return getCount(stateCsvIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = ()->iterator;
		int count = (int)StreamSupport.stream(csvIterable.spliterator(), false).count();
		return count;
	}
}
