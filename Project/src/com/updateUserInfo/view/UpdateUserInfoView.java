package com.updateUserInfo.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.join.VO.JoinVo;
import com.join.dao.JoinDAO;
import com.main.view.MainView;
import javax.swing.SwingConstants;

public class UpdateUserInfoView {

	private JFrame frame;
	private JTextField dognameField;
	private JTextField txtpwd;
	private JTextField txtpwd2;
	private JTextField txtdogN;
	private JTextField txtdogS;
	private JTextField txtdogB;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JPasswordField passwordCheck;
	private JTextField txttel;
	private JLabel lblNewLabel_2;
	
	JoinDAO joinDAO = new JoinDAO();
	JoinVo joinVo = new JoinVo();
	private JTextField textField;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUserInfoView window = new UpdateUserInfoView(null);
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
	public UpdateUserInfoView(String UserID) {
		
		initialize(UserID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String UserID) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 660, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("회원정보수정");

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(
				new ImageIcon(UpdateUserInfoView.class.getResource("/img/joindog.jpg")));
		lblNewLabel.setBounds(351, 0, 293, 523);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("회원정보수정");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 39));
		lblNewLabel_1.setBounds(50, 34, 241, 70);
		frame.getContentPane().add(lblNewLabel_1);

		// 반려견 이름
		dognameField = new JTextField();
		dognameField.setBackground(new Color(255, 255, 255));
		dognameField.setColumns(10);
		dognameField.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		dognameField.setBounds(143, 294, 148, 21);
		frame.getContentPane().add(dognameField);

		// 성별 구분
		JRadioButton rd1 = new JRadioButton("남");
		rd1.setBackground(new Color(255, 255, 255));
		rd1.setFont(new Font("굴림", Font.PLAIN, 12));
		rd1.setBounds(146, 336, 37, 23);
		rd1.addActionListener(new ActionListener() {

	         @Override
	         public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
	            String man = "남";            
	            joinVo.setDogsex(man);
	         }
	         
	      });
		frame.getContentPane().add(rd1);

		JRadioButton rd2 = new JRadioButton("여");
		rd2.setBackground(new Color(255, 255, 255));
		rd2.setFont(new Font("굴림", Font.PLAIN, 12));
		rd2.setBounds(195, 336, 37, 23);
		rd2.addActionListener(new ActionListener() {

	         @Override
	         public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
	            String girl = "여";
	            joinVo.setDogsex(girl);
	         }
	         
	      });
		frame.getContentPane().add(rd2);

		ButtonGroup group = new ButtonGroup();
		group.add(rd1);
		group.add(rd2);

		// 날짜 수정
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

		for (int i = toyear; i >= toyear - 50; i--) {
			yeararray.add(String.valueOf(i));
		}
		JComboBox<String> yearBox = new JComboBox<String>(yeararray.toArray(new String[yeararray.size()]));
		yearBox.setBackground(new Color(255, 255, 255));
		yearBox.setFont(new Font("굴림", Font.PLAIN, 12));
		yearBox.setBounds(50, 396, 81, 23);
		frame.getContentPane().add(yearBox);

		for (int i = 1; i <= 12; i++) {
			montharray.add(addZeroString(i));
		}
		JComboBox<String> monthBox = new JComboBox<String>(montharray.toArray(new String[montharray.size()]));
		monthBox.setBackground(new Color(255, 255, 255));
		monthBox.setFont(new Font("굴림", Font.PLAIN, 12));
		monthBox.setBounds(143, 396, 40, 23);
		String mcom = tomonth >= 10 ? String.valueOf(tomonth) : "0" + tomonth;
		monthBox.setSelectedItem(mcom);
		frame.getContentPane().add(monthBox);

		for (int i = 1; i <= 31; i++) {
			dayarray.add(addZeroString(i));
		}
		JComboBox<String> dayBox = new JComboBox<String>(dayarray.toArray(new String[dayarray.size()]));
		dayBox.setBackground(new Color(255, 255, 255));
		dayBox.setFont(new Font("굴림", Font.PLAIN, 12));
		dayBox.setBounds(195, 396, 57, 23);
		String dcom = tomonth >= 10 ? String.valueOf(today) : "0" + today;
		monthBox.setSelectedItem(dcom);
		frame.getContentPane().add(dayBox);

		// 수정 완료 버튼
		JButton btnNewButton = new JButton("Complete");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(221, 160, 221));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setBounds(100, 440, 120, 23);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pwd = new String(passwordCheck.getPassword());
				String name = nameField.getText();
				String tel = txttel.getText();
				String dogname = dognameField.getText();
				String dogsex = joinVo.getDogsex();
				String yeared = (String) yearBox.getSelectedItem();
				String monthed = (String) monthBox.getSelectedItem();
				String dayed = (String) dayBox.getSelectedItem();
				String dogbirth = yeared + monthed + dayed;
				
				joinVo.setId(UserID);
				joinVo.setPwd(pwd);
				joinVo.setName(name);
				joinVo.setTel(tel);
				joinVo.setDogname(dogname);
				joinVo.setDogsex(dogsex);
				joinVo.setDogbirth(dogbirth);

				Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
				Matcher passMatcher = passPattern1.matcher(pwd);
				if (!passMatcher.find()) {
					JOptionPane.showInternalMessageDialog(null, "비밀번호는 영문+특수문자+숫자 8자 이상으로 구성되어야 합니다.", "비밀번호 오류", 1);
				} else {

					boolean res = joinDAO.changeData(joinVo);
					if (res == true) {
						JOptionPane.showMessageDialog(btnNewButton, "정보수정을 완료했습니다.");
						frame.dispose();
						MainView mainView = new MainView(UserID);
						mainView.ShowWindow();

					} else {
						JOptionPane.showMessageDialog(btnNewButton, "수정에 실패했습니다.");
					}
				}
			}
		});
		frame.getContentPane().add(btnNewButton);

		JLabel pwdInfo = new JLabel("(영문,기호,숫자 포함 8~20자 이내)");
		pwdInfo.setBackground(new Color(255, 255, 255));
		pwdInfo.setFont(new Font("굴림", Font.PLAIN, 10));
		pwdInfo.setBounds(143, 139, 165, 15);
		frame.getContentPane().add(pwdInfo);

		txtpwd = new JTextField();
		txtpwd.setBackground(new Color(255, 255, 255));
		txtpwd.setEditable(false);
		txtpwd.setText("새 비밀번호");
		txtpwd.setBounds(50, 108, 88, 21);
		frame.getContentPane().add(txtpwd);
		txtpwd.setColumns(10);
		txtpwd.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));

		txtpwd2 = new JTextField();
		txtpwd2.setBackground(new Color(255, 255, 255));
		txtpwd2.setEditable(false);
		txtpwd2.setText("비밀번호 확인");
		txtpwd2.setColumns(10);
		txtpwd2.setBounds(50, 164, 88, 21);
		frame.getContentPane().add(txtpwd2);
		txtpwd2.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));

		txtdogN = new JTextField();
		txtdogN.setBackground(new Color(255, 255, 255));
		txtdogN.setEditable(false);
		txtdogN.setText("반려견 이름");
		txtdogN.setColumns(10);
		txtdogN.setBounds(65, 294, 66, 21);
		frame.getContentPane().add(txtdogN);
		txtdogN.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));

		txtdogS = new JTextField();
		txtdogS.setBackground(new Color(255, 255, 255));
		txtdogS.setEditable(false);
		txtdogS.setText("반려견 성별");
		txtdogS.setColumns(10);
		txtdogS.setBounds(65, 337, 66, 21);
		frame.getContentPane().add(txtdogS);
		txtdogS.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));

		txtdogB = new JTextField();
		txtdogB.setBackground(new Color(255, 255, 255));
		txtdogB.setEditable(false);
		txtdogB.setText("반려견 출생일");
		txtdogB.setColumns(10);
		txtdogB.setBounds(124, 365, 88, 21);
		frame.getContentPane().add(txtdogB);
		txtdogB.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));

		// 이름 입력하는 칸
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBackground(Color.WHITE);
		nameField.setBounds(143, 208, 148, 21);
		nameField.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		frame.getContentPane().add(nameField);

		// 전화번호 입력하는 칸
		txttel = new JTextField();
		txttel.setColumns(10);
		txttel.setBackground(Color.WHITE);
		txttel.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		txttel.setBounds(143, 251, 148, 21);
		frame.getContentPane().add(txttel);

		JLabel txtname;
		txtname = new JLabel("이름");
		txtname.setFont(new Font("굴림", Font.PLAIN, 12));
		txtname.setBounds(100, 211, 31, 15);
		frame.getContentPane().add(txtname);

		// 비번 필드
		passwordField = new JPasswordField();
		passwordField.setBounds(143, 108, 148, 21);
		passwordField.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		frame.getContentPane().add(passwordField);

		// 비번 확인필드
		passwordCheck = new JPasswordField();
		passwordCheck.setBounds(143, 164, 148, 21);
		passwordCheck.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		frame.getContentPane().add(passwordCheck);
		passwordCheck.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				char[] passwordchar = passwordField.getPassword();
				String password = new String(passwordchar);
				char[] pwdcheckchar = passwordCheck.getPassword();
				String passwordcheck = new String(pwdcheckchar);

				if (password.equals(passwordcheck)) {
					lblNewLabel_2.setText("비밀번호가 일치합니다.");
				} else {
					lblNewLabel_2.setText("비밀번호가 일치하지 않습니다.");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(143, 184, 134, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("전화번호");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		textField.setBackground(Color.WHITE);
		textField.setBounds(65, 250, 66, 21);
		frame.getContentPane().add(textField);
		
		btnBack = new JButton("back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBack.setBackground(new Color(221, 160, 221));
		btnBack.setBounds(110, 473, 102, 23);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainView mainView = new MainView(UserID);
				mainView.ShowWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnBack);

	}

	private String addZeroString(int k) {
		String value = Integer.toString(k);
		if (value.length() == 1) {
			value = "0" + value;
		}
		return value;
	}
	
	public void ShowWindow() {
		frame.setVisible(true);
	}
}
