package com.spring.dao;

import java.util.List;

import com.spring.domain.Employees;


public interface EmployeesDao {
	public void registerEmployee(Employees emp);
	public Employees updateEmployee(int empId);
	public Employees searchEmployees(int empId);
	public void deleteEmployee(int empId);
	public List<Employees> viewAllEmployees();
}
