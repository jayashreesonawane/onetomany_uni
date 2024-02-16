package onetomany_uni.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import onetomany_uni.dto.Company;
import onetomany_uni.dto.Employee;

public class CompanyDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shree");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	Company company = new Company();

	public void saveCompany(Company company) {
		List<Employee> list = company.getEmployees();
		entityTransaction.begin();

		for (Employee employee : list) {
			entityManager.persist(employee);
		}
		entityManager.persist(company);
		entityTransaction.commit();

	}

	public void getCompany(int id) {
		Company company = entityManager.find(Company.class, id);

		if (company != null) {
			System.out.println(company);
		} else {
			System.out.println("Company is not found");
		}
	}

	public void getAllCompanies() {
		Query query = entityManager.createQuery("SELECT c FROM Company c");
		System.out.println(query.getResultList());
	}

	public void deleteCompany(int id) {
		Company company = entityManager.find(Company.class, id);
		if (company != null) {
			List<Employee> list = company.getEmployees();
			entityTransaction.begin();
			for (Employee employee : list) {
				entityManager.remove(employee);
			}
			entityManager.remove(company);
			entityTransaction.commit();
		} else {
			System.out.println("Company with id " + id + " is not found");
		}
	}

	public void updateCompany(int id, Company company) {
		Company dbCompany = entityManager.find(Company.class, id);
		if (dbCompany != null) {
			company.setId(dbCompany.getId());
			company.setEmployees(dbCompany.getEmployees());

			entityTransaction.begin();
			entityManager.merge(company);
			entityTransaction.commit();
		} else {
			System.out.println("Company with given id " + id + " is not found");
		}
	}

	public void updateBoth(int id, Company company) {
		Company existingCompany = entityManager.find(Company.class, id);
		if (existingCompany != null) {
			// Update existing company properties
			existingCompany.setName(company.getName());
			existingCompany.setEmployees(company.getEmployees());

			// Update existing employees
			for (Employee updatedEmployee : company.getEmployees()) {
				Employee existingEmployee = entityManager.find(Employee.class, updatedEmployee.getId());
				if (existingEmployee != null) {
					existingEmployee.setName(updatedEmployee.getName());
					existingEmployee.setPhone(updatedEmployee.getPhone());
					existingEmployee.setAddress(updatedEmployee.getAddress());
					
				}
			}
		} else {
			System.out.println("Company with id " + id + " not found");
		}
	}

	public void addEmployeeToExistingCompany(int id, Employee employee) {
		Company company = entityManager.find(Company.class, id);
		if (company != null) {
			company.getEmployees().add(employee);
			entityTransaction.begin();
			entityManager.persist(employee);
			entityManager.merge(company);
			entityTransaction.commit();
		}
	}

	public void addMultipleEmployeeToExistingCompany(int companyId, List<Employee> employees) {
	    Company company = entityManager.find(Company.class, companyId);

	    if (company != null) {
	        entityTransaction.begin();

	        for (Employee employee : employees) {
	            company.getEmployees().add(employee);
	            entityManager.persist(employee);
	        }

	        entityManager.merge(company);
	        entityTransaction.commit();
	    }
	}

	public void removeEmployee(int companyId, int employeeId) {
		Company company = entityManager.find(Company.class, companyId);
		if (company != null) {
			Employee employee = entityManager.find(Employee.class, employeeId);
			if (employee != null) {
				entityTransaction.begin();
				company.getEmployees().remove(employee);
				entityManager.remove(employee);
				entityManager.merge(company);
				entityTransaction.commit();
			} else {
				System.out.println("Employee with id " + employeeId + " not found");
			}
		} else {
			System.out.println("Company with id " + companyId + " not found");
		}
	}

	public void removeMultipleEmployees(int companyId, List<Integer> employeeIds) {
		Company company = entityManager.find(Company.class, companyId);

		if (company != null) {

			entityTransaction.begin();

			for (Integer employeeId : employeeIds) {
				Employee employee = entityManager.find(Employee.class, employeeId);

				if (employee != null) {
					company.getEmployees().remove(employee);
					entityManager.remove(employee);
				} else {
					System.out.println("Employee with id " + employeeId + " not found");
				}
			}

			entityManager.merge(company);
			entityTransaction.commit();
		} else {
			System.out.println("Company with id " + companyId + " not found");
		}
	}
}
