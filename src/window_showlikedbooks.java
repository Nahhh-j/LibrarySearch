import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class window_showlikedbooks extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window_showlikedbooks frame = new window_showlikedbooks(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public window_showlikedbooks(int userID) throws ClassNotFoundException, IOException {
		setTitle("관심목록 조회");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 660);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		reverse Reverse = new reverse();
		
		Reverse.findlike(userID);
		Reverse.like_set_array();
		if (Reverse.like_too_many_books == true)
		{
			JOptionPane.showMessageDialog(this, "책이 너무 많습니다. 다른 검색어로 시도해보세요.");
		}
		else if (Reverse.like_no_books == true)
		{
			JOptionPane.showMessageDialog(this, "찾는 책이 없습니다.");
		}
		
		String[] attribute = {"도서관", "책 제목", "지은이", "출판사", "출판년도", "사용자번호"};
		JTable table = new JTable();
		
		table = new JTable(Reverse.like_tuple, attribute); // 주의! 내용, 애트리뷰트 순서임!
		// 여기에 크기 조정값 입력
		table.getColumn("도서관").setPreferredWidth(80);
		table.getColumn("책 제목").setPreferredWidth(150);
		table.getColumn("지은이").setPreferredWidth(75);
		table.getColumn("출판사").setPreferredWidth(75);
		table.getColumn("출판년도").setPreferredWidth(50);
		table.getColumn("사용자번호").setPreferredWidth(50);
		
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		
		JScrollPane scrollPane = new JScrollPane(table, v, h);
		scrollPane.setPreferredSize(new Dimension(600, 600));
		contentPane.add(scrollPane);
		
		this.setVisible(true);
	}

}
