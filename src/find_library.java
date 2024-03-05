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
		setTitle("������ �ȳ�");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 200);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// �迭�� �̸� ���� �����͵־� ��!

		libraryFind libfind = new libraryFind();
		libfind.setlibarray();
		libfind.set_array();
		String[] attribute = {"��ȣ", "������", "��ȭ��ȣ", "�ּ�", "����Ʈ"};
		
		JTable table = new JTable(libfind.tuple, attribute); // ����! ����, ��Ʈ����Ʈ ������!
		
		table.getColumn("��ȣ").setPreferredWidth(5);
		table.getColumn("������").setPreferredWidth(100);
		table.getColumn("��ȭ��ȣ").setPreferredWidth(75);
		table.getColumn("�ּ�").setPreferredWidth(150);
		table.getColumn("����Ʈ").setPreferredWidth(100);

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		
		JScrollPane scrollPane = new JScrollPane(table, v, h);
		scrollPane.setPreferredSize(new Dimension(600, 150));
		contentPane.add(scrollPane);
		
		this.setVisible(true);

	}

}
