/*===========================================
 * 		#8. EmployeeDAO.java
 *=========================================== */

package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class EmployeeDAO implements IEmployeeDAO
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	@Override
	public ArrayList<Employee> list() throws SQLException
	{
		ArrayList<Employee> result = new ArrayList<Employee>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT EMPLOYEEID, NAME, SSN, BIRTHDAY, LUNAR, LUNARNAME, TELEPHONE, DEPARTMENTID, DEPARTMENTNAME, POSITIONID, "
						+ " POSITIONNAME, REGIONID, REGIONNAME, BASICPAY, EXTRAPAY, PAY, GRADE FROM EMPLOYEEVIEW";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			Employee emp = new Employee();
			emp.setEmployeeId(rs.getString("EMPLOYEEID"));
			emp.setName(rs.getString("NAME"));
			emp.setSsn(rs.getString("SSN"));
			emp.setBirthday(rs.getString("BIRTHDAY"));
			emp.setLunar(rs.getInt("LUNAR"));
			emp.setLunarName(rs.getString("LUNARNAME"));
			emp.setTelephone(rs.getString("TELEPHONE"));
			emp.setDepartmentId(rs.getString("DEPARTMENTID"));
			emp.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			emp.setPositionId(rs.getString("POSITIONID"));
			emp.setPositionName(rs.getString("POSITIONNAME"));
			emp.setRegionId(rs.getString("REGIONID"));
			emp.setRegionName(rs.getString("REGIONNAME"));
			emp.setBasicPay(rs.getInt("BASICPAY"));
			emp.setExtraPay(rs.getInt("EXTRAPAY"));
			emp.setPay(rs.getInt("PAY"));
			emp.setGrade(rs.getInt("GRADE"));
			
			result.add(emp);			
		}
		rs.close();
		stmt.close();
		
		return result;
	}

	@Override
	public ArrayList<Region> regionList() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT REGIONID, REGIONNAME, DELCHECK FROM REGIONVIEW";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			Region region = new Region();
			region.setRegionId(rs.getString("REGIONID"));
			region.setRegionName(rs.getString("REGIONNAME"));
			region.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(region);
		}
		rs.close();
		stmt.close();	
		
		return result;
	}

	@Override
	public ArrayList<Department> departmentList() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT DEPARTMENTID, DEPARTMENTNAME, DELCHECK FROM DEPARTMENTVIEW";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			Department dept = new Department();
			dept.setDepartmentId(rs.getString("DEPARTMENTID"));
			dept.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			dept.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(dept);
		}
		rs.close();
		stmt.close();	
		
		return result;
	}

	@Override
	public ArrayList<Position> positionList() throws SQLException
	{
		ArrayList<Position> result = new ArrayList<Position>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT POSITIONID, POSITIONNAME, MINBASICPAY, DELCHECK FROM POSITIONVIEW";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			Position pos = new Position();
			pos.setPositionId(rs.getString("POSITIONID"));
			pos.setPositionName(rs.getString("POSITIONNAME"));
			pos.setMinBasicPay(rs.getInt("MINBASICPAY"));
			pos.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(pos);
		}
		rs.close();
		stmt.close();
		
		return result;
	}

	@Override
	public int getMinBasicPay(String positionId) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		String sql = "SELECT MINBASICPAY FROM POSITION WHERE POSITIONID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, positionId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
			result = rs.getInt("MINBASICPAY");
		
		rs.close();
		pstmt.close();
		
		return result;
	}
		
	@Override
	public int employeeAdd(Employee employee) throws SQLException
	{
		int result = 0;
		Connection conn = dataSource.getConnection();
		String sql = "INSERT INTO EMPLOYEE( EMPLOYEEID, NAME, SSN1, SSN2, BIRTHDAY, LUNAR, TELEPHONE, DEPARTMENTID, POSITIONID, REGIONID, BASICPAY, EXTRAPAY)"
				+ " VALUES( EMPLOYEESEQ.NEXTVAL, ?, ?, CRYPTPACK.ENCRYPT(?,?), TO_DATE(?, 'YYYY-MM-DD'), ?,?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getSsn1());
		pstmt.setString(3, employee.getSsn2());
		pstmt.setString(4, employee.getSsn2());
		pstmt.setString(5, employee.getBirthday());
		pstmt.setInt(6, employee.getLunar());
		pstmt.setString(7, employee.getTelephone());
		pstmt.setString(8, employee.getDepartmentId());
		pstmt.setString(9, employee.getPositionId());
		pstmt.setString(10, employee.getRegionId());
		pstmt.setInt(11, employee.getBasicPay());
		pstmt.setInt(12, employee.getExtraPay());
		
		result = pstmt.executeUpdate();
		pstmt.close();
		
		return result;
	}

	@Override
	public int remove(String employeeId) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, employeeId);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	@Override
	public int modify(Employee employee) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "UPDATE EMPLOYEE SET NAME=? , BIRTHDAY=TO_DATE(?,'YYYY-MM-DD') , LUNAR=? , TELEPHONE=? , "
				+ " DEPARTMENTID=? , POSITIONID=? , REGIONID=? , BASICPAY=? , EXTRAPAY=? , SSN1=? , "
				+ " SSN2=CRYPTPACK.ENCRYPT(?,?) WHERE EMPLOYEEID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getBirthday());
		pstmt.setInt(3, employee.getLunar());
		pstmt.setString(4, employee.getTelephone());
		pstmt.setString(5, employee.getDepartmentId());
		pstmt.setString(6, employee.getPositionId());
		pstmt.setString(7, employee.getRegionId());
		pstmt.setInt(8, employee.getBasicPay());
		pstmt.setInt(9, employee.getExtraPay());
		pstmt.setString(10, employee.getSsn1());
		pstmt.setString(11, employee.getSsn2());
		pstmt.setString(12, employee.getSsn2());
		pstmt.setString(13, employee.getEmployeeId());
		
		result = pstmt.executeUpdate();
		pstmt.close();
		
		return result;
	}

	@Override
	public Employee searchId(String employeeId) throws SQLException
	{
		Employee result = new Employee();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT EMPLOYEEID, NAME, TO_CHAR(BIRTHDAY, 'YYYY-MM-DD') AS BIRTHDAY, LUNAR, TELEPHONE, DEPARTMENTID, POSITIONID, REGIONID, BASICPAY, EXTRAPAY, SSN1, SSN2, GRADE FROM EMPLOYEE WHERE EMPLOYEEID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, employeeId);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{			
			result.setEmployeeId(rs.getString("EMPLOYEEID"));
			result.setName(rs.getString("NAME"));
			result.setBirthday(rs.getString("BIRTHDAY"));
			result.setLunar(rs.getInt("LUNAR"));
			result.setTelephone(rs.getString("TELEPHONE"));
			result.setDepartmentId(rs.getString("DEPARTMENTID"));
			result.setPositionId(rs.getString("POSITIONID"));
			result.setRegionId(rs.getString("REGIONID"));
			result.setBasicPay(rs.getInt("BASICPAY"));
			result.setExtraPay(rs.getInt("EXTRAPAY"));
			result.setSsn1(rs.getString("SSN1"));
			result.setSsn2(rs.getString("SSN2"));
			result.setGrade(rs.getInt("GRADE"));
		}
		rs.close();
		pstmt.close();
		
		return result;
	}

	@Override
	public String login(String id, String pw) throws SQLException
	{
		String result = null;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT NAME FROM EMPLOYEE WHERE EMPLOYEEID=? AND SSN2 = CRYPTPACK.ENCRYPT(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
			result = rs.getString("NAME");
		
		rs.close();
		pstmt.close();		
		
		return result;
	}

	@Override
	public String loginAdmin(String id, String pw) throws SQLException
	{
		String result = null;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT NAME FROM EMPLOYEE WHERE EMPLOYEEID=? AND SSN2 = CRYPTPACK.ENCRYPT(?,?) AND GRADE = 0";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
			result = rs.getString("NAME");
		
		rs.close();
		pstmt.close();		
		
		return result;
	}

}
