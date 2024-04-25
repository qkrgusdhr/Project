package com.login.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.user.dao.UserDAO;
import com.user.vo.UserVO;

public class LoginMain {

    private JFrame frmEd;
    private JTextField textId;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginMain window = new LoginMain();
                    window.frmEd.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginMain() {
        initialize();
    }

    private void initialize() {
        frmEd = new JFrame();
        frmEd.setBackground(Color.WHITE);
        frmEd.getContentPane().setBackground(Color.WHITE);
        frmEd.setTitle("Login");
        frmEd.setBounds(100, 100, 364, 607);
        frmEd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmEd.getContentPane().setLayout(null);

        // ID 입력 필드
        textId = new JTextField();
        textId.setHorizontalAlignment(SwingConstants.LEFT);
        textId.setForeground(Color.BLACK); 
        textId.setToolTipText("ID");
        textId.setColumns(10);
        textId.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK)); // 하단 테두리만 생성
        textId.setBounds(111, 294, 162, 30);
        textId.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (textId.getText().isEmpty()) {
					textId.setText("Enter your ID");
					textId.setForeground(Color.gray);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				 if (textId.getText().equals("Enter your ID")) {
	                    textId.setText("");
	                    textId.setForeground(Color.black);
	                }
			}
		});
        frmEd.getContentPane().add(textId);

        // 비밀번호 입력 필드
        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        passwordField.setBounds(111, 325, 162, 30);
        frmEd.getContentPane().add(passwordField);

        // 로그인 버튼
        btnNewButton = new JButton("Login");
        btnNewButton.setForeground(Color.WHITE); // 텍스트 색상 설정
        btnNewButton.setBackground(Color.BLUE); // 배경색 설정
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 14)); // 폰트 설정
        btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0)); // 테두리 설정
        btnNewButton.setBounds(126, 407, 97, 23);
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = textId.getText();
				String pw = new String(passwordField.getPassword());
				
				UserDAO userDAO = new UserDAO();
				ArrayList<UserVO> res = userDAO.list(id, pw);
				if(id.isEmpty() || pw.isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디와 패스워드를 확인하세요");
				}else if(!res.isEmpty()){
					JOptionPane.showMessageDialog(btnNewButton, "login!!");
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "login fail");
				}
			}
		});
        frmEd.getContentPane().add(btnNewButton);

        // 이미지
        JLabel lblNewLabel = new JLabel(new ImageIcon(LoginMain.class.getResource("/img/dog.jpg")));
        lblNewLabel.setBounds(111, 108, 144, 130);
        frmEd.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(LoginMain.class.getResource("/img/login.png")));
        lblNewLabel_1.setBounds(81, 10, 174, 40);
        frmEd.getContentPane().add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("ID");
        lblNewLabel_2.setFont(new Font("돋움", Font.BOLD, 18));
        lblNewLabel_2.setBounds(62, 294, 29, 30);
        frmEd.getContentPane().add(lblNewLabel_2);
        
        lblNewLabel_3 = new JLabel("PW");
        lblNewLabel_3.setFont(new Font("돋움", Font.BOLD, 18));
        lblNewLabel_3.setBounds(62, 325, 29, 49);
        frmEd.getContentPane().add(lblNewLabel_3);
        
        JButton btnJoin = new JButton("CREATE ACCOUT");
        btnJoin.setForeground(Color.BLACK);
        btnJoin.setFont(new Font("Arial", Font.BOLD, 14));
        btnJoin.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnJoin.setBackground(Color.WHITE);
        btnJoin.setBounds(111, 441, 129, 23);
        frmEd.getContentPane().add(btnJoin);
    }
}