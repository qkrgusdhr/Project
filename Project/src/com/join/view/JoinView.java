package com.join.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;


import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import com.join.VO.JoinVo;
import com.join.dao.JoinDAO;


import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;

public class JoinView{

	private JFrame frame;
	private JTextField textID;
	private JButton btnCreate;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinView window = new JoinView();
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
	public JoinView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setTitle("회원가입");
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 867, 559);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		layeredPane.setBackground(Color.WHITE);
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JoinView.class.getResource("/img/joindog.jpg")));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(560, 0, 291, 527);
		layeredPane.add(lblNewLabel);
		
		textID = new JTextField();
		textID.setToolTipText("ID");
		textID.setHorizontalAlignment(SwingConstants.LEFT);
		textID.setForeground(Color.BLACK);
		textID.setColumns(10);
		textID.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		textID.setBounds(202, 135, 162, 30);
		layeredPane.add(textID);
		
		JButton btnNewButton = new JButton("중복검사");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textID.getText();
				JoinDAO joinDAO = new JoinDAO();
				if(joinDAO.isIdExist(id)) {
					JOptionPane.showMessageDialog(btnNewButton, "이미 사용중인 ID입니다.");
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "사용가능한 ID입니다.");
				}
			}
		});
		btnNewButton.setBounds(391, 138, 91, 23);
		layeredPane.add(btnNewButton);
		
		btnCreate = new JButton("CREATE");
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Arial", Font.BOLD, 14));
		btnCreate.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCreate.setBackground(new Color(221, 160, 221));
		btnCreate.setBounds(191, 449, 184, 23);
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = textID.getText();
				String pw = new String(passwordField.getPassword());
				JoinDAO joinDAO = new JoinDAO();
				JoinVo joinVo = new JoinVo();
				joinVo.setId(id);
				joinVo.setPw(pw);
				int res = joinDAO.insertData(joinVo);
				if(res == 1) {
					joinDAO.insertData(joinVo);
					JOptionPane.showMessageDialog(btnCreate, "회원가입 성공");
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(btnCreate, "회원가입 실패");
				}
			}
		});
		layeredPane.add(btnCreate);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		passwordField.setBounds(202, 208, 162, 30);
		layeredPane.add(passwordField);
		
	}
	
	public void showWindow() {
		frame.setVisible(true);
	}
}
