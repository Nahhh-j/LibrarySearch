import java.awt.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class find_library extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					find_library frame = new find_library();
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
	public find_library() throws ClassNotFoundException, IOException {
		setTitle("도서관 안내");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 200);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// 배열을 미리 만들어서 가져와둬야 함!

		libraryFind libfind = new libraryFind();
		libfind.setlibarray();
		libfind.set_array();
		String[] attribute = {"번호", "도서관", "전화번호", "주소", "사이트"};
		
		JTable table = new JTable(libfind.tuple, attribute); // 주의! 내용, 애트리뷰트 순서임!
		
		table.getColumn("번호").setPreferredWidth(5);
		table.getColumn("도서관").setPreferredWidth(100);
		table.getColumn("전화번호").setPreferredWidth(75);
		table.getColumn("주소").setPreferredWidth(150);
		table.getColumn("사이트").setPreferredWidth(100);

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		
		JScrollPane scrollPane = new JScrollPane(table, v, h);
		scrollPane.setPreferredSize(new Dimension(600, 150));
		contentPane.add(scrollPane);
		
		this.setVisible(true);

	}

}
