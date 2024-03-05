import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;


public class AuthorFind {
	public ArrayList<String> book_lib = new ArrayList<String>();
	public ArrayList<String> book_name = new ArrayList<String>();
	public ArrayList<String> book_author = new ArrayList<String>();
	public ArrayList<String> book_house = new ArrayList<String>();
	public ArrayList<String> book_year = new ArrayList<String>();
	public ArrayList<String> book_borrowed = new ArrayList<String>();
	
	private Connection con;
	private ResultSet rs;
	PreparedStatement pstmt;
	String str1; // 값을 받을 변수
	String str2; // 검색을 하기 위한 변수
	public AuthorFind() throws IOException, ClassNotFoundException {
		/*
		try {
			String Driver="com.mysql.cj.jdbc.Driver";
			String USERNAME="SCKDB";
			String PASSWORD="1234123";
			String URL="jdbc:mysql://140.238.18.250:30306/library";
				
			//book테이블에서 LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED값을 가져오는데 저자의 이름이 ?와 관련된 데이터를 가져온다.
			String sql="select LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED from book where AUTHOR LIKE ?;";
			
			Class.forName(Driver);
			
			//데이터베이스의 연결
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("DB연결 성공");

			//PrepareStatement객체를 생성하여 질의 수행.
			pstmt = con.prepareStatement(sql);
			System.out.println("Statement객체 생성 성공");
			
			//버퍼리더 객체의 생성.
			BufferedReader inbr=new BufferedReader(new InputStreamReader(System.in));
			System.out.print("검색할 책의 저자를 입력하세요:"); //찾을 책의 저자의 이름을 입력한다.
			str1=inbr.readLine();
			
			//str1값을 포함하는 단어
			str2='%'+str1+'%';
			System.out.println();	// 엔터값 제거를 위한 출력.
			
			
			pstmt.setString(1,str2);
			rs = pstmt.executeQuery();// 조회한 결과들을 ResultSet의 rs에 저장을 한다.
		
	
			//테이블의 값을 SELECT했을때 테이블에 원하는 데이터가 없을 경우 rs.next();값은 false가 된다.
			//false경우 이를 부정(!)한 값 true로 원하는 데이터가 없음을 알린다.
			if (!rs.next()) {
				System.out.println("찾는 책이 없습니다.");
			}
			
			
			else {
			//위에서 rs.next()를 하여 커서가 옮겨졌기에 커서가 한번더 옮겨지기전에 값을 출력
			System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5)+ "\t" + rs.getString(6)); 	
			
			
			while(rs.next()) { //rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false를 반환한다.
			System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5)+ "\t" + rs.getString(6)); 
			//getString(n)은 컬럼의 n번째 값을 String형으로 가져온다.
			}
			}
			//데이터의 검색이 끝났음을 알린다.
			System.out.println("***********\t\t\t***********\t\t\t***********\t\t\t***********\t\t\t***********");
			System.out.println("검색이 끝났습니다.");
			
			//연결 종료(자원 반납)
			rs.close();	
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		}
		*/
	}
	
	public void setbookarray(String userinput) throws IOException, ClassNotFoundException
	{
		try {
			String Driver="com.mysql.cj.jdbc.Driver";
			String USERNAME="SCKDB";
			String PASSWORD="1234123";
			String URL="jdbc:mysql://140.238.18.250:30306/library";
				
			//book테이블에서 LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED값을 가져오는데 저자의 이름이 ?와 관련된 데이터를 가져온다.
			String sql="select LOCATION,BOOKNAME,AUTHOR,HOUSE,YEAR,BORROWED from book where AUTHOR LIKE ?;";
			
			Class.forName(Driver);
			
			//데이터베이스의 연결
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("DB연결 성공");

			//PrepareStatement객체를 생성하여 질의 수행.
			pstmt = con.prepareStatement(sql);
			System.out.println("Statement객체 생성 성공");
			
			//버퍼리더 객체의 생성.
			// BufferedReader inbr=new BufferedReader(new InputStreamReader(System.in));
			// System.out.print("검색할 책의 저자를 입력하세요:"); //찾을 책의 저자의 이름을 입력한다.
			str1=userinput;
			
			//str1값을 포함하는 단어
			str2='%'+str1+'%';
			System.out.println();	// 엔터값 제거를 위한 출력.
			
			
			pstmt.setString(1,str2);
			rs = pstmt.executeQuery();// 조회한 결과들을 ResultSet의 rs에 저장을 한다.
		
	
			//테이블의 값을 SELECT했을때 테이블에 원하는 데이터가 없을 경우 rs.next();값은 false가 된다.
			//false경우 이를 부정(!)한 값 true로 원하는 데이터가 없음을 알린다.
			if (!rs.next()) {
				System.out.println("찾는 책이 없습니다.");
				// 아래쪽에 반환처리됨.
			}
			
			else {
				book_lib.add(rs.getString(1));
				book_name.add(rs.getString(2));
				book_author.add(rs.getString(3));
				book_house.add(rs.getString(4));
				book_year.add(rs.getString(5));
				book_borrowed.add(rs.getString(6));
			//위에서 rs.next()를 하여 커서가 옮겨졌기에 커서가 한번더 옮겨지기전에 값을 출력
			// System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5)+ "\t" + rs.getString(6)); 	
			while(rs.next()) { //rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false를 반환한다.
				book_lib.add(rs.getString(1));
				book_name.add(rs.getString(2));
				book_author.add(rs.getString(3));
				book_house.add(rs.getString(4));
				book_year.add(rs.getString(5));
				book_borrowed.add(rs.getString(6));
				
				// System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3)+ "\t" + rs.getString(4)+ "\t" + rs.getString(5)+ "\t" + rs.getString(6)); 
			}
			}
			//데이터의 검색이 끝났음을 알린다.
			System.out.println("***********\t\t\t***********\t\t\t***********\t\t\t***********\t\t\t***********");
			System.out.println("검색이 끝났습니다.");
			// 스윙 이벤트 반환처리 선택사항
			
			//연결 종료(자원 반납)
			rs.close();	
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		}
	}
	
	// 이 시점에서는 ArrayList에 각각 Attribute별로 들어가있음.
	
		// 2차원 배열 길이로 입력
		public String[][] tuple = new String[500][6];
		public boolean too_many_books;
		public boolean no_books;
		
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
		AuthorFind authorfind = new AuthorFind();
		authorfind.setbookarray("박완서"); // 임시로 넣은거. 쿼리를 받는걸로.
		authorfind.set_array();
	}

}