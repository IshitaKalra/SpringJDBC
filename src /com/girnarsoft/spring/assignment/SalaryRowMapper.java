package com.girnarsoft.spring.assignment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SalaryRowMapper implements RowMapper<Salary>{

	public Salary mapRow(ResultSet result, int arg1) throws SQLException {
		Salary salary=new Salary();
		salary.setId(result.getInt("id"));
		salary.setBaseSalary(result.getString("basesalary"));
		salary.setBonus(result.getString("bonus"));
		salary.setCtc(result.getString("ctc"));
		return salary;
	}
	

}
