package com.boardlist.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.InsertBoard.View.BoardInsertView;
import com.board.control.*;
import com.main.view.MainView;
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
		InsertBtn.setIcon(new ImageIcon(BoardListView.class.getResource("/img/baidu-line.png")));
		InsertBtn.setBackground(new Color(192, 192, 192));
		InsertBtn.setBounds(804, 727, 142, 34);
		InsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardInsertView view = new BoardInsertView(userID, null, null);
				view.showWindow();
				closeWindow();

			}
		});
		frame.getContentPane().add(InsertBtn);

		JLabel lblNewLabel = new JLabel("검색조건");
		lblNewLabel.setBounds(435, 90, 73, 31);
		frame.getContentPane().add(lblNewLabel);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "title", "content", "writer" }));
		comboBox.setBounds(520, 90, 104, 31);
		frame.getContentPane().add(comboBox);

		Searching = new JTextField();
		Searching.setBounds(636, 90, 209, 31);
		frame.getContentPane().add(Searching);
		Searching.setColumns(10);

		JButton btnNewButton = new JButton("검색");
		btnNewButton.setBackground(new Color(192, 192, 192));
		
		btnNewButton.setIcon(new ImageIcon(BoardListView.class.getResource("/img/search-line.png")));
		btnNewButton.setBounds(858, 90, 88, 31);
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
		
		JButton BackMainBtn = new JButton("Main");
		BackMainBtn.setBackground(new Color(192, 192, 192));
		BackMainBtn.setIcon(new ImageIcon(BoardListView.class.getResource("/img/home-8-line.png")));
		BackMainBtn.setBounds(12, 728, 97, 33);
		BackMainBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainView view = new MainView(userID);
				view.ShowWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().add(BackMainBtn);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(BoardListView.class.getResource("/img/diary.jpg")));
		lblNewLabel_3.setBounds(53, 29, 151, 42);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(BoardListView.class.getResource("/img/dog2.jpg")));
		lblNewLabel_2.setBounds(12, 10, 49, 65);
		frame.getContentPane().add(lblNewLabel_2);

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
		table.getTableHeader().setBackground(Color.white);
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
		lblNewLabel_1.setBounds(12, 90, 179, 31);
		frame.getContentPane().add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		scrollPane.setBounds(12, 131, 934, 586);
		frame.getContentPane().add(scrollPane);

		JPanel paginationPanel = new JPanel();
		paginationPanel.setBackground(new Color(255, 255, 255));
		paginationPanel.setBounds(12, 771, 934, 43);
		frame.getContentPane().add(paginationPanel);

		populateTable(1, 10);
		JButton prevButton = new JButton("");
		prevButton.setBackground(new Color(255, 255, 255));
		prevButton.setIcon(new ImageIcon(BoardListView.class.getResource("/img/arrow-drop-left-line.png")));
		prevButton.setBorder(new EmptyBorder(0,0,0,0));
		paginationPanel.add(prevButton);

		currentPageLabel = new JLabel("Page: " + currentPage);
		currentPageLabel.setFont(new Font("굴림", Font.BOLD, 12));
		paginationPanel.add(currentPageLabel);
		prevButton.addActionListener(e -> {
			if (currentPage > 1) {
				currentPage--;
				currentPageLabel.setText("Page: " + currentPage);
				populateTable(currentPage, itemsPerPage);
			}
		});

		currentPageLabel.setText("Page: " + currentPage);
		nextButton = new JButton("");
		nextButton.setBackground(new Color(255, 255, 255));
		nextButton.setIcon(new ImageIcon(BoardListView.class.getResource("/img/arrow-drop-right-line.png")));
		paginationPanel.add(nextButton);
		nextButton.setBorder(new EmptyBorder(0,0,0,0));
		nextButton.addActionListener(e -> {
			currentPage++;
			currentPageLabel.setText("Page: " + currentPage);

			populateTable(currentPage, itemsPerPage);

		});
		frame.setBounds(100, 100, 974, 863);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
