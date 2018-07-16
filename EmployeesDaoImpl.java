package com.spring.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.Employees;
@Repository
public class EmployeesDaoImpl implements EmployeesDao{
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	/* This method takes the employee object and inserts into EMPLOYEES table
	 *  (non-Javadoc)
	 * @see com.spring.dao.EmployeesDao#registerEmployee(com.spring.domain.Employees)
	 */
	@Override
	public void registerEmployee(Employees emp) {
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.openSession();
		int empId = getManifestNumber();
		emp.setEmpId(empId);

		Transaction tx = session.beginTransaction();
		session.persist(emp);
		tx.commit();
		session.close();
		
	}

	@Override
	public Employees updateEmployee(int empId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		
		return null;
	}

	@Override
	@Transactional
	public Employees searchEmployees(int empId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		Employees employees = (Employees) session.get(Employees.class,new Integer(empId));
		/*Employees employees = (Employees) session.createCriteria(Employees.class, 
				"SELECT * FROM EMPLOYEES WHERE EMP_ID="+empId);
		*/
		
		return employees;
	}

	/* This method deletes the employee based on the employeeId
	 * (non-Javadoc)
	 * @see com.spring.dao.EmployeesDao#deleteEmployee(int)
	 */
	@Override
	public void deleteEmployee(int empId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Employees emp = new Employees();
		emp.setEmpId(empId);
		session.delete(emp);
		tx.commit();
		session.close();
		
		
	}
	
	/* This method fetches all the employees
	 * (non-Javadoc)
	 * @see com.spring.dao.EmployeesDao#viewAllEmployees()
	 */
	public List<Employees> viewAllEmployees(){
		Session session = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Employees> employeesList = (List<Employees>) session.createCriteria(Employees.class, 
				"SELECT * FROM EMPLOYEES").list();
		
		session.close();
		return employeesList;
		
	}
	
	/**
	 * This method gets the auto assigned employee id
	 * @return
	 */
	public int getManifestNumber() {
		Session sess = this.sessionFactory.openSession();
		
        SQLQuery sqlQuery = sess.createSQLQuery("select EMP_ID_SEQ.NEXTVAL from dual");
        Object uniqueResult = sqlQuery.uniqueResult();	
        Long toReturn = null;
        if (uniqueResult instanceof BigDecimal) {
                toReturn = ((BigDecimal)uniqueResult).longValue();
        }
        int a = Integer.valueOf(toReturn.intValue());
        return a;
    }
	
}
