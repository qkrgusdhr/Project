package com.UpdateBoard.View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;


import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.BoardVO;

import com.showPost.view.ShowPost;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class UpdateBoardView {

	private JFrame frame;
	private JTextField TitleField;
	private JTextArea ContentField;
	private JButton UpdateBtn;
	private JLabel WriterLabel;
	private BoardVO vo;
	private JScrollPane scrollPane;
	private String userID;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBoardView window = new UpdateBoardView(null, null, null, null, 0,0);
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
	public UpdateBoardView(String userID,String id, String title, String content, int boardNum, int LikeCnt) {
		this.userID = userID;
		
		initialize(id, title, content, boardNum, LikeCnt);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id, String title, String content, int boardNum, int LikeCnt) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 678, 580);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// boardVO 객체 생성 및 초기화
		vo = new BoardVO();
		vo.setName(id);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNum(boardNum);
		
		TitleField = new JTextField();
		TitleField.setBounds(103, 62, 401, 21);
		frame.getContentPane().add(TitleField);
		TitleField.setText(vo.getTitle());
		TitleField.setColumns(10);
		

		UpdateBtn = new JButton("수정하기");
		UpdateBtn.setBounds(499, 465, 97, 23);
		UpdateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newTitle = TitleField.getText();
				String newContent = ContentField.getText();

				BoardDAO dao = new BoardDAOImpl();
				BoardVO vo = new BoardVO();
				vo.setNum(boardNum);
				vo.setTitle(newTitle);
				vo.setContent(newContent);
				dao.update(vo);
				
				frame.dispose();

				
				ShowPost showPost = new ShowPost(userID, id, newTitle, newContent, boardNum, LikeCnt);
				showPost.showWindow();
				showPost.UpdateLabel();
				frame.dispose();
				
				
			}
		});
		frame.getContentPane().add(UpdateBtn);

		WriterLabel = new JLabel("New label");
		WriterLabel.setBounds(551, 23, 57, 15);
		WriterLabel.setText(vo.getName());
		frame.getContentPane().add(WriterLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(103, 103, 401, 305);
		frame.getContentPane().add(scrollPane);
		
				ContentField = new JTextArea();
				scrollPane.setViewportView(ContentField);
				ContentField.setLineWrap(true);
				ContentField.setBorder(new EmptyBorder(0, 0, 0, 0));
				ContentField.setText(vo.getContent());
				ContentField.setColumns(10);
	}

	public void showWindow() {
		frame.setVisible(true);
	}

}
