import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;

public class LoginWindow extends JFrame {
	LoginWindow()
	{
		super("로그인");
		FlowLayout flayout = new FlowLayout();
		setLayout(flayout);
		
		JPanel p = new JPanel();
		
		p.setLayout(new GridLayout(2, 2));
		
		EtchedBorder border = new EtchedBorder();	
		p.setBorder(border);
		
		JLabel lbl_name = new JLabel("이름");
		lbl_name.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JLabel lbl_password = new JLabel("패스워드");
		lbl_password.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JTextField ID = new JTextField(10);
		JPasswordField password = new JPasswordField(10);
		
		p.add(lbl_name);
		p.add(ID);
		p.add(lbl_password);
		p.add(password);
		
		add(p);
		
		JButton login = new JButton("로그인");
		login.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					reverse Reverse = new reverse();
					
					String pw = "";
					
					// tf_pw 필드에서 패스워드를 얻어옴, char[] 배열에 저장
					char[] secret_pw = password.getPassword();
					
					// secret_pw 배열에 저장된 암호의 자릿수만큼 for문 돌리면서 cha에 한 글자씩 저장
					for (char cha : secret_pw)
					{
						Character.toString(cha); // cha에 저장된 값을 string으로 변환
						// pw에 저장하기. pw에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
						pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
			 		}
					
					String inputedID = ID.getText();
					String inputedPassword = pw;
					
					int logineduser = Reverse.login(inputedID, inputedPassword);
					
					if (logineduser != 0)
					{
						JOptionPane.showMessageDialog(p, "환영합니다.");
						SelectWindow select_window = new SelectWindow();
						select_window.setuser(logineduser);
					}
					else
					{
						JOptionPane.showMessageDialog(p, "아이디와 비밀번호가 올바르지 않습니다!");
					}
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); // 컴포넌트에 리스너 등록
		
		add(login);
		
		setSize(300, 140);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginWindow();
	}
}
