package com.boardlist.View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.boardVO;
import com.showPost.view.ShowPost;




import java.util.List;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardListView {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private int currentPage = 1;
	private int itemsPerPage;
	private JLabel currentPageLabel;

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
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("게시판번호");
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
						String title = table.getValueAt(row, column).toString();
						// 해당 게시글로 이동하는 로직을 작성 (예: 새로운 창을 열거나 화면을 전환하는 등)
						System.out.println("클릭된 제목: " + title);
						ShowPost post = new ShowPost();
						post.showWindow();
					}
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(98, 58, 599, 401);
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
		JButton nextButton = new JButton("Next");
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

		// 첫 번째부터 10번째 게시글까지만 테이블에 추가
		for (int i = startIndex; i < endIndex; i++) {
			model.addRow(boardData.get(i).toArray());
		}
		
		

	}
	
}
