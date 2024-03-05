// package mysql;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class memberjoin {
	
	static private Connection con;
	static private int rs;
	public int successed;
	
	public void join(String ID, String password, String name, String age, String address, String phonenumber) throws ClassNotFoundException {
		try {
			 successed = 0;
			
			 String Driver = "com.mysql.cj.jdbc.Driver";
	         String USERNAME = "SCKDB";
	         String PASSWORD = "1234123";
	         String URL = "jdbc:mysql://140.238.18.250:30306/library";

	         Class.forName(Driver);
	         con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	         System.out.println("DB연결성공");
			
	         // age는 int로 형변환해줘야 함
	         int age_int = Integer.parseInt(age);
				
	         // Scanner scanner = new Scanner(System.in);
			
	         Statement stmt = con.createStatement();
				
	         //System.out.println("객채 생성 성공");
			
	         String sql ="insert into user(ID, PASSWORD, USERNAME, AGE, ADDRESS, TELEPHONE) " + "values ('" + ID +"','" + password +"','" + name + "'," + age_int + ",'" + address + "','" + phonenumber + "')";
				
	         stmt.executeUpdate(sql);
	         
	         successed = 1;
			
		}catch(SQLException e) {
			System.out.println("오류 : "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		memberjoin join = new memberjoin();
		
		/*
		try {
			memberjoin.join();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

	}

}
