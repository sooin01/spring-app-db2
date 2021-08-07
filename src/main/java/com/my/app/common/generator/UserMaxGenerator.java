package com.my.app.common.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

public class UserMaxGenerator implements IdentifierGenerator, Configurable {

	@Override
	public void configure(Type type, Properties params, Dialect d) throws MappingException {

	}

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Integer seq = 1;

		String sql = "select max(seq) from User";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = session.connection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				seq = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			throw new HibernateException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}

		return seq;
	}

}
