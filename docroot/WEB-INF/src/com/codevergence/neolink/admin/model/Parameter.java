package com.codevergence.neolink.admin.model;

public class Parameter {
	private String param;
	private String value;
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Parameter [param=" + param + ", value=" + value + "]";
	}
}
