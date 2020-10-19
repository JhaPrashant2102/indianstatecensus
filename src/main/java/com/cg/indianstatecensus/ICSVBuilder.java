package com.cg.indianstatecensus;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.exceptions.CsvException;

public interface ICSVBuilder <E>{
	public Iterator<E> getCsvFileIterator(Reader reader, Class typeClass) throws CsvException;
	
}
