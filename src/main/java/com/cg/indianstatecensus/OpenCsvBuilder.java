package com.cg.indianstatecensus;

import java.io.Reader;
import java.util.Iterator;

import com.cg.indianstatecensus.CensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class OpenCsvBuilder <E> implements ICSVBuilder{

	public Iterator<E> getCsvFileIterator(Reader reader, Class typeClass) throws CsvException {
		try{
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
			csvToBeanBuilder.withType(typeClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CsvException();
		}
	}

}
