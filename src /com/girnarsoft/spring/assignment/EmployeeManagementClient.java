package com.girnarsoft.spring.assignment;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author gspl
 *
 */

public class EmployeeManagementClient {


	public  final Scanner SCANNER = new Scanner(System.in);

	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		new EmployeeManagementClient(). options();

	}

	private void options() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		int userInput = 0;
		boolean flag = false;
		System.out.println(Constants.Input.WELCOME_TO_GIRNARSOFT);
		employeeService = (EmployeeService) appContext.getBean("employeeService");

		do {

			System.out.println(Constants.Input.MENU);
			String input = SCANNER.nextLine();

			try {
				userInput = Integer.parseInt(input);
				if (userInput > 0 && userInput < 9)
					flag = true;
				else
					System.out.println("Please enter within range!!!");
			} catch (Exception e) {
				System.out.println(Constants.ErrorMessages.WRONG_INPUT);
			}
			if (flag == true) {

				switch (userInput) {

				case 1:
					getEmployee();
					break;

				case 2:
					removeEmployee();
					break;

				case 3:
					addEmployee();
					break;

				case 4:
					getSalaryStructure();
					break;

				case 5:

					editEmployeeDetails();
					break;

				case 6:
					editSalaryStructure();
					break;

				case 7:
					try {
						System.out.println(employeeService.viewEmployees());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;

				}
			}
		} while (userInput != 8);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws ExitException
	 */

	public String findValidName(String name) throws ExitException {
		while (!employeeService.validateName(name)) {
			System.out.println(Constants.ErrorMessages.INVALID_NAME);
			System.out.println(Constants.Input.EXIT_STATEMENT);
			name = SCANNER.nextLine();
			if (name.equals("0"))
				throw new ExitException();
		}
		return name;
	}

	/**
	 * 
	 * @param phone
	 * @return
	 * @throws ExitException
	 */

	public String findValidPhone(String phone) throws ExitException {
		while (!employeeService.validatePhone(phone)) {
			System.out.println(Constants.ErrorMessages.INVALID_PHONE);
			System.out.println(Constants.Input.EXIT_STATEMENT);
			phone = SCANNER.nextLine();
			if (phone.equals("0"))
				throw new ExitException();
		}
		return phone;
	}

	/**
	 * 
	 * @param salary
	 * @return
	 * @throws ExitException
	 */

	public String findValidSalary(String salary) throws ExitException {
		while (!employeeService.validateSalary(salary)) {
			System.out.println(Constants.ErrorMessages.INVALID_SALARY);
			System.out.println(Constants.Input.EXIT_STATEMENT);
			salary = SCANNER.nextLine();
			if (salary.equals("0"))
				throw new ExitException();
		}
		return salary;
	}

	/**
	 * 
	 * @param inputId
	 * @return
	 * @throws ExitException
	 */

	public int findValidId(String inputId) throws ExitException {
		while (!employeeService.validateId(inputId)) {
			System.out.println(Constants.ErrorMessages.INVALID_ID);
			System.out.println(Constants.Input.EXIT_STATEMENT);
			inputId = SCANNER.nextLine();
			if (inputId.equals("0"))
				throw new ExitException();
		}
		return Integer.parseInt(inputId);
	}

	/**
	 * verifies id finds the details of the employee with the input id
	 */

	public void getEmployee() {
		System.out.println("Enter the Id of the employee");
		try {

			int intId = findValidId(SCANNER.nextLine());
			Employee employee = employeeService.searchEmployee(intId);
			System.out.println(employee);

		} catch (ExitException e) {

		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(Constants.ErrorMessages.WRONG_INPUT);
		}

	}

	/**
	 * verifies Id removes the employee with the input id from the database
	 */

	public void removeEmployee() {
		System.out.println("Enter the Id of the employee you wish to remove");
		try {

			int intId = findValidId(SCANNER.nextLine());
			employeeService.removeEmployee(intId);
			System.out.println("Employee with the Id " + intId + " is succesfully removed");

		} catch (ExitException e) {

		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(Constants.ErrorMessages.WRONG_INPUT);
		}
	}

	/**
	 * validates all inputs adds employee to the database
	 */

	public void addEmployee() {
		System.out.println(Constants.Input.NAME_INPUT);
		try {
			String name = findValidName(SCANNER.nextLine());

			System.out.println(Constants.Input.PHONE_INPUT);
			String phone = findValidPhone(SCANNER.nextLine());

			System.out.println(Constants.Input.BASE_SALARY_INPUT);
			String baseSalary = findValidSalary(SCANNER.nextLine());

			System.out.println(Constants.Input.BONUS_INPUT);
			String bonus = findValidSalary(SCANNER.nextLine());

			System.out.println(Constants.Input.CTC_INPUT);
			String ctc = findValidSalary(SCANNER.nextLine());

			employeeService.addEmployee(name, phone, baseSalary, bonus, ctc);
			System.out.println("Emploee " + name + " is succesfully added in database!!!\n");
			int additionId = employeeService.findAdditionId();
			System.out.println("Id of " + name + " is " + additionId + "");
		} catch (ExitException e) {

		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(Constants.ErrorMessages.WRONG_INPUT);
		}
	}

	/**
	 * retrieves the salary structure verifies the input id
	 */

	public void getSalaryStructure() {
		System.out.println("Enter the Id of the employee whose Salary Structure you want to see");
		try {

			int intInputId = findValidId(SCANNER.nextLine());
			Salary salary = employeeService.findSalary(intInputId);
			System.out.println(salary);
		} catch (ExitException e) {

		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(Constants.ErrorMessages.WRONG_INPUT);
		}
	}

	/**
	 * inputs to edit employee details validating all fields
	 */

	public void editEmployeeDetails() {
		try {
			System.out.println("Enter the Id of the Employee whose details you wish to edit");
			int intId = validateEditId(SCANNER.nextLine());
			System.out.println("Enter the updated Name of the Employee");
			String name = findValidName(SCANNER.nextLine());

			System.out.println("Enter the updated phone number");
			String phone = findValidPhone(SCANNER.nextLine());

			employeeService.editEmployee(intId, name, phone);
			System.out.println("Employee Details have been edited successfully!!\n");

		} catch (ExitException e) {

		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(Constants.ErrorMessages.WRONG_INPUT);
		}
	}

	/**
	 * Inputs to edit salary structure validating all fields
	 */

	public void editSalaryStructure() {
		try {
			System.out.println("Enter the Id of the Employee whose salary details you wish to edit");
			int intId = validateEditId(SCANNER.nextLine());
			System.out.println("Enter the updated Base Salary of the employee");
			String baseSalary = findValidSalary(SCANNER.nextLine());

			System.out.println("Enter the updated bonus of the employee");
			String bonus = findValidSalary(SCANNER.nextLine());

			System.out.println("Enter the updated CTC of the employee");
			String ctc = findValidSalary(SCANNER.nextLine());

			employeeService.editEmployeeSalary(intId, baseSalary, bonus, ctc);
			System.out.println("Salary Structure is updated successfully!!!\n");

		} catch (ExitException e) {

		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(Constants.ErrorMessages.WRONG_INPUT);
		}
	}

	public int validateEditId(String editId) throws NumberFormatException, SQLException {
		while (!employeeService.validateId(editId) || !employeeService.checkEmployee(editId)) {
			if (!employeeService.validateId(editId))
				System.out.println("Id format Incorrect!!! \nPlease Enter Again");
			else
				System.out.println(
						"Employee with this Id is not present in the database!! \nPlease enter the already Existing Employee Id");
			editId = SCANNER.nextLine();
		}

		return Integer.parseInt(editId);
	}
}
