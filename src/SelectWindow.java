import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;

public class SelectWindow extends JFrame {
	public int now_user;
	
	SelectWindow()
	{
		super("메뉴 선택 ");
		FlowLayout flayout = new FlowLayout();
		setLayout(flayout);
		
		ImageIcon icon_books = new ImageIcon("./image/Icon_Books.png");
		Image tempimg = icon_books.getImage();
		Image icon_books_change = tempimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon_books = new ImageIcon(icon_books_change);
		
		ImageIcon icon_search = new ImageIcon("./image/Icon_Search.png");
		tempimg = icon_search.getImage();
		Image icon_search_change = tempimg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon_search = new ImageIcon(icon_search_change);
		
		ImageIcon icon_door = new ImageIcon("./image/Icon_Door.png");
		tempimg = icon_door.getImage();
		Image icon_door_change = tempimg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon_door = new ImageIcon(icon_door_change);
		
		JPanel p = new JPanel();
		
		p.setLayout(new GridLayout(3, 1));
		
		EtchedBorder border = new EtchedBorder();	
		p.setBorder(border);
		
		JButton addtomybook = new JButton("책 관심등록");
		addtomybook.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		addtomybook.setIcon(icon_books);
		addtomybook.setPreferredSize(new Dimension(200, 45));
		addtomybook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				window_getbookname getbookname = new window_getbookname(now_user);
			}
		});
		
		JButton searchmybook = new JButton("관심목록 검색");
		searchmybook.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		searchmybook.setIcon(icon_search);
		searchmybook.setPreferredSize(new Dimension(200, 45));
		searchmybook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					window_showlikedbooks showlikedbooks = new window_showlikedbooks(now_user);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton exitbutton = new JButton("나가기");
		exitbutton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		exitbutton.setIcon(icon_door);
		exitbutton.setPreferredSize(new Dimension(200, 45));
		exitbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		// 관심등록은 책으로, 검색은 돋보기, 시스템종료는 문
		
		add(addtomybook);
		add(searchmybook);
		add(exitbutton);
		
		setSize(300, 200);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setuser(int usernum)
	{
		now_user = usernum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SelectWindow();
	}
}
