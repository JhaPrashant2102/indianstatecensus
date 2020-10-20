package com.cg.builder;

public class CensusAnalyserException extends Exception {
	enum ExceptionType{
		CENSUS_FILE_PROBLEM,INCORRECT_CLASS_TYPE,INCORRECT_DELIMITER,INCORRECT_HEADER;
	}
	
	ExceptionType type;
	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
	public CensusAnalyserException(String message, ExceptionType type,Throwable cause) {
		super(message,cause);
		this.type = type;
	}
}
