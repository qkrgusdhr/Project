package com.forgot.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class ForgotPwd {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPwd window = new ForgotPwd();
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
	public ForgotPwd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 349, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dog Diary");
		lblNewLabel.setForeground(new Color(255, 182, 193));
		lblNewLabel.setFont(new Font("HY강M", Font.BOLD, 35));
		lblNewLabel.setBounds(89, 41, 164, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Manic-063\\Downloads\\urbanbrush-20190108131811238895__2_-removebg-preview.png"));
		lblNewLabel_1.setBounds(89, 82, 164, 135);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(89, 252, 164, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter your E-Mail");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(89, 227, 164, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("당신의 반려견 이름은?");
		lblNewLabel_2_1.setFont(new Font("굴림", Font.PLAIN, 10));
		lblNewLabel_2_1.setBounds(89, 304, 164, 15);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(89, 329, 164, 21);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Complete");
		btnNewButton.setBackground(new Color(221, 160, 221));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBounds(101, 404, 129, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
