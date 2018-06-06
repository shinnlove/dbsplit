package com.robert.dbsplit.core;

/**
 * 水平哈希拆分策略，即分表策略。
 */
public class HorizontalHashSplitStrategy implements SplitStrategy {

	/** 数据库实例数(一个实例含有若干个不同名的数据库:ds_0,ds_1,...) */
	private int portNum;
	/** 数据库数(对应一个spring的JdbcTemplate) */
	private int dbNum;
	/** 表数量(数据库实例数 × 数据库数量 × 每库表数量) */
	private int tableNum;

	public HorizontalHashSplitStrategy() {

	}

	public HorizontalHashSplitStrategy(int portNum, int dbNum, int tableNum) {
		this.portNum = portNum;
		this.dbNum = dbNum;
		this.tableNum = tableNum;
	}

	public int getNodeNo(Object splitKey) {
		return getDbNo(splitKey) / dbNum;
	}

	public int getDbNo(Object splitKey) {
		return getTableNo(splitKey) / tableNum;
	}

	/**
	 * 获取表编号，公式：
	 *
	 * 哈希Code %  (实例数 × 数据库数 × 表数量)
	 *
	 * @param splitKey
	 * @return
	 */
	public int getTableNo(Object splitKey) {
		int hashCode = calcHashCode(splitKey);
		return hashCode % (portNum * dbNum * tableNum);
	}

	/**
	 * 获取分割键的正向HashCode。
	 *
	 * @param splitKey
	 * @return
	 */
	private int calcHashCode(Object splitKey) {
		int hashCode = splitKey.hashCode();
		if (hashCode < 0)
			hashCode = -hashCode;

		return hashCode;
	}
}
