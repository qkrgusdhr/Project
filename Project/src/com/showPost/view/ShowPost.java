package com.showPost.view;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.UpdateBoard.View.UpdateBoardView;
import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.boardVO;
import com.boardlist.View.BoardListView;


import javax.swing.BoxLayout;
import javax.swing.JButton;


import java.awt.Color;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class ShowPost {
	private JFrame frame;
	private int boardNum;
	private JTextField CommentField;
	private JPanel CommentForm;
	private JButton Submit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPost window = new ShowPost(null, null, null, 0);
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
	public ShowPost(String writer, String title, String content, int boardNum) {
		this.boardNum = boardNum;
		initialize(writer, title, content);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String writer, String title, String content) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1036, 823);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWriter = new JLabel("writer: " + writer);
		lblWriter.setBounds(123, 73, 200, 15);
		frame.getContentPane().add(lblWriter);

		JLabel lblTitle = new JLabel("Title: " + title);
		lblTitle.setBounds(123, 117, 716, 15);
		frame.getContentPane().add(lblTitle);

		JLabel lblContent = new JLabel("Content: " + content);
		lblContent.setBackground(new Color(255, 255, 255));
		lblContent.setBounds(123, 153, 716, 297);
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
		UpdateBtn.setBounds(642, 467, 97, 23);
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
		DeleteBtn.setBounds(742, 467, 97, 23);
		frame.getContentPane().add(DeleteBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(true);
		scrollPane.setBounds(151, 509, 857, 209);
		frame.getContentPane().add(scrollPane);

		CommentForm = new JPanel();
		CommentForm.setBackground(new Color(255, 255, 255));
		CommentForm.setLayout(new BoxLayout(CommentForm, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(CommentForm);

		CommentField = new JTextField();
		CommentField.setBounds(269, 728, 610, 21);
		frame.getContentPane().add(CommentField);
		CommentField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Submit.doClick(); // 엔터처리 
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		CommentField.setColumns(10);

		Submit = new JButton("등록");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String author = "name";
				String time = "2012-02-24";
				String CommentText = CommentField.getText();
				if (!CommentText.isEmpty()) {
					
					// 수정 버튼 생성
					JButton editButton = new JButton("수정");
					editButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// 수정 버튼을 눌렀을 때 실행되는 동작 구현
						}
					});

					// 삭제 버튼 생성
					JButton deleteButton = new JButton("삭제");
					deleteButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// 삭제 버튼을 눌렀을 때 실행되는 동작 구현
						}
					});

					// 작성자, 댓글 내용, 시간을 포함한 라벨 생성
					JPanel commentPanel = new JPanel();
					commentPanel.setBackground(Color.white);
					
					JLabel nameLabel = new JLabel(author);
					nameLabel.setPreferredSize(new Dimension(50, 20));
					JLabel commentLabel = new JLabel(CommentText);
					commentLabel.setPreferredSize(new Dimension(500, 20));
					commentLabel.setHorizontalAlignment(SwingConstants.CENTER); 
					JLabel timeLabel = new JLabel(time);
					timeLabel.setPreferredSize(new Dimension(80, 20));
					
					
					commentPanel.add(nameLabel);
					commentPanel.add(commentLabel);
					commentPanel.add(timeLabel);
					commentPanel.add(editButton);
					commentPanel.add(deleteButton);
					
					
					CommentForm.add(commentPanel);
					CommentField.setText(""); // 입력 필드 초기화
					frame.revalidate(); // 다시 그리기
					frame.repaint(); // 다시 그리기
				} else {
					JOptionPane.showMessageDialog(Submit, "댓글을 입력해주세요.");
				}
			}
		});
		Submit.setBounds(891, 727, 117, 23);
		frame.getContentPane().add(Submit);
	}

	public void showWindow() {
		frame.setVisible(true);
	}
}
