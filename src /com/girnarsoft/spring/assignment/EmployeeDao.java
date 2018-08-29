package com.girnarsoft.spring.assignment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gspl
 *
 */

@Component
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 
	 * @return
	 */
	public int findLastId() throws SQLException {
		List<Employee> employeeObject = new ArrayList<Employee>();
		employeeObject = jdbcTemplate.query(Constants.Queries.SELECT_ALL, new EmployeeRowMapper());
		int size = employeeObject.size();
		return employeeObject.get(size - 1).getId();

	}

	/**
	 * 
	 * @return prints details of all employees
	 */
	public List<Employee> viewAll() throws SQLException {
		List<Employee> employeeObject = new ArrayList<Employee>();
		employeeObject = jdbcTemplate.query(Constants.Queries.SELECT_ALL, new EmployeeRowMapper());
		return employeeObject;
	}

	/**
	 * runs Query to insert employee
	 * 
	 * @param name
	 * @param phone
	 * @return
	 */

	public void saveEmployee(String name, String phone) throws SQLException {
		Object[] args = new Object[] { name, phone };
		jdbcTemplate.update(Constants.Queries.SAVE_EMPLOYEE, args);
	}

	/**
	 * 
	 * @param baseSalary
	 * @param bonus
	 * @param ctc
	 */

	public void saveSalary(String baseSalary, String bonus, String ctc) throws SQLException {
		Object[] args = new Object[] { baseSalary, bonus, ctc };
		jdbcTemplate.update(Constants.Queries.SAVE_SALARY, args);
	}

	/**
	 * runs query to update salary features of Employee
	 * 
	 * @param id
	 * @param baseSalary
	 * @param bonus
	 * @param ctc
	 */

	public void updateEmployeeSalary(int id, String baseSalary, String bonus, String ctc) throws SQLException {
		Object[] args = new Object[] { baseSalary, bonus, ctc, id };
		jdbcTemplate.update(Constants.Queries.UPDATE_SALARY, args);
	}

	/**
	 * runs Query to update employee details
	 * 
	 * @param id
	 * @param name
	 * @param phone
	 */

	public void updateEmployee(int id, String name, String phone) throws SQLException {
		Object[] args = new Object[] { name, phone, id };
		jdbcTemplate.update(Constants.Queries.UPDATE_EMPLOYEE, args);
	}

	/**
	 * Finds the Employee with the input id
	 * 
	 * @param id
	 * @return
	 */

	public Employee findEmployee(int id) throws SQLException {
		List<Employee> employeeObject = new ArrayList<Employee>();
		Object[] args = new Object[] { id };
		employeeObject = jdbcTemplate.query(Constants.Queries.FIND_EMPLOYEE, args, new EmployeeRowMapper());
		return employeeObject.get(0);
	}

	/**
	 * Finds the salary structure of the employee with the given id
	 * 
	 * @param id
	 * @return
	 */

	public Salary findSalary(int id) throws SQLException {
		List<Salary> salaryObject = new ArrayList<Salary>();
		Object[] args = new Object[] { id };
		salaryObject = jdbcTemplate.query(Constants.Queries.FIND_SALARY, args, new SalaryRowMapper());
		return salaryObject.get(0);
	}

	/**
	 * checks if the employee with the specific id is present in the database
	 * 
	 * @param id
	 * @return
	 */
	public List<Employee> listEmployee(int id) throws SQLException {
		List<Employee> employeeObject = new ArrayList<Employee>();
		Object[] args = new Object[] { id };
		employeeObject = jdbcTemplate.query(Constants.Queries.FIND_EMPLOYEE, args, new EmployeeRowMapper());
		return employeeObject;
	}

	/**
	 * Removes the Employee with the input id
	 * 
	 * @param id
	 */

	public void deleteEmployee(int id) throws SQLException {
		Object[] args = new Object[] { id };
		jdbcTemplate.update(Constants.Queries.DELETE_EMPLOYEE, args);
		jdbcTemplate.update(Constants.Queries.DELETE_SALARY, args);
	}

}
