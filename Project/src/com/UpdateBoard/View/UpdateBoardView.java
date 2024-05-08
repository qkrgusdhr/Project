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
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class UpdateBoardView {

	private JFrame frame;
	private JTextArea ContentField;
	private JButton UpdateBtn;
	private JLabel WriterLabel;
	private BoardVO vo;
	private JScrollPane scrollPane;
	private String userID;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField TitleField;
	private JButton backBtn;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBoardView window = new UpdateBoardView(null, null, null, null, 0, 0);
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
	public UpdateBoardView(String userID, String id, String title, String content, int boardNum, int LikeCnt) {
		this.userID = userID;

		initialize(id, title, content, boardNum, LikeCnt);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id, String title, String content, int boardNum, int LikeCnt) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 856, 671);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// boardVO 객체 생성 및 초기화
		vo = new BoardVO();
		vo.setName(id);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNum(boardNum);

		UpdateBtn = new JButton("수정하기");
		UpdateBtn.setIcon(new ImageIcon(UpdateBoardView.class.getResource("/img/edit-line.png")));
		UpdateBtn.setFont(new Font("굴림", Font.BOLD, 12));
		UpdateBtn.setBackground(new Color(192, 192, 192));
		UpdateBtn.setBounds(711, 586, 117, 31);
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
		WriterLabel.setFont(new Font("굴림", Font.BOLD, 14));
		WriterLabel.setBounds(677, 89, 151, 31);
		WriterLabel.setText("작성자 : " + vo.getName());
		frame.getContentPane().add(WriterLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 130, 816, 446);
		frame.getContentPane().add(scrollPane);

		ContentField = new JTextArea();
		scrollPane.setViewportView(ContentField);
		ContentField.setLineWrap(true);
		ContentField.setBorder(new EmptyBorder(0, 0, 0, 0));
		ContentField.setText(vo.getContent());
		ContentField.setColumns(10);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UpdateBoardView.class.getResource("/img/diary.jpg")));
		lblNewLabel.setBounds(53, 29, 151, 42);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(UpdateBoardView.class.getResource("/img/dog2.jpg")));
		lblNewLabel_1.setBounds(12, 10, 49, 65);
		frame.getContentPane().add(lblNewLabel_1);

		TitleField = new JTextField();
		TitleField.setText(vo.getTitle());
		TitleField.setColumns(10);
		TitleField.setBounds(102, 89, 563, 31);
		frame.getContentPane().add(TitleField);
		
		backBtn = new JButton("");
		backBtn.setIcon(new ImageIcon(UpdateBoardView.class.getResource("/img/arrow-go-back-line.png")));
		backBtn.setBorder(new EmptyBorder(0,0,0,0));
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ShowPost post = new ShowPost(userID, id, title, content, boardNum, LikeCnt);
				post.showWindow();
				frame.dispose();
			}
		});
		backBtn.setBackground(Color.WHITE);
		backBtn.setBounds(12, 586, 97, 34);
		frame.getContentPane().add(backBtn);
		
		lblNewLabel_2 = new JLabel("제목");
		lblNewLabel_2.setFont(new Font("나눔고딕", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(12, 89, 78, 31);
		frame.getContentPane().add(lblNewLabel_2);
	}

	public void showWindow() {
		frame.setVisible(true);
	}

}
