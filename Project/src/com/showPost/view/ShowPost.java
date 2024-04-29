package com.showPost.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.UpdateBoard.View.UpdateBoardView;
import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.boardVO;
import com.boardlist.View.BoardListView;

import javax.swing.JButton;

public class ShowPost {
	private JFrame frame;
	private int boardNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPost window = new ShowPost(null, null, null,0);
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
    public ShowPost(String writer, String title, String content,int boardNum)
    {	this.boardNum = boardNum;
        initialize(writer, title, content);
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String writer, String title, String content) {
		frame = new JFrame();
		frame.setBounds(100, 100, 696, 626);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWriter = new JLabel("writer: " + writer);
		lblWriter.setBounds(123, 73, 200, 15);
		frame.getContentPane().add(lblWriter);

		JLabel lblTitle = new JLabel("Title: " + title);
		lblTitle.setBounds(123, 117, 341, 15);
		frame.getContentPane().add(lblTitle);

		JLabel lblContent = new JLabel("Content: " + content);
		lblContent.setBounds(123, 162, 341, 288);
		frame.getContentPane().add(lblContent);

		JButton UpdateBtn = new JButton("수정");
		UpdateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				UpdateBoardView view = new UpdateBoardView(writer, title, content, boardNum);
				view.showWindow();
				frame.dispose();
			}
		});
		UpdateBtn.setBounds(425, 490, 97, 23);
		frame.getContentPane().add(UpdateBtn);
		
		JButton DeleteBtn = new JButton("삭제");
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardDAO dao = new BoardDAOImpl();
				boardVO vo = new boardVO();
				vo.setNum(boardNum);
				dao.delete(vo);
				frame.dispose();
				BoardListView view = new BoardListView();
				view.showWindow();
			}
		});
		DeleteBtn.setBounds(534, 490, 97, 23);
		frame.getContentPane().add(DeleteBtn);

		
	}

	public void showWindow() {
		frame.setVisible(true);
	}
}
