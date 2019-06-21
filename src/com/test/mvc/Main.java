package com.test.mvc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class Main
{
	public static void main(String[] args) throws SQLException
	{
		Driver driver = DriverManager.getDriver("jdbc:oracle:thin:@localhost:1521:xe");
		
		SimpleDriverDataSource sds = new SimpleDriverDataSource(driver, "jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
		
		EmployeeDAO dao = new EmployeeDAO();
		dao.setDataSource(sds);
		
		for(Department dto : dao.departmentList())
			System.out.println(dto.getDepartmentId() + " + " + dto.getDepartmentName() + " + " + dto.getDelCheck());
		
		for(Employee employee : dao.list())
			System.out.println(employee.getEmployeeId() + " + " + employee.getName() + " + " + employee.getBirthday() + " + " + employee.getSsn1() + " + " + employee.getSsn2());
		
		for(Position position : dao.positionList())
			System.out.println(position.getPositionId() + " + " + position.getPositionName() + " + " + position.getMinBasicPay());
		
		for(Region region : dao.regionList())
			System.out.println(region.getRegionId() + " + " + region.getRegionName() + " + " + region.getDelCheck());
		
		
		/*
		int basicpay = dao.getMinBasicPay("1");
		System.out.println(basicpay + " basicpay ~~");
		
		Employee employee = new Employee();
		employee.setName("권홍비");
		employee.setSsn1("950410");
		employee.setSsn2("2323234");
		employee.setSsn2("2323234");
		employee.setBirthday("1995-04-10");
		employee.setLunar(0);
		employee.setTelephone("010-9962-9626");
		employee.setDepartmentId("1");
		employee.setPositionId("1");
		employee.setRegionId("1");
		employee.setBasicPay(1500000);
		employee.setExtraPay(150000);
		
		dao.employeeAdd(employee);
		
		
		dao.remove("3");
		*/
		
		/*
		Employee employee = new Employee();
		employee.setName("진윤비");
		employee.setBirthday("1997-10-01");
		employee.setLunar(0);
		employee.setTelephone("010-9962-9626");
		employee.setDepartmentId("2");
		employee.setPositionId("2");
		employee.setRegionId("2");
		employee.setBasicPay(1500000);
		employee.setExtraPay(150000);
		employee.setSsn1("950410");
		employee.setSsn2("2323234");
		employee.setSsn2("2323234");
		employee.setGrade(1);
		employee.setEmployeeId("4");
		
		
		dao.modify(employee);
		*/
		
		Employee emp2 = dao.searchId("1");
		
		System.out.printf("%s %s", emp2.getEmployeeId(), emp2.getName());
		
		
		String name = dao.loginAdmin("1", "1234567");
		
		System.out.println(name);
		
		
		
	}
}
