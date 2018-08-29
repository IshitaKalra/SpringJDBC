package com.girnarsoft.spring.assignment;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gspl
 *
 */

@Component
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	/**
	 * 
	 * @param employeeDao
	 * @throws SQLException
	 */
	public int findAdditionId() throws SQLException {
		return employeeDao.findLastId();
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * 
	 * @param inputId
	 * @return
	 * @throws SQLException
	 */
	public boolean checkEmployee(int inputId) throws SQLException {
		return !employeeDao.listEmployee(inputId).isEmpty();

	}

	/**
	 * 
	 * @param inputId
	 * @return
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public boolean checkEmployee(String inputId) throws NumberFormatException, SQLException {
		return checkEmployee(Integer.parseInt(inputId));

	}

	/**
	 * checks if the employee with that Id exists provides Employee object to print
	 * its details
	 * 
	 * @param employeeId
	 * @return
	 * @throws InvalidInputException
	 * @throws SQLException
	 */

	public Employee searchEmployee(int employeeId) throws InvalidInputException, SQLException {

		if (checkEmployee(employeeId))
			return employeeDao.findEmployee(employeeId);
		else
			throw new InvalidInputException(Constants.ErrorMessages.WRONG_INPUT_ID);

	}

	/**
	 * Add an employee object Add the corresponding Salary object in the database
	 * 
	 * @param name
	 * @param phone
	 * @param baseSalary
	 * @param bonus
	 * @param ctc
	 * @throws InvalidInputException
	 * @throws SQLException
	 */

	public void addEmployee(String name, String phone, String baseSalary, String bonus, String ctc)
			throws InvalidInputException, SQLException {
		employeeDao.saveSalary(baseSalary, bonus, ctc);
		employeeDao.saveEmployee(name, phone);

	}

	/**
	 * checks if Employee with the input id exists finds salary structure details
	 * for the employee
	 * 
	 * @param inputId
	 * @return
	 * @throws InvalidInputException
	 * @throws SQLException
	 */

	public Salary findSalary(int inputId) throws InvalidInputException, SQLException {
		if (checkEmployee(inputId))
			return employeeDao.findSalary(inputId);
		else
			throw new InvalidInputException(Constants.ErrorMessages.WRONG_INPUT_ID);
	}

	/**
	 * checks if the given id exists removes the employee with the given id
	 * 
	 * @param employeeId
	 * @throws InvalidInputException
	 * @throws SQLException
	 */

	public void removeEmployee(int employeeId) throws InvalidInputException, SQLException {
		if (!checkEmployee(employeeId))
			throw new InvalidInputException(Constants.ErrorMessages.WRONG_INPUT_ID);
		else {
			employeeDao.deleteEmployee(employeeId);
		}
	}

	/**
	 * checks if the given id exists updates the salary structure
	 * 
	 * @param id
	 * @param baseSalary
	 * @param bonus
	 * @param ctc
	 * @throws InvalidInputException
	 * @throws SQLException
	 */

	public void editEmployeeSalary(int id, String baseSalary, String bonus, String ctc)
			throws InvalidInputException, SQLException {
		if (!checkEmployee(id))
			throw new InvalidInputException(Constants.ErrorMessages.WRONG_INPUT_ID);
		else {
			employeeDao.updateEmployeeSalary(id, baseSalary, bonus, ctc);
		}

	}

	/**
	 * checks if the given id exists updates details
	 * 
	 * @param id
	 * @param name
	 * @param phone
	 * @throws InvalidInputException
	 * @throws SQLException
	 */

	public void editEmployee(int id, String name, String phone) throws InvalidInputException, SQLException {
		if (!checkEmployee(id))
			throw new InvalidInputException(Constants.ErrorMessages.WRONG_INPUT_ID);
		else {
			employeeDao.updateEmployee(id, name, phone);
		}

	}

	/**
	 * finds the List of all Employee Objects
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Employee> viewEmployees() throws SQLException {
		List<Employee> employees = employeeDao.viewAll();
		return employees;

	}

	/**
	 * validates if the Id format is correct
	 * 
	 * @param id
	 * @return
	 */

	public boolean validateId(String id) {
		return id.matches("\\d*");
	}

	/**
	 * validates name format
	 * 
	 * @param name
	 * @return
	 */

	public boolean validateName(String name) {
		return name.matches("[a-zA-Z][a-zA-Z ]*");
	}

	/**
	 * validate salary format
	 * 
	 * @param salary
	 * @return
	 */

	public boolean validateSalary(String salary) {
		return salary.matches("\\d*");

	}

	/**
	 * check if phone number is in correct format
	 * 
	 * @param phone
	 * @return
	 */

	public boolean validatePhone(String phone) {
		return (phone.matches("\\d*") && phone.length() == 10);
	}

}
