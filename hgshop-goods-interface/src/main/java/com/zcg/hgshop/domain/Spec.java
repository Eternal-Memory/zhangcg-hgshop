package com.zcg.hgshop.domain;

import java.io.Serializable;
import java.util.List;

public class Spec implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String specName;
	
	private List<SpecOption> options;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public List<SpecOption> getOptions() {
		return options;
	}

	public void setOptions(List<SpecOption> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Spec [id=" + id + ", specName=" + specName + ", options=" + options + "]";
	}
	
	
}
