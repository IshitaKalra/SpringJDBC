package com.girnarsoft.spring.assignment;

public class Constants {
	public static interface Queries {
		public static final String SELECT_ALL = "select * from employee";
		public static final String DELETE_EMPLOYEE = "delete from employee where id = ?";
		public static final String DELETE_SALARY = "delete from salary where id = ?";
		public static final String FIND_EMPLOYEE = "SELECT * FROM employee WHERE id = ?";
		public static final String FIND_SALARY = "SELECT * FROM salary WHERE id = ?";
		public static final String SAVE_EMPLOYEE = "insert into employee(name , phone)values( ? , ? )";
		public static final String SAVE_SALARY = "insert into salary(basesalary , bonus , ctc )values( ? , ? , ? )";
		public static final String UPDATE_EMPLOYEE = "update employee set name= ? ,phone= ? where id= ?";
		public static final String UPDATE_SALARY = "update salary set basesalary= ? ,bonus= ?,ctc=  ?  where id= ?";

	}
	public static interface ErrorMessages{
		public static final String WRONG_INPUT = "Wrong Input!!";
		public static final String WRONG_INPUT_HEAD = "Wrong ";
		public static final String WRONG_INPUT_ID = "Employee with this Id does not exist";
		public static final String WRONG_INPUT_SALARY = "Wrong ";
		public static final String INVALID_PHONE = "Invalid Phone Format!! \nPlease Enter Again";
		public static final String INVALID_NAME = "Invalid Name Format!! \nPlease Enter Again";
		public static final String INVALID_SALARY = "Invalid salary Format!! \nPlease Enter Again";
		public static final String INVALID_ID= "Invalid Id Format!! \nPlease Enter Again";
	}
	public static interface Input{
		public static final String WELCOME_TO_GIRNARSOFT = "WELCOME TO GIRNARSOFT!!!";
		public static final String MENU = "\nPress 1 to find details of an employees\n" + 
									"press 2 to remove an employee\n" + 
									"press 3 to add a new employee\"\n" + 
									"press 4 to find the salary structure\"\n" + 
									"press 5 to edit details of the employee\"\n" + 
									"press 6 to edit salary details of the employee\"\n" + 
									"press 7 to find details of all employees\"\n" + 
									"press 8 to exit";
		public static final String PHONE_INPUT = "Enter Phone number of the employee";
		public static final String NAME_INPUT = "Enter the name of the employe";
		public static final String BASE_SALARY_INPUT = "Enter the Base Salary of the employee";
		public static final String BONUS_INPUT = "Enter the bonus of the employee";
		public static final String CTC_INPUT = "Enter the CTC of the employe";
		public static final String ID_INPUT = "Enter the id of the employee";
		public static final String EXIT_STATEMENT = "Press 0 for Menu";
	}

}
