package com.robert.dbsplit.core;

import java.util.HashMap;
import java.util.List;

/**
 * 存放表分片的容器类。
 */
public class SplitTablesHolder {

	/** 拼接分隔符 */
	private static final String DB_TABLE_SEP = "$";

	/** 一个容器含有多个表分片 */
	private List<SplitTable> splitTables;

	/** key:数据库前缀+表名前缀, value:表分片 */
	private HashMap<String, SplitTable> splitTablesMapFull;

	/** key:表名前缀, value:表分片 */
	private HashMap<String, SplitTable> splitTablesMap;

	public SplitTablesHolder() {

	}

	public SplitTablesHolder(List<SplitTable> splitTables) {
		this.splitTables = splitTables;

		init();
	}

	/**
	 * 被spring的init-method调用。
	 */
	public void init() {
		splitTablesMapFull = new HashMap<String, SplitTable>();
		splitTablesMap = new HashMap<String, SplitTable>();

		for (int i = 0; i < splitTables.size(); i++) {
			SplitTable st = splitTables.get(i);

			// 使用数据库前缀、表前缀拼接成一个映射key，存放表分片。
			String key = constructKey(st.getDbNamePrefix(),
					st.getTableNamePrefix());
			splitTablesMapFull.put(key, st);

			splitTablesMap.put(st.getTableNamePrefix(), st);
		}
	}

	private String constructKey(String dbName, String tableName) {
		return dbName + DB_TABLE_SEP + tableName;
	}

	public SplitTable searchSplitTable(String dbName, String tableName) {
		return splitTablesMapFull.get(constructKey(dbName, tableName));
	}

	public SplitTable searchSplitTable(String tableName) {
		return splitTablesMap.get(tableName);
	}

	public List<SplitTable> getSplitTables() {
		return splitTables;
	}

	public void setSplitTables(List<SplitTable> splitTables) {
		this.splitTables = splitTables;
	}
}
