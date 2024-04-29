package com.InsertBoard.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.boardVO;
import com.boardlist.View.BoardListView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardInsertView {

	private JFrame frame;
	private JTextField TitleField;
	private JTextField WriterField;
	private JTextField ContentField;

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
	public BoardInsertView(String Writer, String title, String Content) {
		initialize(Writer, title, Content);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String Writer, String title, String Content) {
		frame = new JFrame();
		frame.setBounds(100, 100, 721, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton InsertBtn = new JButton("등록");
		InsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boardVO vo = new boardVO();
				BoardDAO dao = new BoardDAOImpl();
				String ntitle = TitleField.getText();
				String nwriter = WriterField.getText();
				String ncontent = ContentField.getText();
				
				
				vo.setName(nwriter);
				vo.setTitle(ntitle);
				vo.setContent(ncontent);
				dao.insert(vo);
				
				frame.dispose();
				BoardListView view = new BoardListView();
				view.showWindow();
			}
		});
		InsertBtn.setBounds(571, 453, 97, 23);
		frame.getContentPane().add(InsertBtn);
		
		TitleField = new JTextField();
		TitleField.setBounds(108, 70, 342, 31);
		frame.getContentPane().add(TitleField);
		TitleField.setColumns(10);
		
		WriterField = new JTextField();
		WriterField.setBounds(475, 70, 116, 31);
		frame.getContentPane().add(WriterField);
		WriterField.setColumns(10);
		
		ContentField = new JTextField();
		ContentField.setBounds(108, 111, 485, 332);
		frame.getContentPane().add(ContentField);
		ContentField.setColumns(10);
	}
	
	public void showWindow() {
		frame.setVisible(true);
	}
}
