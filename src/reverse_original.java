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
   
   // 원본파일. 수정금지. 뭔가 막히면 여기서 가져다 쓰는걸로
   
   public reverse_original() throws IOException, ClassNotFoundException {
      try { 

         String Driver = "com.mysql.cj.jdbc.Driver";
         String USERNAME = "SCKDB";
         String PASSWORD = "1234123";
         String URL = "jdbc:mysql://140.238.18.250:30306/library";

         // book테이블에서 LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED값을 가져오는데 책이름이 ?와 관련된
         // 데이터를 가져온다.        
         
         String sql = "select LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED from book where BOOKNAME LIKE ?;";
         String sql2 = "insert into reverse (location,bookname,author,house,year,usernumber) values (?,?,?,?,?,?)";

         Class.forName(Driver);
         con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         System.out.println("DB연결 성공");
         
         
         //*************************로그인****************************
         Scanner scanner = new Scanner(System.in);
 		
 		System.out.println("ID를 입력하세요 : ");
 		String id = scanner.next();
 		System.out.println("비밀번호를 입력하세요 : ");
 		String pw = scanner.next();
 		
 		String sql0 = "SELECT * FROM user WHERE ID='" + id + "' AND PASSWORD='" + pw + "';";
 		
 		Statement stmt = con.createStatement();
 		rs = stmt.executeQuery(sql0);
 		
 		
 		
 		if(rs.next()) {
 			System.out.println("로그인 성공!");
 			NowUser=rs.getInt(1);
 			
 		}
 		else {
 			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
 		}
 		
 		System.out.println("Statement 객체 생성 성공");
 		
 	
         //*************************로그인 끝****************************
        int MP = 0; 
 		while(MP!=3) {
         System.out.println("책 관심등록 1번");
         System.out.println("관심목록 검색 2번");
         System.out.println("시스템종료 3번");
         MP = scanner.nextInt();
         
         
         
         
   
         //*********************책  예약******************************************
         if(MP==1) {
         pstmt = con.prepareStatement(sql);
         pstmt2 = con.prepareStatement(sql2);
         System.out.println("Statement객체 생성 성공");

         BufferedReader inbr = new BufferedReader(new InputStreamReader(System.in));
         System.out.print("예약할 책 제목을 입력하세요:");
         str1 = inbr.readLine();

         // str1값을 포함하는 단어
         str2 = '%' + str1 + '%';
         System.out.println();

         pstmt.setString(1, str2);
         rs2 = pstmt.executeQuery();

         int count = 1;
         // 테이블의 값을 SELECT했을때 테이블에 원하는 데이터가 없을 경우 rs.next();값은 false가 된다.
         // false경우 이를 부정(!)한 값 true로 원하는 데이터가 없음을 알린다.
         if (!rs2.next()) {
            System.out.println("찾는 책이 없습니다.");
         }
 
         else {
            // 위에서 rs.next()를 하여 커서가 옮겨졌기에 커서가 한번더 옮겨지기전에 값을 출력
            System.out.println(count +". "+rs2.getString(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t"
                  + rs2.getString(4) + "\t" + rs2.getString(5) + "\t" + rs2.getString(6));
            while (rs2.next()) { // rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false를 반환한다.
               count++;
               System.out.println(count +". "+rs2.getString(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t"
                     + rs2.getString(4) + "\t" + rs2.getString(5) + "\t" + rs2.getString(6));
            }
            System.out.print("예약할 책 번호를 입력하시오:");
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
         // 데이터의 검색이 끝났음을 알린다.
         System.out.println("***********\t\t\t***********\t\t\t***********\t\t\t***********\t\t\t***********");
         System.out.println("예약이 끝났습니다.");

         }
         //*************************책 예약 끝*******************************************
         

       //*************************찜 목록 검색**********************************
         
         else if(MP==2) {
        	 System.out.println("사용자번호:"+NowUser);
        	 String sql3="SELECT*FROM reverse where usernumber LIKE ?";
        	 pstmt3=con.prepareStatement(sql3);
        	 pstmt3.setLong(1, NowUser);
        	 rs3=pstmt3.executeQuery();
        	 
			System.out.println("도서관\t\t책제목\t\t저자\t\t출판사\t\t출판년도\t\t유저번호");
			
			while(rs3.next()) {				 
				System.out.println(rs3.getString(1) + "\t" + rs3.getString(2)+ "\t" + rs3.getString(3)+ "\t" + rs3.getString(4)+ "\t" + rs3.getString(5)+rs3.getInt(6));				
				}
        }
       //*************************찜 목록 검색 끝**********************************
         else if(MP==3) {break;} 
         else 
        	 System.out.println("번호를 다시 입력해 주세요");
         
 		}
 		
         rs.close();
         rs2.close();
         rs3.close();
         pstmt.close();
         pstmt2.close();
         pstmt3.close();
         con.close();
         System.out.println("프로그램종료");
         
      } catch (SQLException e) {
         System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
         System.out.print("사유 : " + e.getMessage());
      }
   }

   public static void main(String[] args) throws IOException, ClassNotFoundException {
      // TODO Auto-generated method stub
	   
      new reverse();
   }

}