package com.robert.dbsplit.core;

/**
 * 垂直拆分策略。
 *
 * 策略：不停的除以实例数、数据库数量，最后取余数据库数量或表数量。
 */
public class VerticalHashSplitStrategy implements SplitStrategy {

	/** 实例数、不同实例运行在不同端口号上 */
	private int portNum;
	/** 数据库数量 */
	private int dbNum;
	/** 表数量 */
	private int tableNum;

	public VerticalHashSplitStrategy() {

	}

	public VerticalHashSplitStrategy(int portNum, int dbNum, int tableNum) {
		this.portNum = portNum;
		this.dbNum = dbNum;
		this.tableNum = tableNum;
	}

	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public int getDbNum() {
		return dbNum;
	}

	public void setDbNum(int dbNum) {
		this.dbNum = dbNum;
	}

	public int getNodeNo(Object splitKey) {
		int hashCode = calcHashCode(splitKey);
		return hashCode % portNum;
	}

	public int getDbNo(Object splitKey) {
		int hashCode = calcHashCode(splitKey);
		return hashCode / portNum % dbNum;
	}

	public int getTableNo(Object splitKey) {
		int hashCode = calcHashCode(splitKey);
		return hashCode / portNum / dbNum % tableNum;
	}

	private int calcHashCode(Object splitKey) {
		int hashCode = splitKey.hashCode();
		return hashCode;
	}
}
