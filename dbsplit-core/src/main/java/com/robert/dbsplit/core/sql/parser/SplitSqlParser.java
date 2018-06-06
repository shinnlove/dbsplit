package com.robert.dbsplit.core.sql.parser;

/**
 * sql解析接口
 */
public interface SplitSqlParser {
	/** 放入接口的变量自动是static、final的 */
	public static final SplitSqlParser INST = new SplitSqlParserDefImpl();

	/**
	 * 将sql解析为分割的结构。
	 *
	 * 是将原始SQL解析出数据库名、表名和前后缀名称。
	 *
	 * @param sql
	 * @return
	 */
	public SplitSqlStructure parseSplitSql(String sql);
}
