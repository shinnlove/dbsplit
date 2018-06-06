package com.robert.dbsplit.core;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 分割结点数据结构，数据库实例。
 */
public class SplitNode {
	/** 主库 */
	private JdbcTemplate masterTemplate;
	/** 从库 */
	private List<JdbcTemplate> slaveTemplates;

	/** 原子变量：实现轮询从库 */
	private AtomicLong iter = new AtomicLong(0);

	public SplitNode() {
	}

	public SplitNode(JdbcTemplate masterTemplate,
			List<JdbcTemplate> slaveTemplates) {
		this.masterTemplate = masterTemplate;
		this.slaveTemplates = slaveTemplates;
	}

	public SplitNode(JdbcTemplate masterTemplate,
			JdbcTemplate... slaveTemplates) {
		this.masterTemplate = masterTemplate;
		this.slaveTemplates = Arrays.asList(slaveTemplates);
	}

	public JdbcTemplate getMasterTemplate() {
		return masterTemplate;
	}

	public void setMasterTemplate(JdbcTemplate masterTemplate) {
		this.masterTemplate = masterTemplate;
	}

	public List<JdbcTemplate> getSlaveTemplates() {
		return slaveTemplates;
	}

	public void setSlaveTemplates(List<JdbcTemplate> slaveTemplates) {
		this.slaveTemplates = slaveTemplates;
	}

	public void addSalveTemplate(JdbcTemplate jdbcTemplate) {
		this.slaveTemplates.add(jdbcTemplate);
	}

	public void removeSalveTemplate(JdbcTemplate jdbcTemplate) {
		this.slaveTemplates.remove(jdbcTemplate);
	}

	/**
	 * 轮询读取从库。
	 *
	 * @return
	 */
	public JdbcTemplate getRoundRobinSlaveTempate() {
		long iterValue = iter.incrementAndGet();

		// Still race condition, but it doesn't matter
		if (iterValue == Long.MAX_VALUE)
			iter.set(0);

		return slaveTemplates.get((int) iterValue % slaveTemplates.size());
	}

}
