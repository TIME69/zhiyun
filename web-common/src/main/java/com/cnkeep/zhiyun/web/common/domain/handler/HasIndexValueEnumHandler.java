package com.cnkeep.zhiyun.web.common.domain.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;



/**
 * @Description mybatis枚举类型转换器
 * @author <a href="mailto:zhangleili@wxchina.com">LeiLi.Zhang</a>
 * @Date 2017年12月3日
 * @Version 0.0.0
 */
public class HasIndexValueEnumHandler<E extends Enum<E> & HasIndexValue> extends BaseTypeHandler<E> {
	/**
	 * 枚举泛型类
	 */
	private Class<E> type;

	/**
	 * 当前枚举类中的所有枚举实例
	 */
	private E[] enums;

	/**
	 * 提供枚举类实例的映射关系，便于查找
	 */
	private Map<Integer, E> valueMapper = new ConcurrentHashMap<Integer, E>();

	public HasIndexValueEnumHandler(Class<E> type) {
		if (null == type) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
		this.enums = type.getEnumConstants();
		for (E e : enums) {
			this.valueMapper.put(e.getIndex(), e);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getIndex());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Object value = rs.getObject(columnName);
		if (rs.wasNull()) {
			return null;
		}
		return convertOrException(value);
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Object value = rs.getObject(columnIndex);
		if (rs.wasNull()) {
			return null;
		}

		return convertOrException(value);
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Object value = cs.getObject(columnIndex);

		if (cs.wasNull()) {
			return null;
		}
		return convertOrException(value);
	}

	private E convertOrException(Object value) {
		E e = this.valueMapper.get((Integer) value);
		if (null == e) {
			throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getSimpleName());
		} else {
			return e;
		}
	}
}
