package com.cg.builder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.exceptions.CsvException;

public interface ICSVBuilder <E>{
	public Iterator<E> getCsvFileIterator(Reader reader, Class typeClass) throws CsvException;
	public List<E> getCSVFileList(Reader reader, Class typeClass) throws CsvException;
}
