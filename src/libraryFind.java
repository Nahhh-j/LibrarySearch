import java.io.*;
import java.util.*;

import java.sql.Connection;			
import java.sql.DriverManager;			
import java.sql.ResultSet;			
import java.sql.SQLException;		
import java.sql.Statement;			

public class libraryFind {
	
	public ArrayList<String> lib_id = new ArrayList<String>();
	public ArrayList<String> lib_name = new ArrayList<String>();
	public ArrayList<String> lib_tel = new ArrayList<String>();
	public ArrayList<String> lib_addr = new ArrayList<String>();
	public ArrayList<String> lib_url = new ArrayList<String>();
	
	public String[][] tuple = new String[5][5];
	// i�� Ʃ���� id, �̸�, ��ȭ��ȣ, �ּ�, URL
	
	public void get_library_display() throws ClassNotFoundException, IOException
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String Driver="com.mysql.cj.jdbc.Driver";
		String USERNAME="SCKDB";
		String PASSWORD="1234123";
		String URL="jdbc:mysql://140.238.18.250:30306/library";
		
		try {
			Class.forName(Driver);
			con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("db���Ἲ��");
			stmt=con.createStatement();
			System.out.println("stmt��ü ��������");
			rs=stmt.executeQuery("SELECT*FROM library");
			System.out.println("��ȣ\t������\t\t ��ȭ��ȣ\t\t�ּ�\t\t\t\t\t����Ʈ");
			
			
			while(rs.next()) {				 
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5));				
				}
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("����. ����:"+e.getMessage());
		}
	}
	
	// �� ���� �ҷ��ͼ� �迭 ���� ���� ȭ�鿡 ����ϴ� ����
	// �迭�� �����ϴ� �޼ҵ�� �迭���� �������� �޼ҵ带 ¥����ҵ�
	
	// �迭�� �����ϴ� �޼ҵ�
	public void setlibarray() throws ClassNotFoundException, IOException
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String Driver="com.mysql.cj.jdbc.Driver";
		String USERNAME="SCKDB";
		String PASSWORD="1234123";
		String URL="jdbc:mysql://140.238.18.250:30306/library";
		
		try {
			Class.forName(Driver);
			con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("db���Ἲ��");
			stmt=con.createStatement();
			System.out.println("stmt��ü ��������");
			rs=stmt.executeQuery("SELECT*FROM library");
			System.out.println("��ȣ\t������\t\t ��ȭ��ȣ\t\t�ּ�\t\t\t\t\t����Ʈ");
			
			
			while(rs.next()) {
				lib_id.add(rs.getString(1));
				lib_name.add(rs.getString(2));
				lib_tel.add(rs.getString(3));
				lib_addr.add(rs.getString(4));
				lib_url.add(rs.getString(5));
				
				// System.out.println(rs.getInt(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5));				
				}
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("����. ����:"+e.getMessage());
		}
	}
	
	// �ҷ����� �޼ҵ�
	public void set_array()
	{
		for (int i = 0; i < lib_id.size(); i++)
		{
			System.out.print(lib_id.get(i) + "\t");
			tuple[i][0] = lib_id.get(i);
			System.out.print(lib_name.get(i) + "\t");
			tuple[i][1] = lib_name.get(i);
			System.out.print(lib_tel.get(i) + "\t");
			tuple[i][2] = lib_tel.get(i);
			System.out.print(lib_addr.get(i) + "\t");
			tuple[i][3] = lib_addr.get(i);
			System.out.println(lib_url.get(i));
			tuple[i][4] = lib_url.get(i);
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		libraryFind libfind = new libraryFind();
		// libfind.get_library_display();	
		libfind.setlibarray();
		libfind.set_array();
		
		/*
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				System.out.print(libfind.tuple[i][j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println(libfind.tuple[2][3]);
		*/
	}
}
			

		

