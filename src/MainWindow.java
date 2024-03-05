import java.awt.EventQueue;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;

import javax.swing.GroupLayout.Alignment;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import net.miginfocom.swing.MigLayout;
import java.awt.Font;

import java.awt.color.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow implements ActionListener {

	private JFrame main_menu;
	private JTextField textfield_searchquery;
	
	JButton bt_library, bt_person, bt_join;
	JRadioButton rdbtn_book, rdbtn_author;
	
	public int selectedtype = 1;

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
					MainWindow window = new MainWindow();
					window.main_menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() { // 생성자
		ImageIcon icon_books = new ImageIcon("./image/Icon_Books.png");
		Image tempimg = icon_books.getImage();
		Image icon_books_change = tempimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon_books = new ImageIcon(icon_books_change);
		
		ImageIcon icon_library = new ImageIcon("./image/Icon_Library.png");
		tempimg = icon_library.getImage();
		Image icon_library_change = tempimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon_library = new ImageIcon(icon_library_change);
		
		ImageIcon icon_person = new ImageIcon("./image/Icon_Person.png");
		tempimg = icon_person.getImage();
		Image icon_person_change = tempimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon_person = new ImageIcon(icon_person_change);
		
		ImageIcon icon_search = new ImageIcon("./image/Icon_Search.png");
		tempimg = icon_search.getImage();
		Image icon_search_change = tempimg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon_search = new ImageIcon(icon_search_change);
		
		ImageIcon icon_door = new ImageIcon("./image/Icon_Door.png");
		tempimg = icon_door.getImage();
		Image icon_door_change = tempimg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon_door = new ImageIcon(icon_door_change);
		
		ImageIcon icon_sheet = new ImageIcon("./image/Icon_Sheet.png");
		tempimg = icon_sheet.getImage();
		Image icon_sheet_change = tempimg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon_sheet = new ImageIcon(icon_sheet_change);
		
		ImageIcon anyang_logo = new ImageIcon("./image/anyang_logo.png");
		tempimg = anyang_logo.getImage();
		Image anyang_logo_change = tempimg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		anyang_logo = new ImageIcon(anyang_logo_change);
		
		main_menu = new JFrame("안양시 공공도서관");
		main_menu.setResizable(false);
		main_menu.setBounds(100, 100, 300, 450);
		main_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_menu.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel main_search = new JPanel();
		main_search.setBackground(Color.decode("0x1D5C9A"));
		main_menu.getContentPane().add(main_search, BorderLayout.CENTER);
		main_search.setBorder(new EmptyBorder(30, 30, 30, 30));
		main_search.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		
		JPanel main_rdbtn = new JPanel();
		main_rdbtn.setBackground(Color.decode("0x1D5C9A"));
		main_search.add(main_rdbtn);
		
		rdbtn_book = new JRadioButton("도서명", true);
		rdbtn_book.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		rdbtn_book.setForeground(Color.WHITE);
		rdbtn_book.setBackground(Color.decode("0x1D5C9A"));
		
		rdbtn_author = new JRadioButton("저자명", false);
		rdbtn_author.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		rdbtn_author.setForeground(Color.WHITE);
		rdbtn_author.setBackground(Color.decode("0x1D5C9A"));
		
		ButtonGroup name_or_author = new ButtonGroup();
		name_or_author.add(rdbtn_book);
		name_or_author.add(rdbtn_author);
		
		main_rdbtn.add(rdbtn_book);
		main_rdbtn.add(rdbtn_author);
		
		RBHandler rh = new RBHandler();
		
		// 아이템 리스너에 컴포넌트 추가
		// 이 작업을 하지 않으면 버튼을 누르는 등의 액션이 생겨도 반응이 없다.
		rdbtn_book.addItemListener(rh);
		rdbtn_author.addItemListener(rh);
		
		/* <<프레임에 추가순서>>
		 * 1) 체크박스/라디오버튼 생성
		 * 2) 체크박스를 패널 그룹으로 묶음
		 * 3) 라디오버튼 ~ 버튼그룹으로 묶고, 패널로 묶음
		 * 4) 프레임에 컴포넌트를 붙임
		 */
		
		textfield_searchquery = new JTextField();
		textfield_searchquery.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textfield_searchquery.setHorizontalAlignment(SwingConstants.LEFT);
		textfield_searchquery.setColumns(10);
        GhostText ghostText = new GhostText(textfield_searchquery, "검색어 입력");
        main_search.add(textfield_searchquery);
        // textField.setBorder(BorderFactory.createEtchedBorder());
        
        JButton bt_search = new JButton();
		bt_search.setIcon(icon_search);
		bt_search.setPreferredSize(new Dimension(40, 40)); // 버튼 크기 지정
		// bt_search.setHorizontalAlignment(SwingConstants.LEFT);
		// bt_search.setFont(new Font("굴림", Font.PLAIN, 15));

		bt_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String query = textfield_searchquery.getText();
					new find_books(selectedtype, query);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); // 컴포넌트에 리스너 등록
		main_search.add(bt_search);
			
		JPanel main_title = new JPanel();
		main_title.setBackground(Color.decode("0x1D5C9A"));
		main_menu.getContentPane().add(main_title, BorderLayout.NORTH);
		main_title.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		/*
		JLabel lbl_title = new JLabel();
		lbl_title.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		// lbl_title.setFont(new Font("굴림", Font.PLAIN, 40));
		main_title.add(lbl_title);
		*/
		main_title.setLayout(new BorderLayout(0, 0));
		
		JLabel lbl_logo = new JLabel();
		lbl_logo.setText(null);
		lbl_logo.setIcon(anyang_logo);
		lbl_logo.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_logo.setHorizontalAlignment(SwingConstants.CENTER);
		main_title.add(lbl_logo, BorderLayout.CENTER);
		
		JLabel lbl_title = new JLabel("안양시 공공도서관");
		lbl_title.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		main_title.add(lbl_title, BorderLayout.SOUTH);
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBorder(new EmptyBorder(5, 0, 0, 0));
		lbl_title.setForeground(Color.white);
		
		JPanel MainNavigation = new JPanel();
		// MainNavigation.setBorder(new EmptyBorder(10, 10, 10, 10));
		MainNavigation.setBackground(Color.LIGHT_GRAY);
		main_menu.getContentPane().add(MainNavigation, BorderLayout.SOUTH);
		MainNavigation.setLayout(new BorderLayout(10, 10));
		
		bt_library = new JButton();
		bt_library.setIcon(icon_library);
		bt_library.setPreferredSize(new Dimension(50, 50)); // 버튼 크기 지정
		bt_library.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					new find_library();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); // 컴포넌트에 리스너 등록
		MainNavigation.add(bt_library, BorderLayout.WEST);
		
		bt_person = new JButton();
		bt_person.setIcon(icon_person);
		bt_person.setPreferredSize(new Dimension(50, 50)); // 버튼 크기 지정
		bt_person.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new LoginWindow();
			}
		}); // 컴포넌트에 리스너 등록
		MainNavigation.add(bt_person, BorderLayout.CENTER);
		
		bt_join = new JButton();
		bt_join.setIcon(icon_sheet);
		bt_join.setPreferredSize(new Dimension(50, 50)); // 버튼 크기 지정
		bt_join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new window_memberjoin();
			}
		}); // 컴포넌트에 리스너 등록
		MainNavigation.add(bt_join, BorderLayout.EAST);
		// initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/*
	private void initialize() {

	}
	*/

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private class RBHandler implements ItemListener // 내부 클래스 - 이벤트 처리
	{
		// 이벤트에 맞게 취해줄 액션을 정해준다.
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getSource() == rdbtn_book)
				if (e.getStateChange() == ItemEvent.SELECTED)
					selectedtype = 1;
			if (e.getSource() == rdbtn_author)
				if (e.getStateChange() == ItemEvent.SELECTED)
					selectedtype = 2;
		}
	}
}
