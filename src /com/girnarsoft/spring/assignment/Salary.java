package com.girnarsoft.spring.assignment;

public class Salary {
	
	private int id;
	private String baseSalary;
	private String bonus;
	private String ctc;
	
	public Salary() {
		super();
	}
	public Salary(int id, String baseSalary, String bonus, String ctc) {
		super();
		this.id = id;
		this.baseSalary = baseSalary;
		this.bonus = bonus;
		this.ctc = ctc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(String baseSalary) {
		this.baseSalary = baseSalary;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public String getCtc() {
		return ctc;
	}
	public void setCtc(String ctc) {
		this.ctc = ctc;
	}
	public String toString() {
		return "Salary [id=" + id + ", baseSalary=" + baseSalary + ", bonus=" + bonus + ", ctc=" + ctc + "]";
	}
	

}
