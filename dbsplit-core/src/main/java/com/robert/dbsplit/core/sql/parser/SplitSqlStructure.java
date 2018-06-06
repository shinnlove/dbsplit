package com.robert.dbsplit.core.sql.parser;

import org.springframework.util.StringUtils;

/**
 * 切分sql的数据结构。
 */
public class SplitSqlStructure {

	/**
	 * 查询、插入、更新、删除。
	 */
	public enum SqlType {
		SELECT, INSERT, UPDATE, DELETE
	};

	/** 切分数据类型：增、删、改、查 */
	private SqlType sqlType;

	private String dbName;
	private String tableName;

	/** 先前分隔符 */
	private String previousPart;
	/** 随后分隔符 */
	private String sebsequentPart;

	public SqlType getSqlType() {
		return sqlType;
	}

	public void setSqlType(SqlType sqlType) {
		this.sqlType = sqlType;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPreviousPart() {
		return previousPart;
	}

	public void setPreviousPart(String previousPart) {
		this.previousPart = previousPart;
	}

	public String getSebsequentPart() {
		return sebsequentPart;
	}

	public void setSebsequentPart(String sebsequentPart) {
		this.sebsequentPart = sebsequentPart;
	}

	public String getSplitSql(int dbNo, int tableNo) {
		if (sqlType == null || StringUtils.isEmpty(dbName)
				|| StringUtils.isEmpty(tableName)
				|| StringUtils.isEmpty(previousPart)
				|| StringUtils.isEmpty(sebsequentPart))
			throw new IllegalStateException(
					"The split SQL should be constructed after the SQL is parsed completely.");

		StringBuffer sb = new StringBuffer();
		sb.append(previousPart).append(" ");
		// 数据库名称_数据库编号：weact_2
		sb.append(dbName).append("_").append(dbNo);
		sb.append(".");
		// 表名_表编号：customer_info_3
		sb.append(tableName).append("_").append(tableNo).append(" ");
		sb.append(sebsequentPart);

		return sb.toString();
	}
}
