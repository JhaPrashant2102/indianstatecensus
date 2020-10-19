package com.cg.indianstatecensus;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder <E>{
	public Iterator<E> getCsvFileIterator(Reader reader, Class typeClass) throws CensusAnalyserException;
}
