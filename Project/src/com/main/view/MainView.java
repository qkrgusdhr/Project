package com.main.view;

import java.awt.EventQueue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import com.board.control.BoardDAO;
import com.board.control.BoardDAOImpl;
import com.board.control.BoardVO;
import com.boardlist.View.BoardListView;
import com.login.view.LoginMain;
import com.showPost.view.ShowPost;
import com.updateUserInfo.view.UpdateUserInfoView;

import java.awt.Color;

import javax.swing.*;
import java.awt.Font;
import javax.swing.border.EmptyBorder;




public class MainView {
	private final String UserID;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private int LikeCnt;
	private int currentPage = 1;
	private int itemsPerPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView(null);
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
	public MainView(String id) {
		this.UserID = id;
		initialize(UserID);
		populateTable(1, 3);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String UserID) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1050, 856);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tableModel = new DefaultTableModel();
		
		
		tableModel.addColumn("No.");
		tableModel.addColumn("작성자");
		tableModel.addColumn("제목");
		tableModel.addColumn("시간");
		tableModel.addColumn("추천");
		
		table = new JTable(tableModel);
		

		DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(11, 85, 240, 722);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("게시판");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(MainView.class.getResource("/img/list-check.png")));
		lblNewLabel_1.setBounds(48, 53, 144, 59);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("경기천년제목 Medium", Font.PLAIN, 33));
		lblNewLabel_1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				BoardListView view = new BoardListView(UserID);
				view.showWindow();
				frame.dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("사용자 : " + UserID);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setBounds(654, 29, 164, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(MainView.class.getResource("/img/dog2.jpg")));
		lblNewLabel_2.setBounds(25, 10, 49, 65);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MainView.class.getResource("/img/diary.jpg")));
		lblNewLabel_3.setBounds(66, 29, 151, 42);
		frame.getContentPane().add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(263, 85, 759, 722);
		
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("인기 게시물");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setIcon(new ImageIcon(MainView.class.getResource("/img/thumb-up-fill.png")));
		lblNewLabel_1_1.setBounds(12, 10, 201, 59);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("경기천년제목 Medium", Font.PLAIN, 33));
		
		
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(false);
		table.setDragEnabled(false);
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
		table.setRowHeight(77);
		
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
							ShowPost post = new ShowPost(UserID, writer, title, content, boardNum, LikeCnt);
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
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		scrollPane.setBounds(12, 79, 724, 254);
		panel_1.add(scrollPane);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(255, 255, 255));
		panel_2_1.setBounds(12, 424, 349, 288);
		panel_1.add(panel_2_1);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(new Color(255, 255, 255));
		panel_2_1_1.setBounds(387, 424, 349, 288);
		panel_1.add(panel_2_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("정보수정");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_4.setBounds(830, 34, 78, 42);
		lblNewLabel_4.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UpdateUserInfoView infoView = new UpdateUserInfoView(UserID);
				infoView.ShowWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel LogOutBtn = new JLabel("로그아웃");
		LogOutBtn.setHorizontalAlignment(SwingConstants.CENTER);
		LogOutBtn.setFont(new Font("굴림", Font.BOLD, 14));
		LogOutBtn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				LoginMain loginMain = new LoginMain();
				loginMain.ShowWindow();
				frame.dispose();
			}
		});
		
		LogOutBtn.setBounds(920, 34, 78, 42);
		frame.getContentPane().add(LogOutBtn);
		
		
	}
	
	public void ShowWindow() {
		frame.setVisible(true);
	}
	
	public void populateTable(int currentPage, int itemsPerPage) {

		this.setCurrentPage(currentPage);
		this.setItemsPerPage(itemsPerPage);
		BoardDAO dao = new BoardDAOImpl();
		List<BoardVO> boardData = dao.LikeCntASC();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // 기존의 테이블 내용 삭제
		int startIndex = (currentPage - 1) * itemsPerPage;
		int endIndex = Math.min(startIndex + itemsPerPage, boardData.size());
		
		

		// 첫 번째부터 itemsPerPage번째 게시글까지만 테이블에 추가
		for (int i = startIndex; i < endIndex; i++) {
			if (boardData.get(i) != null) {
				model.addRow(boardData.get(i).toArray());
			}
		}
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
}
