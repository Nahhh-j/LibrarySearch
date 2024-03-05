import javax.swing.*; // ���� UI ���
import javax.swing.border.EmptyBorder;

import java.awt.*; // ���̾ƿ� �Ŵ���
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
		super("ȸ�� ����");
		
		setLayout(new FlowLayout()); // Default�� BorderLayout(���������߾� ���ϱ�), FlowLayout�� ȭ�� ũ�⿡ ���� ������� ������ ���
		// �ڵ����� ȭ�� ũ�⿡ �����ֱ� ������ ũ�������� �ϸ� ���̾ƿ��� ��Ʈ���� �� �ִ�.
		
		// ���ʴ�� ��ü�� �������ش�. ���̺� ���� add�ؼ��� �ȵǰ� ������� ���־�� �Ѵ�.
		lb_ID = new JLabel("ID");
		tf_ID = new JTextField(20);
		lb_password = new JLabel("��й�ȣ");
		pf_password = new JPasswordField(20);
		lb_name = new JLabel("�̸�");
		tf_name = new JTextField(20);
		lb_age = new JLabel("����");
		tf_age = new JTextField(20);
		lb_address = new JLabel("�ּ�");
		tf_address = new JTextField(20);
		lb_phonenumber = new JLabel("��ȭ��ȣ");
		tf_phonenumber = new JTextField(20);
		
		lb_ID.setPreferredSize(new Dimension(50, 25));
		lb_ID.setBorder(new EmptyBorder(0, 23, 0, 0));
		lb_ID.setFont(new Font("���� ���", Font.PLAIN, 12));
		lb_password.setPreferredSize(new Dimension(50, 25));
		lb_password.setFont(new Font("���� ���", Font.PLAIN, 12));
		lb_name.setPreferredSize(new Dimension(50, 25));
		lb_name.setBorder(new EmptyBorder(0, 15, 0, 0));
		lb_name.setFont(new Font("���� ���", Font.PLAIN, 12));
		lb_age.setPreferredSize(new Dimension(50, 25));
		lb_age.setBorder(new EmptyBorder(0, 15, 0, 0));
		lb_age.setFont(new Font("���� ���", Font.PLAIN, 12));
		lb_address.setPreferredSize(new Dimension(50, 25));
		lb_address.setBorder(new EmptyBorder(0, 15, 0, 0));
		lb_address.setFont(new Font("���� ���", Font.PLAIN, 12));
		lb_phonenumber.setFont(new Font("���� ���", Font.PLAIN, 12));
		
		JButton submit = new JButton("ȸ������");
		submit.setFont(new Font("���� ���", Font.PLAIN, 13));
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					memberjoin join = new memberjoin();
					
					String pw = "";
					
					// tf_pw �ʵ忡�� �н����带 ����, char[] �迭�� ����
					char[] secret_pw = pf_password.getPassword();
					
					// secret_pw �迭�� ����� ��ȣ�� �ڸ�����ŭ for�� �����鼭 cha�� �� ���ھ� ����
					for (char cha : secret_pw)
					{
						Character.toString(cha); // cha�� ����� ���� string���� ��ȯ
						// pw�� �����ϱ�. pw�� ���� ��������� ����, ���� ������ �̾ �����ϴ� ���׿�����
						pw += (pw.equals("")) ? "" + cha + "" : "" + cha + "";
			 		}
					
					// ���̵�, ��й�ȣ, �̸�, ����, �ּ�, ��ȭ��ȣ
					
					String ID = tf_ID.getText();
					String Password = pw;
					String Name = tf_name.getText();
					String Age = tf_age.getText();
					String Address = tf_address.getText();
					String Phonenumber = tf_phonenumber.getText();
					
					if (ID.length() < 1 || pw.length() < 1 || Name.length() < 1 || Age.length() < 1 || Address.length() < 1 || Phonenumber.length() < 1)
					{
						JOptionPane.showMessageDialog(null, "�߸��� �Է��Դϴ�!");
						dispose();
					}
					else
					{
						join.join(ID, Password, Name, Age, Address, Phonenumber);
					}
					
					if (join.successed == 1)
					{
						JOptionPane.showMessageDialog(null, Name + "���� ȸ�������� ���������� �Ϸ�Ǿ����ϴ�.");
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, Name + "���� ȸ�������� ���������� �Ϸ���� �ʾҽ��ϴ�.");
						dispose();
					}
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); // ������Ʈ�� ������ ���
		
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
		setSize(300, 260); // ������ ����
		setResizable(false);
		setVisible(true); // ȭ�鿡 ������ ǥ�õǵ��� ����
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �ݱ� ��ư Ȱ��ȭ
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new window_memberjoin(); // ��ü(�ν��Ͻ�) �̸��� �������� �ʾұ� ����
		
		// main���� ��ü�� ������ ���
		// JLabelTextJFrame it = new JLabelTextJFrame();
		// it.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}
