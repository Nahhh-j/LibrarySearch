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
					find_books frame = new find_books(1, "������");
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
			setTitle("������ �˻� ��� : " + userinput);
			break;
		case 2:
			setTitle("���ڸ� �˻� ��� : " + userinput);
			break;
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 660);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// super("���̺� ����");
		// setLayout(new BorderLayout());
		
		// �迭�� �̸� ���� �����͵־� ��!
		BookFind bookfind = new BookFind();
		AuthorFind authorfind = new AuthorFind();
		
		switch (type)
		{
		case 1:
			bookfind.setbookarray(userinput);
			bookfind.set_array();
			if (bookfind.too_many_books == true)
			{
				JOptionPane.showMessageDialog(this, "å�� �ʹ� �����ϴ�. �ٸ� �˻���� �õ��غ�����.");
				type = 3;
			}
			else if (bookfind.no_books == true)
			{
				JOptionPane.showMessageDialog(this, "ã�� å�� �����ϴ�.");
				type = 3;
			}
			break;
		case 2:
			authorfind.setbookarray(userinput);
			authorfind.set_array();
			if (authorfind.too_many_books == true)
			{
				JOptionPane.showMessageDialog(this, "å�� �ʹ� �����ϴ�. �ٸ� �˻���� �õ��غ�����.");
				type = 3;
			}
			else if (authorfind.no_books == true)
			{
				JOptionPane.showMessageDialog(this, "ã�� å�� �����ϴ�.");
				type = 3;
			}
			break;
		}

		String[] attribute = {"������", "å ����", "������", "���ǻ�", "����"};
		JTable table = new JTable();
		
		switch (type)
		{
		case 1:
			table = new JTable(bookfind.tuple, attribute); // ����! ����, ��Ʈ����Ʈ ������!
			// ���⿡ ũ�� ������ �Է�
			table.getColumn("������").setPreferredWidth(80);
			table.getColumn("å ����").setPreferredWidth(150);
			table.getColumn("������").setPreferredWidth(100);
			table.getColumn("���ǻ�").setPreferredWidth(100);
			table.getColumn("����").setPreferredWidth(20);
			break;
		case 2:
			table = new JTable(authorfind.tuple, attribute); // ����! ����, ��Ʈ����Ʈ ������!
			// ���⿡ ũ�� ������ �Է�
			table.getColumn("������").setPreferredWidth(80);
			table.getColumn("å ����").setPreferredWidth(150);
			table.getColumn("������").setPreferredWidth(100);
			table.getColumn("���ǻ�").setPreferredWidth(100);
			table.getColumn("����").setPreferredWidth(20);
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
