package com.InsertBoard.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.BoardVO;
import com.boardlist.View.BoardListView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class BoardInsertView {

	private JFrame frame;
	private JTextField TitleField;
	private JTextArea ContentField;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel titleLabel;
	private JButton backBtn;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardInsertView window = new BoardInsertView(null, null, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BoardInsertView(String id, String title, String Content) {
		
		initialize(id, title, Content);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id, String title, String Content) {
		frame = new JFrame();
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 856, 671);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton InsertBtn = new JButton("등록");
		InsertBtn.setBackground(new Color(192, 192, 192));
		InsertBtn.setFont(new Font("굴림", Font.BOLD, 12));
		InsertBtn.setIcon(new ImageIcon(BoardInsertView.class.getResource("/img/baidu-line.png")));
		InsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardVO vo = new BoardVO();
				BoardDAO dao = new BoardDAOImpl();
				
				
				BoardListView view = new BoardListView(id);
				String ntitle = TitleField.getText();
				String ncontent = ContentField.getText();
				
				
				vo.setName(id);
				vo.setTitle(ntitle);
				vo.setContent(ncontent);
				dao.insert(vo);
				view.populateTable(1, 10);
				frame.dispose();
				view.showWindow();
			}
		});
		InsertBtn.setBounds(731, 586, 97, 31);
		frame.getContentPane().add(InsertBtn);
		
		TitleField = new JTextField();
		Border Title = BorderFactory.createLineBorder(Color.black);
		TitleField.setBorder(Title);
		TitleField.setBounds(102, 89, 563, 31);
		frame.getContentPane().add(TitleField);
		TitleField.setColumns(10);
		Border textArBorder = BorderFactory.createLineBorder(Color.black);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.white);
		scrollPane.setBounds(12, 130, 816, 446);
		frame.getContentPane().add(scrollPane);
		
		ContentField = new JTextArea();
		scrollPane.setViewportView(ContentField);
		ContentField.setBorder(textArBorder);
		ContentField.setBackground(new Color(255, 255, 255));
		ContentField.setWrapStyleWord(true);
		ContentField.setLineWrap(true);
		ContentField.setColumns(10);
		
		
		lblNewLabel = new JLabel("작성자 : " + id);
		lblNewLabel.setBounds(677, 89, 142, 31);
		frame.getContentPane().add(lblNewLabel);
		
		titleLabel = new JLabel("제목");
		titleLabel.setFont(new Font("나눔고딕", Font.BOLD, 14));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 88, 87, 31);
		frame.getContentPane().add(titleLabel);
		
		backBtn = new JButton("");
		backBtn.setIcon(new ImageIcon(BoardInsertView.class.getResource("/img/arrow-go-back-line.png")));
		backBtn.setBorder(new EmptyBorder(0,0,0,0));
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BoardListView view = new BoardListView(id);
				view.showWindow();
				frame.dispose();
			}
		});
		backBtn.setBackground(Color.WHITE);
		backBtn.setBounds(12, 586, 97, 34);
		frame.getContentPane().add(backBtn);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(BoardInsertView.class.getResource("/img/diary.jpg")));
		lblNewLabel_1.setBounds(53, 29, 151, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(BoardInsertView.class.getResource("/img/dog2.jpg")));
		lblNewLabel_2.setBounds(12, 10, 49, 65);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
	public void showWindow() {
		frame.setVisible(true);
		
	}
	
	public void closeWindow() {
		frame.setVisible(false);
	}
}
