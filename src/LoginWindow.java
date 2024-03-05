import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;

public class LoginWindow extends JFrame {
	LoginWindow()
	{
		super("�α���");
		FlowLayout flayout = new FlowLayout();
		setLayout(flayout);
		
		JPanel p = new JPanel();
		
		p.setLayout(new GridLayout(2, 2));
		
		EtchedBorder border = new EtchedBorder();	
		p.setBorder(border);
		
		JLabel lbl_name = new JLabel("�̸�");
		lbl_name.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JLabel lbl_password = new JLabel("�н�����");
		lbl_password.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JTextField ID = new JTextField(10);
		JPasswordField password = new JPasswordField(10);
		
		p.add(lbl_name);
		p.add(ID);
		p.add(lbl_password);
		p.add(password);
		
		add(p);
		
		JButton login = new JButton("�α���");
		login.setFont(new Font("���� ���", Font.PLAIN, 13));
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					reverse Reverse = new reverse();
					
					String pw = "";
					
					// tf_pw �ʵ忡�� �н����带 ����, char[] �迭�� ����
					char[] secret_pw = password.getPassword();
					
					// secret_pw �迭�� ����� ��ȣ�� �ڸ�����ŭ for�� �����鼭 cha�� �� ���ھ� ����
					for (char cha : secret_pw)
					{
						Character.toString(cha); // cha�� ����� ���� string���� ��ȯ
						// pw�� �����ϱ�. pw�� ���� ��������� ����, ���� ������ �̾ �����ϴ� ���׿�����
						pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
			 		}
					
					String inputedID = ID.getText();
					String inputedPassword = pw;
					
					int logineduser = Reverse.login(inputedID, inputedPassword);
					
					if (logineduser != 0)
					{
						JOptionPane.showMessageDialog(p, "ȯ���մϴ�.");
						SelectWindow select_window = new SelectWindow();
						select_window.setuser(logineduser);
					}
					else
					{
						JOptionPane.showMessageDialog(p, "���̵�� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�!");
					}
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); // ������Ʈ�� ������ ���
		
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
