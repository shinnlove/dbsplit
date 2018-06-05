package com.robert.dbsplit.core;

public enum SplitStrategyType {

	/** 垂直业务拆分 */
	VERTICAL("vertical"),

	/** 水平分表拆分 */
	HORIZONTAL("horizontal");

	private String value;

	SplitStrategyType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}

}
