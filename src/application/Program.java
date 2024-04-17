package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			conn.setAutoCommit(false); // só vai alterar no banco de dados quando isso aqui for true, e paar isso depende do conn.commit()

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
				
			/*int x=1;
				if(x<2) {
					throw new SQLException("Fake error");
				} */
				
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			conn.commit(); // confirma a transação
			
			System.out.println("rows1 = " + rows1);
			System.out.println("rows2 = " + rows2);
			
		} catch (SQLException e) {
			try {
				conn.rollback(); // Para desfazer o que foi feito em caso de erro. Não permite alterar o banco. 
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();

		}

	}

}
