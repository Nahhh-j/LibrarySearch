import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;

public class window_getbookname extends JFrame {
	window_getbookname(int userID)
	{
		super("책 제목 입력");
		FlowLayout flayout = new FlowLayout();
		setLayout(flayout);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		
		JLabel lbl_bookname = new JLabel("예약할 책 제목을 입력하세요.");
		lbl_bookname.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JTextField bookname = new JTextField(10);
		
		JButton submit = new JButton("입력");
		submit.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String query = bookname.getText();
				try {
					window_findbooktolist findbooktolist = new window_findbooktolist(userID, query);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				/*
				FindbookstolistWindow findbooktolise_window = new FindbookstolistWindow(now_user, ); 
				if (logineduser != 0)
				{
					SelectWindow select_window = new SelectWindow();
					select_window.setuser(logineduser);
				}
				*/
			}
		});
		
		p.add(lbl_bookname);
		p.add(bookname);
		p.add(submit);
		
		add(p);
		
		setSize(350, 200);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		if (userID == 0)
		{
			JOptionPane.showMessageDialog(this, "잘못된 사용자 ID입니다!");
			dispose();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		window_getbookname getbookwin = new window_getbookname(0);
	}
}
