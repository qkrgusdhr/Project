package com.boardlist.View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.InsertBoard.View.BoardInsertView;
import com.board.control.*;
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
	private JTextField Searching;
	private final String userID;
	private int LikeCnt;
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BoardListView window = new BoardListView(null);
				window.frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BoardListView(String id) {
		this.userID = id;
		
		initialize(userID);
		populateTable(1, 10);
		InsertBtn = new JButton("게시물 등록");
		InsertBtn.setBounds(822, 652, 124, 23);
		InsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardInsertView view = new BoardInsertView(userID, null, null);
				view.showWindow();
				closeWindow();

			}
		});
		frame.getContentPane().add(InsertBtn);

		JLabel lblNewLabel = new JLabel("검색조건");
		lblNewLabel.setBounds(436, 23, 73, 23);
		frame.getContentPane().add(lblNewLabel);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "title", "content", "writer" }));
		comboBox.setBounds(521, 23, 104, 23);
		frame.getContentPane().add(comboBox);

		Searching = new JTextField();
		Searching.setBounds(637, 24, 209, 22);
		frame.getContentPane().add(Searching);
		Searching.setColumns(10);

		JButton btnNewButton = new JButton("search");
		btnNewButton.setBounds(858, 23, 88, 23);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BoardDAO dao = new BoardDAOImpl();
				List<BoardVO> SearchResult = dao.search(String.valueOf(comboBox.getSelectedItem()),
						Searching.getText());
				if (!SearchResult.isEmpty()) {
					populateTableWithSearchResults(SearchResult);

				} else {
					JOptionPane.showMessageDialog(btnNewButton, "검색 결과가 없습니다.");
				}
			}
		});
		frame.getContentPane().add(btnNewButton);

	}

	private void initialize(String id) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		tableModel = new DefaultTableModel();

		tableModel.addColumn("No.");
		tableModel.addColumn("작성자");
		tableModel.addColumn("제목");
		tableModel.addColumn("시간");
		tableModel.addColumn("추천");

		table = new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(false);
		table.setDragEnabled(false);

		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
		}
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		table.setRowHeight(38);

		table.setDropMode(DropMode.USE_SELECTION);
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
						BoardVO vo = new BoardVO();

						vo.setNum(boardNum);
						dao.selectBoard(vo);
						String writer = vo.getName();
						String title = vo.getTitle();
						String content = vo.getContent();
						LikeCnt = vo.getLikeCnt();
						if (writer != null && title != null && content != null) { // 데이터가 있는지 확인
							// 데이터가 있다면 해당 정보를 이용하여 ShowPost 창을 열거나 처리합니다.
							ShowPost post = new ShowPost(userID, writer, title, content, boardNum, LikeCnt);
							post.showWindow();
							frame.dispose();
						} else {
							System.out.println("게시글을 찾을수 없습니다.");
						}
					}
				}
			}

		});
		
		

		JLabel lblNewLabel_1 = new JLabel("사용자 : " + userID);
		lblNewLabel_1.setBounds(112, 23, 162, 23);
		frame.getContentPane().add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(98, 56, 848, 586);
		frame.getContentPane().add(scrollPane);

		JPanel paginationPanel = new JPanel();
		paginationPanel.setBounds(98, 685, 848, 33);
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
		frame.setBounds(100, 100, 974, 767);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void populateTable(int currentPage, int itemsPerPage) {

		this.currentPage = currentPage;
		this.itemsPerPage = itemsPerPage;
		BoardDAO dao = new BoardDAOImpl();
		List<BoardVO> boardData = dao.select();
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

	private void populateTableWithSearchResults(List<BoardVO> searchResults) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (BoardVO vo : searchResults) {
			model.addRow(vo.toArray());
		}

		currentPage = 1;
		currentPageLabel.setText("Page : " + currentPage);
		nextButton.setEnabled(false);
	}

	public void showWindow() {
		frame.setVisible(true);
	}

	public void closeWindow() {
		frame.setVisible(false);
	}

	

}
