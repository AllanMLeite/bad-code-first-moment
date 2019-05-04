package br.com.cc.report;

import java.util.ArrayList;
import java.util.List;

public class ReportResult {

	private String text;
	private List<String> constraints = new ArrayList<String>();
	
	public ReportResult(String text, List<String> constraints) {
		this.text = text;
		this.constraints = constraints;
	}

	public List<String> getConstraints() {
		return constraints;
	}
	
	public String getText() {
		return text;
	}
}
