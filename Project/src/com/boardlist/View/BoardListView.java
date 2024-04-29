package com.boardlist.View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.InsertBoard.View.BoardInsertView;
import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.boardVO;
import com.showPost.view.ShowPost;

import java.util.List;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardListView {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private int currentPage = 1;
	private int itemsPerPage;
	private JLabel currentPageLabel;
	private JButton nextButton; 
	private JButton InsertBtn;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BoardListView window = new BoardListView();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BoardListView() {
		initialize();
		populateTable(1, 10);
		InsertBtn = new JButton("게시물 등록");
		InsertBtn.setBounds(573, 458, 124, 23);
		frame.getContentPane().add(InsertBtn);
		InsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardInsertView view = new BoardInsertView(null, null, null);
				view.showWindow();
				frame.dispose();
			}
		});
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		tableModel = new DefaultTableModel();
		
		
		
		tableModel.addColumn("No.");
		tableModel.addColumn("작성자");
		tableModel.addColumn("제목");
		tableModel.addColumn("시간");
		
		table = new JTable(tableModel);
		table.setShowGrid(false);
		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
		}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) { // 한 번 클릭 시
					int column = table.columnAtPoint(e.getPoint());
					int row = table.rowAtPoint(e.getPoint());
					if (column == 2) { // 제목 열 클릭 시
						String num = table.getValueAt(row, 0).toString();
						int boardNum = Integer.parseInt(num);
						// 해당 게시글로 이동
						System.out.println("클릭된 제목: " + boardNum);

						BoardDAO dao = new BoardDAOImpl();
						boardVO vo = new boardVO();
						vo.setNum(boardNum);
						dao.selectBoard(vo);
						String writer = vo.getName();
						String title = vo.getTitle();
						String content = vo.getContent();
						if (writer != null && title != null && content != null) { // 데이터가 있는지 확인
							// 데이터가 있다면 해당 정보를 이용하여 ShowPost 창을 열거나 처리합니다.
							ShowPost post = new ShowPost(writer, title, content, boardNum);
							post.showWindow();
							frame.dispose();
						} else {
							System.out.println("게시글을 찾을수 없습니다.");
						}
					}
				}
			}

		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(98, 56, 599, 401);
		frame.getContentPane().add(scrollPane);

		JPanel paginationPanel = new JPanel();
		paginationPanel.setBounds(98, 491, 652, 33);
		frame.getContentPane().add(paginationPanel);

		populateTable(1, 10);
		JButton prevButton = new JButton("Prev");
		paginationPanel.add(prevButton);

		currentPageLabel = new JLabel("Page: " + currentPage);
		paginationPanel.add(currentPageLabel);
		prevButton.addActionListener(e -> {
			if (currentPage > 1) {
				currentPage--;
				currentPageLabel.setText("Page: " + currentPage);
				populateTable(currentPage, itemsPerPage);
			}
		});

		currentPageLabel.setText("Page: " + currentPage);
		nextButton = new JButton("Next");
		paginationPanel.add(nextButton);
		nextButton.addActionListener(e -> {
			currentPage++;
			currentPageLabel.setText("Page: " + currentPage);
			
			
			populateTable(currentPage, itemsPerPage);

		});
		frame.setBounds(100, 100, 766, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void populateTable(int currentPage, int itemsPerPage) {

		this.currentPage = currentPage;
		this.itemsPerPage = itemsPerPage;
		BoardDAO dao = new BoardDAOImpl();
		List<boardVO> boardData = dao.select();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // 기존의 테이블 내용 삭제
		int startIndex = (currentPage - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, boardData.size());
		int totalItems = boardData.size();
		boolean isLastPage = endIndex >= totalItems;

		// 첫 번째부터 itemsPerPage번째 게시글까지만 테이블에 추가
		for (int i = startIndex; i < endIndex; i++) {
			if (boardData.get(i) != null) {
				model.addRow(boardData.get(i).toArray());
			}
		}
		if (nextButton != null) {
            nextButton.setEnabled(!isLastPage);
        }
	}

	public void showWindow() {
		frame.setVisible(true);
	}

}
