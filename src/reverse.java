import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class reverse {

   private Connection con;
   private ResultSet rs;
   private ResultSet rs2;
   private ResultSet rs3;
   private ResultSet rs4;
   PreparedStatement pstmt;
   PreparedStatement pstmt2;
   PreparedStatement pstmt3;
   String str1;
   String str2;
   String str3;
   
   public ArrayList<String> book_count = new ArrayList<String>();
   public ArrayList<String> book_lib = new ArrayList<String>();
   public ArrayList<String> book_name = new ArrayList<String>();
   public ArrayList<String> book_author = new ArrayList<String>();
   public ArrayList<String> book_house = new ArrayList<String>();
   public ArrayList<String> book_year = new ArrayList<String>();
   public ArrayList<String> book_borrowed = new ArrayList<String>();
   
   public ArrayList<String> like_book_count = new ArrayList<String>();
   public ArrayList<String> like_book_lib = new ArrayList<String>();
   public ArrayList<String> like_book_name = new ArrayList<String>();
   public ArrayList<String> like_book_author = new ArrayList<String>();
   public ArrayList<String> like_book_house = new ArrayList<String>();
   public ArrayList<String> like_book_year = new ArrayList<String>();
   public ArrayList<String> like_book_borrowed = new ArrayList<String>();
   
   // book���̺��� LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED���� �������µ� å�̸��� ?�� ���õ�
   // �����͸� �����´�.        
   
   String sql = "select LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED from book where BOOKNAME LIKE ?;";
   String sql2 = "insert into reverse (location,bookname,author,house,year,usernumber) values (?,?,?,?,?,?)";
   
   int NowUser;
   
   public reverse() throws IOException, ClassNotFoundException {
	   /*
	   try {
	        String Driver = "com.mysql.cj.jdbc.Driver";
	        String USERNAME = "SCKDB";
	        String PASSWORD = "1234123";
	        String URL = "jdbc:mysql://140.238.18.250:30306/library";

	        // book���̺��� LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED���� �������µ� å�̸��� ?�� ���õ�
	        // �����͸� �����´�.        
	         
	        String sql = "select LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED from book where BOOKNAME LIKE ?;";
	        String sql2 = "insert into reverse (location,bookname,author,house,year,usernumber) values (?,?,?,?,?,?)";

	        Class.forName(Driver);
	        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	        System.out.println("DB���� ����");
	         
	         
	        //*************************�α���****************************
	        Scanner scanner = new Scanner(System.in);
	 		
	 		System.out.println("ID�� �Է��ϼ��� : ");
	 		String id = scanner.next();
	 		System.out.println("��й�ȣ�� �Է��ϼ��� : ");
	 		String pw = scanner.next();
	 		
	 		String sql0 = "SELECT * FROM user WHERE ID='" + id + "' AND PASSWORD='" + pw + "';";
	 		
	 		Statement stmt = con.createStatement();
	 		rs = stmt.executeQuery(sql0);
	 		
	 		if(rs.next()) {
	 			System.out.println("�α��� ����!");
	 			NowUser=rs.getInt(1);
	 			
	 		}
	 		else {
	 			System.out.println("���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
	 		}
	 		
	 		System.out.println("Statement ��ü ���� ����");
	 		
	 	
	         //*************************�α��� ��****************************
	 		
	  		// �̰� ���� â���� ����°� ������. ������ ���� �۾�.
	        int MP = 0; 
	  		// MP�� ������Է��� �޾� ó���ϴ� �ɷ�
	 		while(MP!=3) {
	         System.out.println("å ���ɵ�� 1��");
	         System.out.println("���ɸ�� �˻� 2��");
	         System.out.println("�ý������� 3��");
	         MP = scanner.nextInt();
      
	         //*********************å  ����******************************************
	         if(MP==1) {
	         pstmt = con.prepareStatement(sql);
	         pstmt2 = con.prepareStatement(sql2);
	         System.out.println("Statement��ü ���� ����");

	         BufferedReader inbr = new BufferedReader(new InputStreamReader(System.in));
	         System.out.print("������ å ������ �Է��ϼ���:");
	         str1 = inbr.readLine();

	         // str1���� �����ϴ� �ܾ�
	         str2 = '%' + str1 + '%';
	         System.out.println();

	         pstmt.setString(1, str2);
	         rs2 = pstmt.executeQuery();

	         int count = 1;
	         // ���̺��� ���� SELECT������ ���̺� ���ϴ� �����Ͱ� ���� ��� rs.next();���� false�� �ȴ�.
	         // false��� �̸� ����(!)�� �� true�� ���ϴ� �����Ͱ� ������ �˸���.
	         if (!rs2.next()) {
	            System.out.println("ã�� å�� �����ϴ�.");
	         }
	 
	         else {
	            // ������ rs.next()�� �Ͽ� Ŀ���� �Ű����⿡ Ŀ���� �ѹ��� �Ű��������� ���� ���
	            System.out.println(count +". "+rs2.getString(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t"
	                  + rs2.getString(4) + "\t" + rs2.getString(5) + "\t" + rs2.getString(6));
	            while (rs2.next()) { // rs.next()�� ���� �������� ������ �� ������ true�� ��ȯ�ϰ�, Ŀ���� ��ĭ ������. �������� ������ false�� ��ȯ�Ѵ�.
	               count++;
	               System.out.println(count +". "+rs2.getString(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t"
	                     + rs2.getString(4) + "\t" + rs2.getString(5) + "\t" + rs2.getString(6));
	            }
	            System.out.print("������ å ��ȣ�� �Է��Ͻÿ�:");
	            str3 = inbr.readLine();
	            int BN = Integer.parseInt(str3);
	            count = 0;
	            rs2 = pstmt.executeQuery();
	            while(rs2.next())
	            {
	               count++;
	               if(BN == count)
	               {
	                  pstmt2.setString(1, rs2.getString(1));
	                  pstmt2.setString(2, rs2.getString(2));
	                  pstmt2.setString(3, rs2.getString(3));
	                  pstmt2.setString(4, rs2.getString(4));
	                  pstmt2.setString(5, rs2.getString(5));
	                  pstmt2.setLong(6,NowUser) ;
	                  
	                  
	                  pstmt2.executeUpdate();
	               }
	               
	            }
	         }
	         // �������� �˻��� �������� �˸���.
	         System.out.println("***********\t\t\t***********\t\t\t***********\t\t\t***********\t\t\t***********");
	         System.out.println("������ �������ϴ�.");

	         }
	         //*************************å ���� ��*******************************************
	         

	       //*************************�� ��� �˻�**********************************
	         
	         else if(MP==2) {
	        	 System.out.println("����ڹ�ȣ:"+NowUser);
	        	 String sql3="SELECT*FROM reverse where usernumber LIKE ?";
	        	 pstmt3=con.prepareStatement(sql3);
	        	 pstmt3.setLong(1, NowUser);
	        	 rs3=pstmt3.executeQuery();
	        	 
				System.out.println("������\t\tå����\t\t����\t\t���ǻ�\t\t���ǳ⵵\t\t������ȣ");
				
				while(rs3.next()) {				 
					System.out.println(rs3.getString(1) + "\t" + rs3.getString(2)+ "\t" + rs3.getString(3)+ "\t" + rs3.getString(4)+ "\t" + rs3.getString(5)+rs3.getInt(6));				
					}
	        }
	       //*************************�� ��� �˻� ��**********************************
	         else if(MP==3) {break;} 
	         else 
	        	 System.out.println("��ȣ�� �ٽ� �Է��� �ּ���");
	         
	 		}
	 		
	         rs.close();
	         rs2.close();
	         rs3.close();
	         pstmt.close();
	         pstmt2.close();
	         pstmt3.close();
	         con.close();
	         System.out.println("���α׷�����");
	      // â�� ������ ������ �ַ���� �����;� �ҵ�

      } catch (SQLException e) {
         System.out.println("DB���� �����ϰų�, SQL���� Ʋ�Ƚ��ϴ�.");
         System.out.print("���� : " + e.getMessage());
      }
      */
   }
   
   // �α��� �޼ҵ� ���� �ʿ�. ID�� ��й�ȣ �μ� ���� �ʿ�. return NowUser ���ִ� �������. Ʋ���� return null.
   // *************************�α���****************************
   public int login(String ID, String Password) throws IOException, ClassNotFoundException
   {
	      try { 
	          String Driver = "com.mysql.cj.jdbc.Driver";
	          String USERNAME = "SCKDB";
	          String PASSWORD = "1234123";
	          String URL = "jdbc:mysql://140.238.18.250:30306/library";

	          Class.forName(Driver);
	          con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	          System.out.println("DB���� ����");
	          
	          // Scanner scanner = new Scanner(System.in);
	  	
	          // System.out.println("ID�� �Է��ϼ��� : ");
	          String id = ID;
	          // System.out.println("��й�ȣ�� �Է��ϼ��� : ");
	          String pw = Password;
	  		
	  		String sql0 = "SELECT * FROM user WHERE ID='" + id + "' AND PASSWORD='" + pw + "';";
	  		
	  		Statement stmt = con.createStatement();
	  		rs = stmt.executeQuery(sql0);
	  		
	  		if(rs.next()) {
	  			System.out.println("�α��� ����!");
	  			System.out.println("Statement ��ü ���� ����");
	  			NowUser=rs.getInt(1);
	  			return NowUser;
	  		}
	  		else {
	  			System.out.println("���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
	  			return 0; // 0�� �α��� ���з� ó���Ѵ�.
	  		}
	  		
	  		// ���ǹ� ������ �����ؼ� != null ���� ���̸� ���� â ����ְ�, �ƴϸ� ��� ����
	  		
	          //*************************�α��� ��****************************
	          
	      } catch (SQLException e) {
	          System.out.println("DB���� �����ϰų�, SQL���� Ʋ�Ƚ��ϴ�.");
	          System.out.print("���� : " + e.getMessage());
	       }
		return 0;
   }
   
   public boolean no_books;
   public boolean too_many_books;
   public String[][] tuple = new String[500][6];
   
   // ������ å�� ã�� �κа� å�� �����ϴ� �κ��� �и�
   // ������ â�� ������ִ� ������
   // *********************å  ����******************************************
   public void findbook(int ID, String bookname) throws IOException, ClassNotFoundException
   {        
	   try {
		   String Driver = "com.mysql.cj.jdbc.Driver";
		   String USERNAME = "SCKDB";
		   String PASSWORD = "1234123";
		   String URL = "jdbc:mysql://140.238.18.250:30306/library";

		   Class.forName(Driver);
		   con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		   System.out.println("DB���� ����");
       
		   pstmt = con.prepareStatement(sql);
		   pstmt2 = con.prepareStatement(sql2);
		   System.out.println("Statement��ü ���� ����");
       
		   // BufferedReader inbr = new BufferedReader(new InputStreamReader(System.in));
		   // System.out.print("������ å ������ �Է��ϼ���:");
		   str1 = bookname;

		   // str1���� �����ϴ� �ܾ�
		   str2 = '%' + str1 + '%';
		   System.out.println();

		   pstmt.setString(1, str2);
		   rs2 = pstmt.executeQuery();

		   int count = 1;
		   // ���̺��� ���� SELECT������ ���̺� ���ϴ� �����Ͱ� ���� ��� rs.next();���� false�� �ȴ�.
		   // false��� �̸� ����(!)�� �� true�� ���ϴ� �����Ͱ� ������ �˸���.
		   if (!rs2.next()) {
			   System.out.println("ã�� å�� �����ϴ�.");
			   no_books = true;
		   }

		   else {
			   book_count.add(Integer.toString(count));
			   book_lib.add(rs2.getString(1));
			   book_name.add(rs2.getString(2));
			   book_author.add(rs2.getString(3));
			   book_house.add(rs2.getString(4));
			   book_year.add(rs2.getString(5));
			   book_borrowed.add(rs2.getString(6));
			   // ������ rs.next()�� �Ͽ� Ŀ���� �Ű����⿡ Ŀ���� �ѹ��� �Ű��������� ���� ���
			   // System.out.println(rs2.getString(1) + "\t" + rs2.getString(2)+ "\t" + rs2.getString(3)+ "\t" + rs2.getString(4)+ "\t" + rs2.getString(5)+ "\t" + rs2.getString(6));
			   while (rs2.next()) { // rs.next()�� ���� �������� ������ �� ������ true�� ��ȯ�ϰ�, Ŀ���� ��ĭ ������. �������� ������ false�� ��ȯ�Ѵ�.
				   count++;
				   book_count.add(Integer.toString(count));
				   book_lib.add(rs2.getString(1));
				   book_name.add(rs2.getString(2));
				   book_author.add(rs2.getString(3));
				   book_house.add(rs2.getString(4));
				   book_year.add(rs2.getString(5));
				   book_borrowed.add(rs2.getString(6));
				   // System.out.println(rs2.getString(1) + "\t" + rs2.getString(2)+ "\t" + rs2.getString(3)+ "\t" + rs2.getString(4)+ "\t" + rs2.getString(5)+ "\t" + rs2.getString(6));
			   }
		   }
	   } catch (SQLException e) {
	          System.out.println("DB���� �����ϰų�, SQL���� Ʋ�Ƚ��ϴ�.");
	          System.out.print("���� : " + e.getMessage());
	   }
   }
   
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
				System.out.print(book_count.get(i) + ". \t");
				System.out.print(book_lib.get(i) + "\t");
				System.out.print(book_name.get(i) + "\t");
				System.out.print(book_author.get(i) + "\t");
				System.out.print(book_house.get(i) + "\t");
				System.out.print(book_year.get(i) + "\t");
				System.out.println(book_borrowed.get(i));
				
				tuple[i][0] = book_count.get(i);
				tuple[i][1] = book_lib.get(i);
				tuple[i][2] = book_name.get(i);
				tuple[i][3] = book_author.get(i);
				tuple[i][4] = book_house.get(i);
				tuple[i][5] = book_year.get(i);
			}
		}
	}
   
   public int UID;
   public int BN;
   public int result;
    
   public void makereserve(int userID, String bookID, String booknamequery) throws IOException, ClassNotFoundException
   {        
	   try {
		   String Driver = "com.mysql.cj.jdbc.Driver";
		   String USERNAME = "SCKDB";
		   String PASSWORD = "1234123";
		   String URL = "jdbc:mysql://140.238.18.250:30306/library";

		   Class.forName(Driver);
		   con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		   System.out.println("DB���� ����");
		   
		   result = 0;
		   
		   pstmt = con.prepareStatement(sql);
	       pstmt2 = con.prepareStatement(sql2);
	       System.out.println("Statement��ü ���� ����");
	       
	       BufferedReader inbr = new BufferedReader(new InputStreamReader(System.in));
	       // System.out.print("������ å ������ �Է��ϼ���:");
	       str1 = booknamequery;
		   
		   NowUser = userID;
		   
	       // str1���� �����ϴ� �ܾ�
	       str2 = '%' + str1 + '%';
	       System.out.println();

	       pstmt.setString(1, str2);
	       rs2 = pstmt.executeQuery();

	       int count = 1;
	       // ���̺��� ���� SELECT������ ���̺� ���ϴ� �����Ͱ� ���� ��� rs.next();���� false�� �ȴ�.
	       // false��� �̸� ����(!)�� �� true�� ���ϴ� �����Ͱ� ������ �˸���.
	       if (!rs2.next()) {
	          System.out.println("ã�� å�� �����ϴ�.");
	       }
	 
	       else {
	          // ������ rs.next()�� �Ͽ� Ŀ���� �Ű����⿡ Ŀ���� �ѹ��� �Ű��������� ���� ���
	          System.out.println(count +". "+rs2.getString(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t"
	                + rs2.getString(4) + "\t" + rs2.getString(5) + "\t" + rs2.getString(6));
	          while (rs2.next()) { // rs.next()�� ���� �������� ������ �� ������ true�� ��ȯ�ϰ�, Ŀ���� ��ĭ ������. �������� ������ false�� ��ȯ�Ѵ�.
	             count++;
	             System.out.println(count +". "+rs2.getString(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t"
	                   + rs2.getString(4) + "\t" + rs2.getString(5) + "\t" + rs2.getString(6));
	          }
	          // System.out.print("������ å ��ȣ�� �Է��Ͻÿ�:");
	          str3 = bookID;
	          UID = userID;
	          BN = Integer.parseInt(str3);
	          count = 0;
	            
	          System.out.println("����ڸ�: " + UID);
	          System.out.println("�Է¹��� å ��ȣ: " + BN);
	          rs2 = pstmt.executeQuery();
	   
	          while(rs2.next())
	          {
	             count++;
	             if(BN == count)
	             {
	                pstmt2.setString(1, rs2.getString(1));
	                pstmt2.setString(2, rs2.getString(2));
	                pstmt2.setString(3, rs2.getString(3));
	                pstmt2.setString(4, rs2.getString(4));
	                pstmt2.setString(5, rs2.getString(5));
	                pstmt2.setLong(6,NowUser) ;
	                        
	                pstmt2.executeUpdate();
	             }               
	          }
	          
	       }
	            
	       System.out.println("***********\t\t\t***********\t\t\t***********\t\t\t***********\t\t\t***********");
	       System.out.println("������ �������ϴ�.");
	       
	       result = 1;
		   
	   } catch (SQLException e) {
	          System.out.println("DB���� �����ϰų�, SQL���� Ʋ�Ƚ��ϴ�.");
	          System.out.print("���� : " + e.getMessage());
	   }
   }
   
   // *************************�� ��� �˻�**********************************
   public void findlike(int userID) throws IOException, ClassNotFoundException
   {
	   try {
		   String Driver = "com.mysql.cj.jdbc.Driver";
		   String USERNAME = "SCKDB";
		   String PASSWORD = "1234123";
		   String URL = "jdbc:mysql://140.238.18.250:30306/library";

		   Class.forName(Driver);
		   con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		   System.out.println("DB���� ����");
		   
		   pstmt = con.prepareStatement(sql);
	       pstmt2 = con.prepareStatement(sql2);
	       System.out.println("Statement��ü ���� ����"); 
		  
	       NowUser = userID;
	       System.out.println("����ڹ�ȣ:"+NowUser);
	       String sql3="SELECT*FROM reverse where usernumber LIKE ?";
      	 
	       pstmt3 = con.prepareStatement(sql3);

	       pstmt3.setLong(1, NowUser);
	       rs3=pstmt3.executeQuery();

	       System.out.println("������\t\tå����\t\t����\t\t���ǻ�\t\t���ǳ⵵\t\t������ȣ");
			
	       while(rs3.next()) {
			   like_book_lib.add(rs3.getString(1)); // ������
			   like_book_name.add(rs3.getString(2)); // å����
			   like_book_author.add(rs3.getString(3)); // ����
			   like_book_house.add(rs3.getString(4)); // ���ǻ�
			   like_book_year.add(rs3.getString(5)); // ���ǳ⵵
			   like_book_borrowed.add(rs3.getString(6)); // ������ȣ
			   // System.out.println(rs3.getString(1) + "\t" + rs3.getString(2)+ "\t" + rs3.getString(3)+ "\t" + rs3.getString(4)+ "\t" + rs3.getString(5)+rs3.getInt(6));				
      	 }
			
     //*************************�� ��� �˻� ��**********************************
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   
   public boolean like_no_books;
   public boolean like_too_many_books;
   public String[][] like_tuple = new String[500][6];
   
	public void like_set_array()
	{
		if (like_book_lib.size() > 500)
		{
			System.out.println("å�� �ʹ� �����ϴ�. �ٸ� �˻���� �õ��غ�����.");
			like_too_many_books = true;
		}
		else if (like_book_lib.size() == 0)
		{
			System.out.println("ã�� å�� �����ϴ�.");
			like_no_books = true;
		}
		else
		{
			// tuple = list.toArray(result); // ������ ����Ʈ ���ؼ� �̰� ����
			
			for (int i = 0; i < like_book_lib.size(); i++)
			{
				System.out.print(like_book_lib.get(i) + "\t");
				System.out.print(like_book_name.get(i) + "\t");
				System.out.print(like_book_author.get(i) + "\t");
				System.out.print(like_book_house.get(i) + "\t");
				System.out.print(like_book_year.get(i) + "\t");
				System.out.println(like_book_borrowed.get(i));
				
				like_tuple[i][0] = like_book_lib.get(i);
				like_tuple[i][1] = like_book_name.get(i);
				like_tuple[i][2] = like_book_author.get(i);
				like_tuple[i][3] = like_book_house.get(i);
				like_tuple[i][4] = like_book_year.get(i);
				like_tuple[i][5] = like_book_borrowed.get(i);
			}
		}
	}
          
   public static void main(String[] args) throws IOException, ClassNotFoundException {
	  // TODO Auto-generated method stub
	  reverse Reverse = new reverse();
   }
   
 }