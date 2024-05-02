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

public class BoardInsertView {

	private JFrame frame;
	private JTextField TitleField;
	private JTextArea ContentField;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;

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
		frame.setBounds(100, 100, 721, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton InsertBtn = new JButton("등록");
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
		InsertBtn.setBounds(571, 453, 97, 23);
		frame.getContentPane().add(InsertBtn);
		
		TitleField = new JTextField();
		Border Title = BorderFactory.createLineBorder(Color.black);
		TitleField.setBorder(Title);
		TitleField.setBounds(108, 70, 342, 31);
		frame.getContentPane().add(TitleField);
		TitleField.setColumns(10);
		Border textArBorder = BorderFactory.createLineBorder(Color.black);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.white);
		scrollPane.setBounds(106, 111, 485, 332);
		frame.getContentPane().add(scrollPane);
		
		ContentField = new JTextArea();
		scrollPane.setViewportView(ContentField);
		ContentField.setBorder(textArBorder);
		ContentField.setBackground(new Color(255, 255, 255));
		ContentField.setWrapStyleWord(true);
		ContentField.setLineWrap(true);
		ContentField.setColumns(10);
		
		
		lblNewLabel = new JLabel("작성자 : " + id);
		lblNewLabel.setBounds(462, 70, 129, 31);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void showWindow() {
		frame.setVisible(true);
		
	}
	
	public void closeWindow() {
		frame.setVisible(false);
	}
}
