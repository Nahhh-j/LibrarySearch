import javax.swing.*; // 스윙 UI 사용
import javax.swing.border.EmptyBorder;

import java.awt.*; // 레이아웃 매니저
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class window_memberjoin extends JFrame {
	JLabel lb_ID, lb_password, lb_name, lb_age, lb_address, lb_phonenumber;
	JLabel lb1, lb2, lb3;
	JTextField tf_ID, tf_name, tf_age, tf_address, tf_phonenumber;
	JPasswordField pf_password;
	JTextArea ta_address;
	
	window_memberjoin()
	{
		super("회원 가입");
		
		setLayout(new FlowLayout()); // Default는 BorderLayout(동서남북중앙 정하기), FlowLayout는 화면 크기에 따라서 순서대로 나오는 방식
		// 자동으로 화면 크기에 맞춰주기 때문에 크기조정을 하면 레이아웃이 흐트러질 수 있다.
		
		// 차례대로 객체를 생성해준다. 레이블에 먼저 add해서는 안되고 순서대로 해주어야 한다.
		lb_ID = new JLabel("ID");
		tf_ID = new JTextField(20);
		lb_password = new JLabel("비밀번호");
		pf_password = new JPasswordField(20);
		lb_name = new JLabel("이름");
		tf_name = new JTextField(20);
		lb_age = new JLabel("나이");
		tf_age = new JTextField(20);
		lb_address = new JLabel("주소");
		tf_address = new JTextField(20);
		lb_phonenumber = new JLabel("전화번호");
		tf_phonenumber = new JTextField(20);
		
		lb_ID.setPreferredSize(new Dimension(50, 25));
		lb_ID.setBorder(new EmptyBorder(0, 23, 0, 0));
		lb_ID.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lb_password.setPreferredSize(new Dimension(50, 25));
		lb_password.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lb_name.setPreferredSize(new Dimension(50, 25));
		lb_name.setBorder(new EmptyBorder(0, 15, 0, 0));
		lb_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lb_age.setPreferredSize(new Dimension(50, 25));
		lb_age.setBorder(new EmptyBorder(0, 15, 0, 0));
		lb_age.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lb_address.setPreferredSize(new Dimension(50, 25));
		lb_address.setBorder(new EmptyBorder(0, 15, 0, 0));
		lb_address.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lb_phonenumber.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		
		JButton submit = new JButton("회원가입");
		submit.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					memberjoin join = new memberjoin();
					
					String pw = "";
					
					// tf_pw 필드에서 패스워드를 얻어옴, char[] 배열에 저장
					char[] secret_pw = pf_password.getPassword();
					
					// secret_pw 배열에 저장된 암호의 자릿수만큼 for문 돌리면서 cha에 한 글자씩 저장
					for (char cha : secret_pw)
					{
						Character.toString(cha); // cha에 저장된 값을 string으로 변환
						// pw에 저장하기. pw에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
						pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
			 		}
					
					// 아이디, 비밀번호, 이름, 나이, 주소, 전화번호
					
					String ID = tf_ID.getText();
					String Password = pw;
					String Name = tf_name.getText();
					String Age = tf_age.getText();
					String Address = tf_address.getText();
					String Phonenumber = tf_phonenumber.getText();
					
					if (ID.length() < 1 || pw.length() < 1 || Name.length() < 1 || Age.length() < 1 || Address.length() < 1 || Phonenumber.length() < 1)
					{
						JOptionPane.showMessageDialog(null, "잘못된 입력입니다!");
						dispose();
					}
					else
					{
						join.join(ID, Password, Name, Age, Address, Phonenumber);
					}
					
					if (join.successed == 1)
					{
						JOptionPane.showMessageDialog(null, Name + "님의 회원가입이 정상적으로 완료되었습니다.");
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, Name + "님의 회원가입이 정상적으로 완료되지 않았습니다.");
						dispose();
					}
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); // 컴포넌트에 리스너 등록
		
		add(lb_ID);
		add(tf_ID);
		add(lb_password);
		add(pf_password);
		add(lb_name);
		add(tf_name);
		add(lb_age);
		add(tf_age);
		add(lb_address);
		add(tf_address);
		add(lb_phonenumber);
		add(tf_phonenumber);
		add(submit);
		
		// 
		setSize(300, 260); // 사이즈 설정
		setResizable(false);
		setVisible(true); // 화면에 실제로 표시되도록 설정
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 닫기 버튼 활성화
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new window_memberjoin(); // 객체(인스턴스) 이름을 지정하지 않았기 때문
		
		// main에서 객체를 생성한 경우
		// JLabelTextJFrame it = new JLabelTextJFrame();
		// it.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}
