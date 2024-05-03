package com.userinfo.view;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.join.view.JoinView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class UserInfo {

	private JFrame frame;
	private JTextField pwdField;
	private JTextField pwdCheck;
	private JTextField dognameField;
	private JTextField txtpwd;
	private JTextField txtpwd2;
	private JTextField txtdogN;
	private JTextField txtdogS;
	private JTextField txtdogB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfo window = new UserInfo();
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
	public UserInfo() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 660, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("회원정보수정");
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Manic-063\\Documents\\카카오톡 받은 파일\\KakaoTalk_20240429_115613669.jpg"));
		lblNewLabel.setBounds(351, 0, 293, 523);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("회원정보수정");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 39));
		lblNewLabel_1.setBounds(50, 34, 241, 70);
		frame.getContentPane().add(lblNewLabel_1);
		
		pwdField = new JTextField();
		pwdField.setBackground(new Color(255, 255, 255));
		pwdField.setFont(new Font("굴림", Font.PLAIN, 12));
		pwdField.setBounds(143, 155, 148, 21);
		frame.getContentPane().add(pwdField);
		pwdField.setColumns(10);
		
		pwdCheck = new JTextField();
		pwdCheck.setBackground(new Color(255, 255, 255));
		pwdCheck.setColumns(10);
		pwdCheck.setBounds(143, 201, 148, 21);
		frame.getContentPane().add(pwdCheck);
		
		dognameField = new JTextField();
		dognameField.setBackground(new Color(255, 255, 255));
		dognameField.setColumns(10);
		dognameField.setBounds(143, 251, 148, 21);
		frame.getContentPane().add(dognameField);
		
		JRadioButton rd1 = new JRadioButton("남");
		rd1.setBackground(new Color(255, 255, 255));
		rd1.setFont(new Font("굴림", Font.PLAIN, 12));
		rd1.setBounds(150, 302, 37, 23);
		frame.getContentPane().add(rd1);
		
		JRadioButton rd2 = new JRadioButton("여");
		rd2.setBackground(new Color(255, 255, 255));
		rd2.setFont(new Font("굴림", Font.PLAIN, 12));
		rd2.setBounds(207, 302, 37, 23);
		frame.getContentPane().add(rd2);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rd1); group.add(rd2);
		
		
		
		
		
		ArrayList<String> yeararray;
		ArrayList<String> montharray;
		ArrayList<String> dayarray;
		
		Calendar oCalendar = Calendar.getInstance();
		
		int toyear = oCalendar.get(Calendar.YEAR);
		int tomonth = oCalendar.get(Calendar.MONTH) + 1;
		int today = oCalendar.get(Calendar.DAY_OF_MONTH);
		
		yeararray = new ArrayList<String>();
		montharray = new ArrayList<String>();
		dayarray = new ArrayList<String>();
		
		for(int i = toyear; i>=toyear-50; i--) {
			yeararray.add(String.valueOf(i));
		}
		JComboBox<String> yearBox = new JComboBox<String>(yeararray.toArray(new String[yeararray.size()]));
		yearBox.setBackground(new Color(255, 255, 255));
		yearBox.setFont(new Font("굴림", Font.PLAIN, 12));
		yearBox.setBounds(50, 384, 81, 23);
		frame.getContentPane().add(yearBox);
		
		for(int i = 1 ; i <= 12; i++) {
			montharray.add(addZeroString(i));
		}
		JComboBox<String> monthBox = new JComboBox<String>(montharray.toArray(new String[montharray.size()]));
		monthBox.setBackground(new Color(255, 255, 255));
		monthBox.setFont(new Font("굴림", Font.PLAIN, 12));
		monthBox.setBounds(150, 384, 40, 23);
		String mcom = tomonth >= 10?String.valueOf(tomonth):"0"+tomonth;
		monthBox.setSelectedItem(mcom);
		frame.getContentPane().add(monthBox);
		
		for(int i = 1; i <= 31; i++) {
			dayarray.add(addZeroString(i));
		}
		JComboBox<String> dayBox = new JComboBox<String>(dayarray.toArray(new String[dayarray.size()]));
		dayBox.setBackground(new Color(255, 255, 255));
		dayBox.setFont(new Font("굴림", Font.PLAIN, 12));
		dayBox.setBounds(202, 384, 57, 23);
		String dcom = tomonth >= 10?String.valueOf(today):"0"+today;
		monthBox.setSelectedItem(dcom);
		frame.getContentPane().add(dayBox);
		
	
		
		JButton btnNewButton = new JButton("Complete");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(221, 160, 221));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setBounds(100, 440, 120, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
		JLabel pwdInfo = new JLabel("(영문,기호,숫자 포함 8~20자 이내)");
		pwdInfo.setBackground(new Color(255, 255, 255));
		pwdInfo.setFont(new Font("굴림", Font.PLAIN, 10));
		pwdInfo.setBounds(143, 176, 165, 15);
		frame.getContentPane().add(pwdInfo);
		
		txtpwd = new JTextField();
		txtpwd.setBackground(new Color(255, 255, 255));
		txtpwd.setEditable(false);
		txtpwd.setText("새 비밀번호");
		txtpwd.setBounds(50, 155, 88, 21);
		frame.getContentPane().add(txtpwd);
		txtpwd.setColumns(10);
		txtpwd.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtpwd2 = new JTextField();
		txtpwd2.setBackground(new Color(255, 255, 255));
		txtpwd2.setEditable(false);
		txtpwd2.setText("비밀번호 확인");
		txtpwd2.setColumns(10);
		txtpwd2.setBounds(50, 201, 88, 21);
		frame.getContentPane().add(txtpwd2);
		txtpwd2.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtdogN = new JTextField();
		txtdogN.setBackground(new Color(255, 255, 255));
		txtdogN.setEditable(false);
		txtdogN.setText("반려견 이름");
		txtdogN.setColumns(10);
		txtdogN.setBounds(50, 251, 88, 21);
		frame.getContentPane().add(txtdogN);
		txtdogN.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtdogS = new JTextField();
		txtdogS.setBackground(new Color(255, 255, 255));
		txtdogS.setEditable(false);
		txtdogS.setText("반려견 성별");
		txtdogS.setColumns(10);
		txtdogS.setBounds(50, 303, 88, 21);
		frame.getContentPane().add(txtdogS);
		txtdogS.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtdogB = new JTextField();
		txtdogB.setBackground(new Color(255, 255, 255));
		txtdogB.setEditable(false);
		txtdogB.setText("반려견 출생일");
		txtdogB.setColumns(10);
		txtdogB.setBounds(50, 353, 88, 21);
		frame.getContentPane().add(txtdogB);
		txtdogB.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
	}
	private String addZeroString(int k) {
		String value = Integer.toString(k);
		if(value.length()==1) {
			value="0"+value;
		}
		return value;
	}
	    }


