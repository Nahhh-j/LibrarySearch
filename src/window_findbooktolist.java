import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.FlowLayout;

public class window_findbooktolist extends JFrame {

	private JPanel contentPane;
	
	public boolean bookerror;
	
	public static class GhostText implements FocusListener, DocumentListener, PropertyChangeListener {
        private final JTextField textfield;
        private boolean isEmpty;
        private Color ghostColor;
        private Color foregroundColor;
        private final String ghostText;

        protected GhostText(final JTextField textfield, String ghostText) {
            super();
            this.textfield = textfield;
            this.ghostText = ghostText;
            this.ghostColor = Color.LIGHT_GRAY;
            textfield.addFocusListener(this);
            registerListeners();
            updateState();
            if (!this.textfield.hasFocus()) {
                focusLost(null);
            }
        }

        public void delete() {
            unregisterListeners();
            textfield.removeFocusListener(this);
        }

        private void registerListeners() {
            textfield.getDocument().addDocumentListener(this);
            textfield.addPropertyChangeListener("foreground", this);
        }

        private void unregisterListeners() {
            textfield.getDocument().removeDocumentListener(this);
            textfield.removePropertyChangeListener("foreground", this);
        }

        public Color getGhostColor() {
            return ghostColor;
        }

        public void setGhostColor(Color ghostColor) {
            this.ghostColor = ghostColor;
        }

        private void updateState() {
            isEmpty = textfield.getText().length() == 0;
            foregroundColor = textfield.getForeground();
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (isEmpty) {
                unregisterListeners();
                try {
                    textfield.setText("");
                    textfield.setForeground(foregroundColor);
                } finally {
                    registerListeners();
                }
            }

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (isEmpty) {
                unregisterListeners();
                try {
                    textfield.setText(ghostText);
                    textfield.setForeground(ghostColor);
                } finally {
                    registerListeners();
                }
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            updateState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateState();
        }

    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window_findbooktolist frame = new window_findbooktolist(1, "������");
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
	public window_findbooktolist(int userID, String booknamequery) throws ClassNotFoundException, IOException {
		setTitle("������ �˻� ��� : " + booknamequery);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		reverse Reverse = new reverse();
		
		Reverse.findbook(userID, booknamequery);
		Reverse.set_array();
		
		if (Reverse.too_many_books == true)
		{
			JOptionPane.showMessageDialog(this, "å�� �ʹ� �����ϴ�. �ٸ� �˻���� �õ��غ�����.");
			bookerror = true;
			
		}
		else if (Reverse.no_books == true)
		{
			JOptionPane.showMessageDialog(this, "ã�� å�� �����ϴ�.");
			bookerror = true;
		}
		
		String[] attribute = {"å ��ȣ", "������", "å ����", "������", "���ǻ�", "����"};
		JTable table = new JTable();
		
		if (bookerror == false)
		{
			table = new JTable(Reverse.tuple, attribute); // ����! ����, ��Ʈ����Ʈ ������!
			// ���⿡ ũ�� ������ �Է�
			table.getColumn("å ��ȣ").setPreferredWidth(5);
			table.getColumn("������").setPreferredWidth(80);
			table.getColumn("å ����").setPreferredWidth(150);
			table.getColumn("������").setPreferredWidth(100);
			table.getColumn("���ǻ�").setPreferredWidth(100);
			table.getColumn("����").setPreferredWidth(20);
		}
		else
		{
			
		}
		
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane(table, v, h);
		scrollPane.setPreferredSize(new Dimension(600, 600));
		contentPane.add(scrollPane);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		EtchedBorder border = new EtchedBorder();	
		p.setBorder(border);
		p.setPreferredSize(new Dimension(500, 35));
		
		JTextField numchoice = new JTextField();
		numchoice.setFont(new Font("���� ���", Font.PLAIN, 15));
        GhostText ghostText = new GhostText(numchoice, "������ å ��ȣ�� �Է��ϼ���");
        numchoice.setPreferredSize(new Dimension(300, 35));
        
        JButton querysubmit = new JButton("�Է�");
		querysubmit.setFont(new Font("���� ���", Font.PLAIN, 13));
		querysubmit.setPreferredSize(new Dimension(100, 35));
		querysubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String booknum_query = numchoice.getText();
					// �ؽ�Ʈ�ʵ忡 �Է��� ���ڸ� ������ �Ѵ�.
					Reverse.makereserve(userID, booknum_query, booknamequery);
					
					if (Reverse.result == 1) // ���� ����
					{
						JOptionPane.showMessageDialog(p, "����ڸ� : " + userID + " �Է¹��� å ��ȣ : " + booknum_query + " ������ ���������� �������ϴ�.");
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(p, "���࿡ �����߽��ϴ�. Console â�� Ȯ���Ͻʽÿ�.");
						dispose();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); // ������Ʈ�� ������ ���
        
        
        p.add(numchoice);
        p.add(querysubmit);
        
        contentPane.add(p);
		
		if (bookerror == false)
		{
			this.setVisible(true);
		}
	}

}
