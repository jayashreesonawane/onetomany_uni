package onetomany_uni.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import onetomany_uni.dao.CompanyDao;
import onetomany_uni.dto.Company;
import onetomany_uni.dto.Employee;

public class MainController {

	public static void main(String[] args) {

		Company company = new Company();
		CompanyDao companyDao = new CompanyDao();

		Scanner scanner = new Scanner(System.in);
		System.out.println(
				"Enter your choice: \n1.Save Company \n2.Get Company \n3.Get All Companies \n4.Delete Company \n5.Update Company Only "
						+ "\n6.Update Both \n7.Add Employee to existing company \n8.Remove Employee \n9.Remove multiple employees \n10.add multiple employees to company");

		int choice = scanner.nextInt();

		switch (choice) {

		case 1: {
			Employee employee1 = new Employee();
			System.out.println("Enter Employee Id : ");
			int id = scanner.nextInt();
			System.out.println("Enter Employee name : ");
			String name = scanner.next();
			System.out.println("Enter Employee Contact : ");
			long phone = scanner.nextLong();
			System.out.println("Enter Employee Address : ");
			String address = scanner.next();
			employee1.setId(id);
			employee1.setName(name);
			employee1.setPhone(phone);
			employee1.setAddress(address);

			Employee employee2 = new Employee();
			System.out.println("Enter Employee Id : ");
			int sid = scanner.nextInt();
			System.out.println("Enter Employee name : ");
			String sname = scanner.next();
			System.out.println("Enter Employee Contact : ");
			long sphone = scanner.nextLong();
			System.out.println("Enter Employee Address : ");
			String saddress = scanner.next();
			employee1.setId(sid);
			employee1.setName(sname);
			employee1.setPhone(sphone);
			employee1.setAddress(saddress);

			Employee employee3 = new Employee();
			System.out.println("Enter Employee Id : ");
			int tid = scanner.nextInt();
			System.out.println("Enter Employee name : ");
			String tname = scanner.next();
			System.out.println("Enter Employee Contact : ");
			long tphone = scanner.nextLong();
			System.out.println("Enter Employee Address : ");
			String taddress = scanner.next();
			employee1.setId(tid);
			employee1.setName(tname);
			employee1.setPhone(tphone);
			employee1.setAddress(taddress);

			List<Employee> list = new ArrayList<Employee>();
			list.add(employee1);
			list.add(employee2);
			list.add(employee3);

			System.out.println("Enter Company Id : ");
			int c_id = scanner.nextInt();
			System.out.println("Enter Company name : ");
			String cname = scanner.next();
			System.out.println("Enter Company Location : ");
			String clocation = scanner.next();
			System.out.println("Enter Company GST : ");
			String cgst = scanner.next();
			company.setId(c_id);
			company.setName(cname);
			company.setLocation(clocation);
			company.setGst(cgst);
			company.setEmployees(list);

			companyDao.saveCompany(company);
			System.out.println("Company Saved Successfully");
		}
			break;

		case 2: {
			System.out.println("Enter company id you want to get : ");
			int id = scanner.nextInt();
			companyDao.getCompany(id);
		}

			break;

		case 3: {
			companyDao.getAllCompanies();
		}

			break;

		case 4: {
			System.out.println("Enter company id you want to delete : ");
			int id = scanner.nextInt();
			companyDao.deleteCompany(id);
		}

			break;

		case 5: {
			System.out.println("Enter company id you want to update : ");
			int id = scanner.nextInt();
			companyDao.updateCompany(id, company);
		}

			break;

		case 6: {
			System.out.println("Enter company id you want to update : ");
			int id = scanner.nextInt();
			companyDao.updateBoth(id, company);
		}

			break;

		case 7: {
			System.out.println("Enter company id you want to add employee : ");
			int id = scanner.nextInt();

			Employee employee = new Employee();
			System.out.println("Enter Employee Id : ");
			int tid = scanner.nextInt();
			System.out.println("Enter Employee name : ");
			String tname = scanner.next();
			System.out.println("Enter Employee Contact : ");
			long tphone = scanner.nextLong();
			System.out.println("Enter Employee Address : ");
			String taddress = scanner.next();
			employee.setId(tid);
			employee.setName(tname);
			employee.setPhone(tphone);
			employee.setAddress(taddress);

			List<Employee> list = new ArrayList<Employee>();
			list.add(employee);
			companyDao.addEmployeeToExistingCompany(id, employee);
		}

			break;

		case 8: {
			System.out.println("Enter company id from you want to remove employee : ");
			int cid = scanner.nextInt();
			System.out.println("Enter employee id you want to remove : ");
			int eid = scanner.nextInt();
			companyDao.removeEmployee(cid, eid);
		}

			break;

		case 9: {
			System.out.println("Enter company id from you want to remove employee : ");
			int cid = scanner.nextInt();
			System.out.println("Enter employee id you want to remove : ");
			int eid1 = scanner.nextInt();
			System.out.println("Enter employee id you want to remove : ");
			int eid2 = scanner.nextInt();
			companyDao.removeMultipleEmployees(101, Arrays.asList(eid1, eid2));
		}

			break;

		case 10: {
			List<Employee> list = new ArrayList<Employee>();
			System.out.println("Enter company id from you want to add employee : ");
			int cid = scanner.nextInt();
			companyDao.addMultipleEmployeeToExistingCompany(cid, list);
		}

			break;

		default:
			break;
		}

	}
}
