package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn= conn;
	}
	
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+"from seller INNER JOIN department "
					+"on seller.DepartmentId = department.Id "
					+"Where seller.Id = ? ");
					
			st.setInt(1, id);
			rs= st.executeQuery();  //rs é um resultSet, nao obj em si
			if(rs.next()) {
				Department dep = instanciateDepartment(rs); //setter
				Seller obj = instanciateSeller(rs,dep);
				return obj;
			}
			return null;
					
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	

	private Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {

		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("baseSalary"));
		obj.setBirthDate(rs.getDate("birthDate"));
		obj.setDepartment(dep);
		return obj;
	}


	private Department instanciateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));  //setter
		dep.setName(rs.getString("DepName")); 

		return dep ;
	}


	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+"from seller INNER JOIN department "
					+"on seller.DepartmentId = department.Id "
					+"Where DepartmentId = ? "
					+"ORDER BY Name ");
					
			st.setInt(1, department.getId());
			rs= st.executeQuery();  //rs é um resultSet, nao obj em si
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map =  new HashMap<>();
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				if(dep== null) {
					dep = instanciateDepartment(rs); //setter
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instanciateSeller(rs,dep);
				list.add(obj);
			}
			return list;
					
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	
	}

	
	
	
}
