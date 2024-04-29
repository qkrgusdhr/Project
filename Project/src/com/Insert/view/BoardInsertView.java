package com.Insert.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.boardVO;

import javax.swing.JButton;

public class BoardInsertView {

	private JFrame frame;
	private JTextField textTitle;
	private JTextField textField;
	private JTextField nameText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardInsertView window = new BoardInsertView();
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
	public BoardInsertView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 679);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textTitle = new JTextField();
		textTitle.setBounds(79, 73, 627, 33);
		frame.getContentPane().add(textTitle);
		textTitle.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(79, 126, 627, 385);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton InsertBtn = new JButton("등록");
		InsertBtn.setBounds(609, 553, 97, 23);
		InsertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BoardDAO dao = new BoardDAOImpl();
				boardVO vo = new boardVO();
				
				String title = textTitle.getText();
				String writer = nameText.getText();
				String content = textField.getText();
				
				vo.setTitle(title);
				vo.setName(writer);
				vo.setContent(content);
				
				dao.insert(vo);
			}
		});
		frame.getContentPane().add(InsertBtn);
		
		nameText = new JTextField();
		nameText.setBounds(79, 26, 116, 21);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);
	}
}
