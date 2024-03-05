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
   
   // book테이블에서 LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED값을 가져오는데 책이름이 ?와 관련된
   // 데이터를 가져온다.        
   
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
	 		
	  		// 이건 별도 창으로 만드는게 나을듯. 아이콘 옆에 글씨.
	        int MP = 0; 
	  		// MP는 사용자입력을 받아 처리하는 걸로
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
	      // 창이 스스로 닫히는 솔루션을 가져와야 할듯

      } catch (SQLException e) {
         System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
         System.out.print("사유 : " + e.getMessage());
      }
      */
   }
   
   // 로그인 메소드 구현 필요. ID랑 비밀번호 인수 전달 필요. return NowUser 해주는 방식으로. 틀리면 return null.
   // *************************로그인****************************
   public int login(String ID, String Password) throws IOException, ClassNotFoundException
   {
	      try { 
	          String Driver = "com.mysql.cj.jdbc.Driver";
	          String USERNAME = "SCKDB";
	          String PASSWORD = "1234123";
	          String URL = "jdbc:mysql://140.238.18.250:30306/library";

	          Class.forName(Driver);
	          con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	          System.out.println("DB연결 성공");
	          
	          // Scanner scanner = new Scanner(System.in);
	  	
	          // System.out.println("ID를 입력하세요 : ");
	          String id = ID;
	          // System.out.println("비밀번호를 입력하세요 : ");
	          String pw = Password;
	  		
	  		String sql0 = "SELECT * FROM user WHERE ID='" + id + "' AND PASSWORD='" + pw + "';";
	  		
	  		Statement stmt = con.createStatement();
	  		rs = stmt.executeQuery(sql0);
	  		
	  		if(rs.next()) {
	  			System.out.println("로그인 성공!");
	  			System.out.println("Statement 객체 생성 성공");
	  			NowUser=rs.getInt(1);
	  			return NowUser;
	  		}
	  		else {
	  			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
	  			return 0; // 0은 로그인 실패로 처리한다.
	  		}
	  		
	  		// 조건문 상으로 구현해서 != null 으로 참이면 다음 창 띄워주고, 아니면 경고 띄우고
	  		
	          //*************************로그인 끝****************************
	          
	      } catch (SQLException e) {
	          System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
	          System.out.print("사유 : " + e.getMessage());
	       }
		return 0;
   }
   
   public boolean no_books;
   public boolean too_many_books;
   public String[][] tuple = new String[500][6];
   
   // 예약할 책을 찾는 부분과 책을 예약하는 부분을 분리
   // 별도의 창을 만들어주는 식으로
   // *********************책  예약******************************************
   public void findbook(int ID, String bookname) throws IOException, ClassNotFoundException
   {        
	   try {
		   String Driver = "com.mysql.cj.jdbc.Driver";
		   String USERNAME = "SCKDB";
		   String PASSWORD = "1234123";
		   String URL = "jdbc:mysql://140.238.18.250:30306/library";

		   Class.forName(Driver);
		   con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		   System.out.println("DB연결 성공");
       
		   pstmt = con.prepareStatement(sql);
		   pstmt2 = con.prepareStatement(sql2);
		   System.out.println("Statement객체 생성 성공");
       
		   // BufferedReader inbr = new BufferedReader(new InputStreamReader(System.in));
		   // System.out.print("예약할 책 제목을 입력하세요:");
		   str1 = bookname;

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
			   // 위에서 rs.next()를 하여 커서가 옮겨졌기에 커서가 한번더 옮겨지기전에 값을 출력
			   // System.out.println(rs2.getString(1) + "\t" + rs2.getString(2)+ "\t" + rs2.getString(3)+ "\t" + rs2.getString(4)+ "\t" + rs2.getString(5)+ "\t" + rs2.getString(6));
			   while (rs2.next()) { // rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false를 반환한다.
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
	          System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
	          System.out.print("사유 : " + e.getMessage());
	   }
   }
   
	public void set_array()
	{
		if (book_lib.size() > 500)
		{
			System.out.println("책이 너무 많습니다. 다른 검색어로 시도해보세요.");
			too_many_books = true;
		}
		else if (book_lib.size() == 0)
		{
			System.out.println("찾는 책이 없습니다.");
			no_books = true;
		}
		else
		{
			// tuple = list.toArray(result); // 다차원 리스트 못해서 이거 못함
			
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
		   System.out.println("DB연결 성공");
		   
		   result = 0;
		   
		   pstmt = con.prepareStatement(sql);
	       pstmt2 = con.prepareStatement(sql2);
	       System.out.println("Statement객체 생성 성공");
	       
	       BufferedReader inbr = new BufferedReader(new InputStreamReader(System.in));
	       // System.out.print("예약할 책 제목을 입력하세요:");
	       str1 = booknamequery;
		   
		   NowUser = userID;
		   
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
	          // System.out.print("예약할 책 번호를 입력하시오:");
	          str3 = bookID;
	          UID = userID;
	          BN = Integer.parseInt(str3);
	          count = 0;
	            
	          System.out.println("사용자명: " + UID);
	          System.out.println("입력받은 책 번호: " + BN);
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
	       System.out.println("예약이 끝났습니다.");
	       
	       result = 1;
		   
	   } catch (SQLException e) {
	          System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
	          System.out.print("사유 : " + e.getMessage());
	   }
   }
   
   // *************************찜 목록 검색**********************************
   public void findlike(int userID) throws IOException, ClassNotFoundException
   {
	   try {
		   String Driver = "com.mysql.cj.jdbc.Driver";
		   String USERNAME = "SCKDB";
		   String PASSWORD = "1234123";
		   String URL = "jdbc:mysql://140.238.18.250:30306/library";

		   Class.forName(Driver);
		   con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		   System.out.println("DB연결 성공");
		   
		   pstmt = con.prepareStatement(sql);
	       pstmt2 = con.prepareStatement(sql2);
	       System.out.println("Statement객체 생성 성공"); 
		  
	       NowUser = userID;
	       System.out.println("사용자번호:"+NowUser);
	       String sql3="SELECT*FROM reverse where usernumber LIKE ?";
      	 
	       pstmt3 = con.prepareStatement(sql3);

	       pstmt3.setLong(1, NowUser);
	       rs3=pstmt3.executeQuery();

	       System.out.println("도서관\t\t책제목\t\t저자\t\t출판사\t\t출판년도\t\t유저번호");
			
	       while(rs3.next()) {
			   like_book_lib.add(rs3.getString(1)); // 도서관
			   like_book_name.add(rs3.getString(2)); // 책제목
			   like_book_author.add(rs3.getString(3)); // 저자
			   like_book_house.add(rs3.getString(4)); // 출판사
			   like_book_year.add(rs3.getString(5)); // 출판년도
			   like_book_borrowed.add(rs3.getString(6)); // 유저번호
			   // System.out.println(rs3.getString(1) + "\t" + rs3.getString(2)+ "\t" + rs3.getString(3)+ "\t" + rs3.getString(4)+ "\t" + rs3.getString(5)+rs3.getInt(6));				
      	 }
			
     //*************************찜 목록 검색 끝**********************************
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
			System.out.println("책이 너무 많습니다. 다른 검색어로 시도해보세요.");
			like_too_many_books = true;
		}
		else if (like_book_lib.size() == 0)
		{
			System.out.println("찾는 책이 없습니다.");
			like_no_books = true;
		}
		else
		{
			// tuple = list.toArray(result); // 다차원 리스트 못해서 이거 못함
			
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