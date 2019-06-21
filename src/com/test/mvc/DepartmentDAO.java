/*===========================================
 * 		#10. DepartmentDAO.java
 * 		- 데이터베이스 액션 처리 클래스.
 * 		- 부서 정보 입출력 / 수정 / 삭제 액션
 * 		- Connection 객체에 대한 의존성 주입
 * 		  → setter 메소드 정의
 *=========================================== */

package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class DepartmentDAO implements IDepartmentDAO
{
	// 인터페이스 자료형을 속성으로 구성
	private DataSource dataSource;

	// setter 구성
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	
	// 인터페이스 메소드 재정의 -------------------------------------------------------------------------------------------------
	
	// 부서 전체 리스트 출력
	@Override
	public ArrayList<Department> list() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT DEPARTMENTID, DEPARTMENTNAME, DELCHECK FROM DEPARTMENTVIEW ORDER BY DEPARTMENTID";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		Department department = null;
		while(rs.next())
		{
			department = new Department();
			department.setDepartmentId(rs.getString("DEPARTMENTID"));
			department.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			department.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(department);
		}
		rs.close();
		stmt.close();
		conn.close();		
		
		return result;
	}

	@Override
	public int add(Department department) throws SQLException
	{
		int result=0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "INSERT INTO DEPARTMENT(DEPARTMENTID, DEPARTMENTNAME) VALUES(DEPARTMENTSEQ.NEXTVAL, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public Department searchId(String departmentId) throws SQLException
	{
		Department result = new Department();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT DEPARTMENTID, DEPARTMENTNAME, DELCHECK FROM DEPARTMENTVIEW WHERE DEPARTMENTID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, departmentId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			result.setDepartmentId(rs.getString("DEPARTMENTID"));
			result.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			result.setDelCheck(rs.getInt("DELCHECK"));
		}
		rs.close();
		pstmt.close();
		conn.close();	
		
		return result;
	}

	
	@Override
	public int remove(String departmentId) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "DELETE FROM DEPARTMENT WHERE DEPARTMENTID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(departmentId));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int modify(Department department) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "UPDATE DEPARTMENT SET DEPARTMENTNAME = ? WHERE DEPARTMENTID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		pstmt.setInt(2, Integer.parseInt(department.getDepartmentId()));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();		
		
		return result;
	}
	
}
