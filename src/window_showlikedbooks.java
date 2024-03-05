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
		setTitle("���ɸ�� ��ȸ");
		
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
			JOptionPane.showMessageDialog(this, "å�� �ʹ� �����ϴ�. �ٸ� �˻���� �õ��غ�����.");
		}
		else if (Reverse.like_no_books == true)
		{
			JOptionPane.showMessageDialog(this, "ã�� å�� �����ϴ�.");
		}
		
		String[] attribute = {"������", "å ����", "������", "���ǻ�", "���ǳ⵵", "����ڹ�ȣ"};
		JTable table = new JTable();
		
		table = new JTable(Reverse.like_tuple, attribute); // ����! ����, ��Ʈ����Ʈ ������!
		// ���⿡ ũ�� ������ �Է�
		table.getColumn("������").setPreferredWidth(80);
		table.getColumn("å ����").setPreferredWidth(150);
		table.getColumn("������").setPreferredWidth(75);
		table.getColumn("���ǻ�").setPreferredWidth(75);
		table.getColumn("���ǳ⵵").setPreferredWidth(50);
		table.getColumn("����ڹ�ȣ").setPreferredWidth(50);
		
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		
		JScrollPane scrollPane = new JScrollPane(table, v, h);
		scrollPane.setPreferredSize(new Dimension(600, 600));
		contentPane.add(scrollPane);
		
		this.setVisible(true);
	}

}
