import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class BookFind {
	
	public ArrayList<String> book_lib = new ArrayList<String>();
	public ArrayList<String> book_name = new ArrayList<String>();
	public ArrayList<String> book_author = new ArrayList<String>();
	public ArrayList<String> book_house = new ArrayList<String>();
	public ArrayList<String> book_year = new ArrayList<String>();
	public ArrayList<String> book_borrowed = new ArrayList<String>();

	private Connection con;
	private ResultSet rs;
	PreparedStatement pstmt;
	String str1;
	String str2;
	public BookFind() throws IOException, ClassNotFoundException {
		/*
		try {
			String Driver="com.mysql.cj.jdbc.Driver";
			String USERNAME="SCKDB";
			String PASSWORD="1234123";
			String URL="jdbc:mysql://140.238.18.250:30306/library";
			
			//book���̺��� LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED���� �������µ� å�̸��� ?�� ���õ� �����͸� �����´�.
			String sql="select LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED from book where BOOKNAME LIKE ?;";
			
			Class.forName(Driver);
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("DB���� ����");

			pstmt = con.prepareStatement(sql);
			System.out.println("Statement��ü ���� ����");
			
			BufferedReader inbr=new BufferedReader(new InputStreamReader(System.in));
			System.out.print("�˻��� å ������ �Է��ϼ���:");
			str1=inbr.readLine();
			
			//str1���� �����ϴ� �ܾ�
			str2='%'+str1+'%';
			System.out.println();
			
			
			pstmt.setString(1,str2);
			rs = pstmt.executeQuery();
		
	
			//���̺��� ���� SELECT������ ���̺� ���ϴ� �����Ͱ� ���� ��� rs.next();���� false�� �ȴ�.
			//false��� �̸� ����(!)�� �� true�� ���ϴ� �����Ͱ� ������ �˸���.
			if (!rs.next()) {
				System.out.println("ã�� å�� �����ϴ�.");
			}
			
			
			else {
			//������ rs.next()�� �Ͽ� Ŀ���� �Ű����⿡ Ŀ���� �ѹ��� �Ű��������� ���� ���
			System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5)+ "\t" + rs.getString(6)); 	
			while(rs.next()) { //rs.next()�� ���� �������� ������ �� ������ true�� ��ȯ�ϰ�, Ŀ���� ��ĭ ������. �������� ������ false�� ��ȯ�Ѵ�.
				System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5)+ "\t" + rs.getString(6)); 
			}
			}
			//�������� �˻��� �������� �˸���.
			System.out.println("***********\t\t\t***********\t\t\t***********\t\t\t***********\t\t\t***********");
			System.out.println("�˻��� �������ϴ�.");
			
			rs.close();	
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("DB���� �����ϰų�, SQL���� Ʋ�Ƚ��ϴ�.");
			System.out.print("���� : " + e.getMessage());
		}
		*/
	}
	
	public void setbookarray(String userinput) throws IOException, ClassNotFoundException {
		try {
			String Driver="com.mysql.cj.jdbc.Driver";
			String USERNAME="SCKDB";
			String PASSWORD="1234123";
			String URL="jdbc:mysql://140.238.18.250:30306/library";
			
			//book���̺��� LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED���� �������µ� å�̸��� ?�� ���õ� �����͸� �����´�.
			String sql="select LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED from book where BOOKNAME LIKE ?;";
			
			Class.forName(Driver);
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("DB���� ����");

			pstmt = con.prepareStatement(sql);
			System.out.println("Statement��ü ���� ����");
			
			// BufferedReader inbr=new BufferedReader(new InputStreamReader(System.in));
			// System.out.print("�˻��� å ������ �Է��ϼ���:");
			str1=userinput;
			
			//str1���� �����ϴ� �ܾ�
			str2='%'+str1+'%';
			System.out.println();
			
			pstmt.setString(1,str2);
			rs = pstmt.executeQuery();
		
			//���̺��� ���� SELECT������ ���̺� ���ϴ� �����Ͱ� ���� ��� rs.next();���� false�� �ȴ�.
			//false��� �̸� ����(!)�� �� true�� ���ϴ� �����Ͱ� ������ �˸���.
			if (!rs.next()) {
				System.out.println("ã�� å�� �����ϴ�.");
				// ���߿� �̰� ��ȯó�� �������.
			}
			
			
			else {
				book_lib.add(rs.getString(1));
				book_name.add(rs.getString(2));
				book_author.add(rs.getString(3));
				book_house.add(rs.getString(4));
				book_year.add(rs.getString(5));
				book_borrowed.add(rs.getString(6));
			//������ rs.next()�� �Ͽ� Ŀ���� �Ű����⿡ Ŀ���� �ѹ��� �Ű��������� ���� ���
			// System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5)+ "\t" + rs.getString(6)); 	
			while(rs.next()) { //rs.next()�� ���� �������� ������ �� ������ true�� ��ȯ�ϰ�, Ŀ���� ��ĭ ������. �������� ������ false�� ��ȯ�Ѵ�.
				book_lib.add(rs.getString(1));
				book_name.add(rs.getString(2));
				book_author.add(rs.getString(3));
				book_house.add(rs.getString(4));
				book_year.add(rs.getString(5));
				book_borrowed.add(rs.getString(6));
				// System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5)+ "\t" + rs.getString(6)); 
			}
			}
			
			//�������� �˻��� �������� �˸���.
			System.out.println("***********\t\t\t***********\t\t\t***********\t\t\t***********\t\t\t***********");
			System.out.println("�˻��� �������ϴ�.");
			// �̺�Ʈ ��ȯ �ұ� ����
			
			rs.close();	
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("DB���� �����ϰų�, SQL���� Ʋ�Ƚ��ϴ�.");
			System.out.print("���� : " + e.getMessage());
		}
	}
	
	// �� ���������� ArrayList�� ���� Attribute���� ������.
	
	// 2���� �迭 ���̷� �Է�
	public String[][] tuple = new String[500][6];
	public boolean too_many_books;
	public boolean no_books;
	
	public void set_array()
	{
		if (book_lib.size() > 500)
		{
			System.out.println("å�� �ʹ� �����ϴ�. �ٸ� �˻���� �õ��غ�����.");
			too_many_books = true;
		}
		else if (book_lib.size() == 0)
		{
			System.out.println("ã�� å�� �����ϴ�.");
			no_books = true;
		}
		else
		{
			// tuple = list.toArray(result); // ������ ����Ʈ ���ؼ� �̰� ����
			
			for (int i = 0; i < book_lib.size(); i++)
			{
				System.out.print(book_lib.get(i) + "\t");
				System.out.print(book_name.get(i) + "\t");
				System.out.print(book_author.get(i) + "\t");
				System.out.print(book_house.get(i) + "\t");
				System.out.print(book_year.get(i) + "\t");
				System.out.println(book_borrowed.get(i));
				
				tuple[i][0] = book_lib.get(i);
				tuple[i][1] = book_name.get(i);
				tuple[i][2] = book_author.get(i);
				tuple[i][3] = book_house.get(i);
				tuple[i][4] = book_year.get(i);
				// tuple[i][5] = book_borrowed.get(i);
			}
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		BookFind bookfind = new BookFind();
		bookfind.setbookarray("������"); // �׽�Ʈ�� ���� ���
		bookfind.set_array();
		
	}

}