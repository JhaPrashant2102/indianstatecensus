package com.cg.indianstatecensus;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {
	@CsvBindByName(column = "State")
	private String stateName;

	@CsvBindByName(column = "Population")
	private long population;

	@CsvBindByName(column = "AreaInSqKm")
	private int area;

	@CsvBindByName(column = "DensityPerSqKm")
	private int density;

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getDensity() {
		return density;
	}

	public void setDensity(int density) {
		this.density = density;
	}

}
