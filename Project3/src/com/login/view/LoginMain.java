package com.login.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.join.view.JoinView;
import com.user.dao.LoginDAO;
import com.user.vo.LoginVO;

import next.Screen;

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
    public void showWindow() {
		frmEd.setVisible(true);
	}
    

    private void initialize() {
        frmEd = new JFrame();
        frmEd.setBackground(Color.WHITE);
        frmEd.getContentPane().setBackground(Color.WHITE);
        frmEd.setTitle("Login");
        frmEd.setBounds(100, 100, 364, 607);
        frmEd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmEd.getContentPane().setLayout(null);
        frmEd.setResizable(false); 

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
        passwordField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnNewButton.doClick();
			}
		}); // 엔터 처리
        passwordField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(passwordField.getPassword().length == 0) {
					passwordField.setText("123456");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(Arrays.equals(passwordField.getPassword(), "123456".toCharArray())) {
					passwordField.setText("");
				}
			}
		});
        frmEd.getContentPane().add(passwordField);
        

        // 로그인 버튼
        btnNewButton = new JButton("Login");
        btnNewButton.setForeground(Color.WHITE); // 텍스트 색상 설정
        btnNewButton.setBackground(new Color(221, 160, 221)); // 배경색 설정
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 14)); // 폰트 설정
        btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0)); // 테두리 설정
        btnNewButton.setBounds(111, 394, 107, 23);
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = textId.getText();
				String pw = new String(passwordField.getPassword());
				
				LoginDAO loginDAO  = new LoginDAO();
				ArrayList<LoginVO> res = loginDAO.list(id, pw);
				if(id.isEmpty() || pw.isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디와 패스워드를 확인하세요");
				}else if(!res.isEmpty()){
					JOptionPane.showMessageDialog(btnNewButton, "login!!");
					Screen screen = new Screen();
					frmEd.dispose();
					screen.showWindow();
					
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
        
        //회원가입
        JButton btnJoin = new JButton("CREATE ACCOUNT");
        btnJoin.setForeground(SystemColor.activeCaption);
        btnJoin.setFont(new Font("Arial", Font.PLAIN, 10));
        btnJoin.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnJoin.setBackground(Color.WHITE);
        btnJoin.setBounds(111, 440, 107, 13);
        btnJoin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JoinView view = new JoinView();
				view.showWindow();
			}
		});
        frmEd.getContentPane().add(btnJoin);
        
        JButton btnForgot = new JButton("Forgot password?");
        btnForgot.setForeground(SystemColor.activeCaption);
        btnForgot.setFont(new Font("Arial", Font.PLAIN, 10));
        btnForgot.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnForgot.setBackground(Color.WHITE);
        btnForgot.setBounds(111, 463, 107, 13);
        frmEd.getContentPane().add(btnForgot);
        btnForgot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JoinView view = new JoinView();
				view.showWindow();
			}
        	
        });
        }
}

