import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class find_books extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					find_books frame = new find_books(1, "도서관");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public find_books(int type, String userinput) throws ClassNotFoundException, IOException {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		switch (type)
		{
		case 1:
			setTitle("도서명 검색 결과 : " + userinput);
			break;
		case 2:
			setTitle("저자명 검색 결과 : " + userinput);
			break;
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 660);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// super("테이블 생성");
		// setLayout(new BorderLayout());
		
		// 배열을 미리 만들어서 가져와둬야 함!
		BookFind bookfind = new BookFind();
		AuthorFind authorfind = new AuthorFind();
		
		switch (type)
		{
		case 1:
			bookfind.setbookarray(userinput);
			bookfind.set_array();
			if (bookfind.too_many_books == true)
			{
				JOptionPane.showMessageDialog(this, "책이 너무 많습니다. 다른 검색어로 시도해보세요.");
				type = 3;
			}
			else if (bookfind.no_books == true)
			{
				JOptionPane.showMessageDialog(this, "찾는 책이 없습니다.");
				type = 3;
			}
			break;
		case 2:
			authorfind.setbookarray(userinput);
			authorfind.set_array();
			if (authorfind.too_many_books == true)
			{
				JOptionPane.showMessageDialog(this, "책이 너무 많습니다. 다른 검색어로 시도해보세요.");
				type = 3;
			}
			else if (authorfind.no_books == true)
			{
				JOptionPane.showMessageDialog(this, "찾는 책이 없습니다.");
				type = 3;
			}
			break;
		}

		String[] attribute = {"도서관", "책 제목", "지은이", "출판사", "연도"};
		JTable table = new JTable();
		
		switch (type)
		{
		case 1:
			table = new JTable(bookfind.tuple, attribute); // 주의! 내용, 애트리뷰트 순서임!
			// 여기에 크기 조정값 입력
			table.getColumn("도서관").setPreferredWidth(80);
			table.getColumn("책 제목").setPreferredWidth(150);
			table.getColumn("지은이").setPreferredWidth(100);
			table.getColumn("출판사").setPreferredWidth(100);
			table.getColumn("연도").setPreferredWidth(20);
			break;
		case 2:
			table = new JTable(authorfind.tuple, attribute); // 주의! 내용, 애트리뷰트 순서임!
			// 여기에 크기 조정값 입력
			table.getColumn("도서관").setPreferredWidth(80);
			table.getColumn("책 제목").setPreferredWidth(150);
			table.getColumn("지은이").setPreferredWidth(100);
			table.getColumn("출판사").setPreferredWidth(100);
			table.getColumn("연도").setPreferredWidth(20);
			break;
		case 3:
			break;
		}
		
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		
		JScrollPane scrollPane = new JScrollPane(table, v, h);
		scrollPane.setPreferredSize(new Dimension(600, 600));
		contentPane.add(scrollPane);
		
		if (type != 3)
		{
			this.setVisible(true);
		}
	}

}
