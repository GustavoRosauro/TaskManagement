package com.project.task;

import static org.hamcrest.CoreMatchers.containsString;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mysql.cj.protocol.Resultset;
import com.project.task.Repository.ITaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/taskbase?useTimezone=true&serverTimezone=UTC","root","");
	}
	// valida se tem dados na tabela
	@Test
	public void validaDados() throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM TASK");
		ResultSet rs = stmt.executeQuery();
		boolean temRegistro = rs.next();//caso tenha registro valor será true
		con.close();
		rs.close();
		stmt.close();
		Assert.assertTrue(temRegistro);
	}
	

}
