package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;



public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? " // ? valor a ser atribuido
					+ "WHERE "
					+ "(DepartmentId = ?)"// ? valor a ser atribuido
					//Atualiza salario onde o department id for igual ao valor informado
					);
			st.setDouble(1,2000.00);
			st.setInt(2,2);
			int rowsAffected = st.executeUpdate();
			System.out.println("Qunatidade de linhas afetafas: "+ rowsAffected);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		

	}

}
