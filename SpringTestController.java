package com.spring.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.EmployeesDao;
import com.spring.domain.Employees;

/**
 * @author chary
 * Controller class that handles all the requests coming from the browser 
 */
@Controller
@RequestMapping(value = "/Employee")
public class SpringTestController {
	//Autowire EmployeeDao object
	@Autowired
	public EmployeesDao empDao;

	private static final Logger logger = Logger.getLogger(SpringTestController.class);
	/**
	 * Method to load register employee page
	 * @param emp
	 * @return
	 */
	@RequestMapping("/springtest")
	public ModelAndView springTest(@ModelAttribute("employees") Employees emp) {
		logger.info("springTest method");;
		if (emp != null && emp.getEmpId() == 0) {
			Employees empqq = new Employees();
			return new ModelAndView("SpringTest", "employees", empqq);
		}

		return new ModelAndView("SpringTest", "employees", emp);

	}

	
	/**
	 * This method is called when the user submits the employee application
	 * @param employees
	 * @return
	 */
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public ModelAndView registerEmployee(@ModelAttribute Employees employees) {
		try{
		Employees emp = new Employees();
		emp.setEmpId(employees.getEmpId());
		emp.setFirstName(employees.getFirstName());
		emp.setLastName(employees.getLastName());
		emp.setAddress(employees.getAddress());
		emp.setEmailaddress(employees.getEmailaddress());
		emp.setSalary(employees.getSalary());
		emp.setDesignation(employees.getDesignation());

		empDao.registerEmployee(emp);
		}
		catch(Exception e){
			logger.info("Exception in registerEmployee method in SpringTestController class "
					+e.getMessage());
		}
		return new ModelAndView("redirect:/viewEmployees.htm");

	}

	/**
	 * Thsi method retreives all the employees and forwards the results to summary page
	 * @return
	 */
	@RequestMapping("/viewEmployees")
	public ModelAndView getEmployees() {
		List<Employees> allEmp = new ArrayList<Employees>();
		try{
			allEmp = empDao.viewAllEmployees();
		}
		catch(Exception e){
			logger.info("Exception in getEmployees method in SpringTestController class "
					+e.getMessage());
		}
		return new ModelAndView("viewAllEmployees", "list", allEmp);
	}

	/**
	 * This method is called when a user clicks delete icon on the summary page
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deletedetails", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request, Model model) {
		try{
		empDao.deleteEmployee(Integer.parseInt(request.getParameter("empId")));
		}
		catch(Exception e){
			logger.info("Exception in deleteEmployee method in SpringTestController class "
					+e.getMessage());
		}
		return new ModelAndView("redirect:/viewEmployees.htm");
	}

	
	/**
	 * This method is called when a user clicks edit icon on the summary page
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editdetails", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request, Model model) {
		
		Employees emp = new Employees();
		try{
		 emp = empDao.searchEmployees(Integer.parseInt(request.getParameter("empId")));
		model.addAttribute(emp);
		}
		catch(Exception e){
			logger.info("Exception in editEmployee method in SpringTestController class "
					+e.getMessage());
		}

		return new ModelAndView("redirect:/springtest.htm", "employees", emp);

	}

}
