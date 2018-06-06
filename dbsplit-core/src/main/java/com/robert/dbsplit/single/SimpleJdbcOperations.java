package com.robert.dbsplit.single;

import java.util.List;

/**
 * 封装Jdbc基础操作，让所有操作都针对实体类进行的接口。
 */
public interface SimpleJdbcOperations {
	public <T> void insert(T bean);

	public <T> void update(T bean);

	public <T> void delete(long id, Class<T> clazz);

	public <T> T get(long id, final Class<T> clazz);

	public <T> T get(String name, Object value, final Class<T> clazz);

	public <T> List<T> search(final T bean);

	public <T> List<T> search(String sql, Object[] params, final Class<T> clazz);
}
