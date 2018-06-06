package com.robert.dbsplit.core;

import java.util.List;

/**
 * 分表策略，可以配置成spring的bean。
 *
 * 一个`SplitTable`包含了这个表分片有多少个数据库和表信息。
 * 保存的`SplitNode`集合代表这个`SplitTable`会分布到多少个数据库实例上。
 */
public class SplitTable {

	private String dbNamePrefix;
	private String tableNamePrefix;

	private int dbNum;
	private int tableNum;

	/** 分表策略标记，默认垂直分表，可以在springbean中配置。 */
	private SplitStrategyType splitStrategyType = SplitStrategyType.VERTICAL;
	/** 具体分表策略类(根据标记来实例化) */
	private SplitStrategy splitStrategy;

	/** 多个分割结点，数据库实例。 */
	private List<SplitNode> splitNodes;

	/** 读写分离标记 */
	private boolean readWriteSeparate = true;

	/**
	 * spring的bean的init-method。
	 */
	public void init() {
		if (splitStrategyType == SplitStrategyType.VERTICAL)
			this.splitStrategy = new VerticalHashSplitStrategy(
					splitNodes.size(), dbNum, tableNum);
		else if (splitStrategyType == SplitStrategyType.HORIZONTAL)
			this.splitStrategy = new HorizontalHashSplitStrategy(
					splitNodes.size(), dbNum, tableNum);
	}

	public void setSplitStrategyType(String splitStrategyType) {
		this.splitStrategyType = SplitStrategyType.valueOf(splitStrategyType);
	}

	public String getDbNamePrefix() {
		return dbNamePrefix;
	}

	public void setDbNamePrefix(String dbNamePrifix) {
		this.dbNamePrefix = dbNamePrifix;
	}

	public String getTableNamePrefix() {
		return tableNamePrefix;
	}

	public void setTableNamePrefix(String tableNamePrifix) {
		this.tableNamePrefix = tableNamePrifix;
	}

	public int getDbNum() {
		return dbNum;
	}

	public void setDbNum(int dbNum) {
		this.dbNum = dbNum;
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public List<SplitNode> getSplitNodes() {
		return splitNodes;
	}

	public void setSplitNodes(List<SplitNode> splitNodes) {
		this.splitNodes = splitNodes;
	}

	public SplitStrategy getSplitStrategy() {
		return splitStrategy;
	}

	public void setSplitStrategy(SplitStrategy splitStrategy) {
		this.splitStrategy = splitStrategy;
	}

	public boolean isReadWriteSeparate() {
		return readWriteSeparate;
	}

	public void setReadWriteSeparate(boolean readWriteSeparate) {
		this.readWriteSeparate = readWriteSeparate;
	}
}
