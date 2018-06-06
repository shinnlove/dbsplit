package com.robert.dbsplit.core;

/**
 * 切分策略。
 */
public interface SplitStrategy {

	/**
	 * 获取结点编号
	 *
	 * @param splitKey
	 * @param <K>
	 * @return
	 */
	public <K> int getNodeNo(K splitKey);

	/**
	 * 获取数据库编号
	 *
	 * @param splitKey
	 * @param <K>
	 * @return
	 */
	public <K> int getDbNo(K splitKey);

	/**
	 * 获取表编号。
	 *
	 * @param splitKey
	 * @param <K>
	 * @return
	 */
	public <K> int getTableNo(K splitKey);

}
