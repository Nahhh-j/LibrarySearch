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
					window_findbooktolist frame = new window_findbooktolist(1, "도서관");
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
		setTitle("도서명 검색 결과 : " + booknamequery);
		
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
			JOptionPane.showMessageDialog(this, "책이 너무 많습니다. 다른 검색어로 시도해보세요.");
			bookerror = true;
			
		}
		else if (Reverse.no_books == true)
		{
			JOptionPane.showMessageDialog(this, "찾는 책이 없습니다.");
			bookerror = true;
		}
		
		String[] attribute = {"책 번호", "도서관", "책 제목", "지은이", "출판사", "연도"};
		JTable table = new JTable();
		
		if (bookerror == false)
		{
			table = new JTable(Reverse.tuple, attribute); // 주의! 내용, 애트리뷰트 순서임!
			// 여기에 크기 조정값 입력
			table.getColumn("책 번호").setPreferredWidth(5);
			table.getColumn("도서관").setPreferredWidth(80);
			table.getColumn("책 제목").setPreferredWidth(150);
			table.getColumn("지은이").setPreferredWidth(100);
			table.getColumn("출판사").setPreferredWidth(100);
			table.getColumn("연도").setPreferredWidth(20);
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
		numchoice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        GhostText ghostText = new GhostText(numchoice, "예약할 책 번호를 입력하세요");
        numchoice.setPreferredSize(new Dimension(300, 35));
        
        JButton querysubmit = new JButton("입력");
		querysubmit.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		querysubmit.setPreferredSize(new Dimension(100, 35));
		querysubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String booknum_query = numchoice.getText();
					// 텍스트필드에 입력한 숫자를 보내야 한다.
					Reverse.makereserve(userID, booknum_query, booknamequery);
					
					if (Reverse.result == 1) // 예약 성공
					{
						JOptionPane.showMessageDialog(p, "사용자명 : " + userID + " 입력받은 책 번호 : " + booknum_query + " 예약이 성공적으로 끝났습니다.");
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(p, "예약에 실패했습니다. Console 창을 확인하십시오.");
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
		}); // 컴포넌트에 리스너 등록
        
        
        p.add(numchoice);
        p.add(querysubmit);
        
        contentPane.add(p);
		
		if (bookerror == false)
		{
			this.setVisible(true);
		}
	}

}
