package com.UpdateBoard.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.boardVO;
import com.boardlist.View.BoardListView;
import com.showPost.view.ShowPost;

import javax.swing.JButton;
import javax.swing.JLabel;

public class UpdateBoardView {

	private JFrame frame;
	private JTextField TitleField;
	private JTextField ContentField;
	private JButton UpdateBtn;
	private JLabel WriterLabel;
	private boardVO vo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBoardView window = new UpdateBoardView(null, null, null, 0);
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
	public UpdateBoardView(String writer, String title, String content, int boardNum) {
		initialize(writer, title, content, boardNum);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String writer, String title, String content, int boardNum) {
		frame = new JFrame();
		frame.setBounds(100, 100, 678, 580);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// boardVO 객체 생성 및 초기화
		vo = new boardVO();
		vo.setName(writer);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNum(boardNum);

		TitleField = new JTextField();
		TitleField.setBounds(103, 62, 401, 21);
		frame.getContentPane().add(TitleField);
		TitleField.setText(vo.getTitle());
		TitleField.setColumns(10);

		ContentField = new JTextField();
		ContentField.setBounds(103, 103, 401, 305);
		frame.getContentPane().add(ContentField);
		ContentField.setText(vo.getContent());
		ContentField.setColumns(10);

		UpdateBtn = new JButton("수정하기");
		UpdateBtn.setBounds(499, 465, 97, 23);
		UpdateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newTitle = TitleField.getText();
				String newContent = ContentField.getText();

				BoardDAO dao = new BoardDAOImpl();
				boardVO vo = new boardVO();
				vo.setNum(boardNum);
				vo.setTitle(newTitle);
				vo.setContent(newContent);
				dao.update(vo);
				frame.dispose();

				ShowPost showPost = new ShowPost(writer, newTitle, newContent, boardNum);
				showPost.showWindow();
				frame.dispose();
				BoardListView boardlist = new BoardListView();
				boardlist.showWindow();
				
			}
		});
		frame.getContentPane().add(UpdateBtn);

		WriterLabel = new JLabel("New label");
		WriterLabel.setBounds(551, 23, 57, 15);
		WriterLabel.setText(vo.getName());
		frame.getContentPane().add(WriterLabel);
	}

	public void showWindow() {
		frame.setVisible(true);
	}

}
