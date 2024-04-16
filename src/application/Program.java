package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES " + "(?, ?, ?, ?, ?)", // place holder: lugar onde vou acrescentar os valores depois.
					  Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Miguel"); // substitui o primeiro '?' PELO NOME MIGUEL
			st.setString(2, "Miguel@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/01/2000").getTime()));
			st.setDouble(4, 3000.00);
			st.setInt(5, 4);

			int rowsAffected = st.executeUpdate(); // execute para uma operação que vai ser alterado os dados
													// O resultado é um numero inteiro indicando quantas linhas foram
													// alteradas no banco de dados.
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1); // indicando que quer o valor da primeira coluna
					System.out.println("Id = " + id);
				}
			}else {
				System.out.println("Sem linhas alteradas");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) { // exceção para a data
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.getConnection();
		}

	}

}
