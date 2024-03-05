import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class reverse_original {

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
   int NowUser;
   
   // ��������. ��������. ���� ������ ���⼭ ������ ���°ɷ�
   
   public reverse_original() throws IOException, ClassNotFoundException {
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
        int MP = 0; 
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
         
      } catch (SQLException e) {
         System.out.println("DB���� �����ϰų�, SQL���� Ʋ�Ƚ��ϴ�.");
         System.out.print("���� : " + e.getMessage());
      }
   }

   public static void main(String[] args) throws IOException, ClassNotFoundException {
      // TODO Auto-generated method stub
	   
      new reverse();
   }

}