// package mysql;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
class DB{
	
		
	
	public DB() {String url = "jdbc:mysql://127.0.0.1/?useSSL=false&user=root&password=asdf153212@";
	Connection conn = null;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("드라이버 로딩 성공!");
		
		conn = DriverManager.getConnection(url);
		System.out.println("디비 연결 성공!");
		
		Statement stmt = conn.createStatement();
		String sql;
		String uselibrary = "use library";
		stmt.executeUpdate(uselibrary);
		
		sql ="insert into user(ID, PASSWORD, USERNAME, AGE, ADDRESS, TELEPHONE) values ('ckck1410','1234','김창건',24,'마곡동','01089311411')";
			
		int affectedCount = stmt.executeUpdate(sql);
		System.out.println("affectedCount = "+affectedCount);
		
		sql = "select * from user";
		ResultSet rs = stmt.executeQuery(sql);
		
		
		while(rs.next()) {
			String id = rs.getString("id");
			String pw = rs.getString("pw");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String ad = rs.getString("ad");
			String tp = rs.getString("tp");
			
			String result ="";
			result = result.format(" %s\t%s\t%s\t%d\t%s\t%s\t",id,pw,name,age,ad,tp);
			System.out.println(result);
		}	
	}
	catch (Exception e) {
		System.out.println("에러 : "+e.getMessage());
	}
		
	}
	
}
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DB();
		
		}
		

	}