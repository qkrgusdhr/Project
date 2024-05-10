package com.join.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.join.VO.JoinVo;
import com.join.dao.JoinDAO;
import com.login.view.LoginMain;

public class JoinView {

	private JFrame frame;
	private JTextField textID;
	private JButton btnCreate;
	private JPasswordField pwdCheck;
	private JTextField pwdinfo;
	private JTextField textname;
	private JTextField texttel;
	private JPasswordField pwd;
	private JTextField textdogname;
	private JTextField txtId;
	private JTextField txtPwd;
	private JTextField txtPwd2;
	private JTextField txtName;
	private JTextField txtTel;
	private JTextField txtDogName;
	private JTextField txtDogSex;
	private JTextField txtDogBirth;
	private JLabel lblNewLabel_1;
	private JTextField textField;

	
	JoinDAO joinDAO = new JoinDAO();
	JoinVo joinVo = new JoinVo();
	JComboBox<String> year;
	JComboBox<String> month;
	JComboBox<String> day;
	
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
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setResizable(false);
		frame.setTitle("회원가입");
		frame.setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 867, 559);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		layeredPane.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JoinView.class.getResource("/img/joindog.jpg")));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(560, 0, 291, 527);
		layeredPane.add(lblNewLabel);
		
		
		//ID 입력칸
		textID = new JTextField();
		textID.setBackground(new Color(255, 255, 255));
		textID.setToolTipText("ID");
		textID.setHorizontalAlignment(SwingConstants.LEFT);
		textID.setForeground(Color.BLACK);
		textID.setColumns(10);
		textID.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		textID.setBounds(202, 87, 162, 30);
		textID.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if (textID.getText().isEmpty()) {
						textID.setText("Enter your ID");
						textID.setForeground(Color.gray);
					}
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					 if (textID.getText().equals("Enter your ID")) {
						 textID.setText("");
						 textID.setForeground(Color.black);
		                }
				}
			});
		layeredPane.add(textID);
		
		//비번칸
		pwd = new JPasswordField();
		pwd.setBackground(new Color(255, 255, 255));
		pwd.setHorizontalAlignment(SwingConstants.LEFT);
		pwd.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		pwd.setBounds(202, 127, 162, 30);
		layeredPane.add(pwd);
		
		//비번 입력값 정보
		pwdinfo = new JTextField();
		pwdinfo.setEditable(false);
		pwdinfo.setBackground(new Color(255, 255, 255));
		pwdinfo.setFont(new Font("굴림", Font.PLAIN, 10));
		pwdinfo.setText("(영문,기호, 숫자 포함 8~20자 이내)");
		pwdinfo.setBounds(373, 132, 175, 30);
		layeredPane.add(pwdinfo);
		pwdinfo.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
		pwdinfo.setColumns(10);
		
		//비번 확인하는 칸
		pwdCheck = new JPasswordField();
		pwdCheck.setBackground(new Color(255, 255, 255));
		pwdCheck.setHorizontalAlignment(SwingConstants.LEFT);
		pwdCheck.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		pwdCheck.setBounds(202, 167, 162, 30);
		if(pwd.getPassword() == pwdCheck.getPassword()) {
			
		}
		layeredPane.add(pwdCheck);
		
		//이름칸
		textname = new JTextField();
		textname.setBackground(new Color(255, 255, 255));
		textname.setHorizontalAlignment(SwingConstants.LEFT);
		textname.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		textname.setBounds(202, 207, 162, 30);
		layeredPane.add(textname);
		
		//전화번호
		texttel = new JTextField();
		texttel.setBackground(new Color(255, 255, 255));
		texttel.setHorizontalAlignment(SwingConstants.LEFT);
		texttel.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		texttel.setBounds(202, 247, 162, 30);
		layeredPane.add(texttel);
		
		//강아지 이름		
		textdogname = new JTextField();
		textdogname.setBackground(new Color(255, 255, 255));
		textdogname.setHorizontalAlignment(SwingConstants.LEFT);
		textdogname.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		textdogname.setBounds(202, 287, 162, 30);
		layeredPane.add(textdogname);
		
		// 중복검사 버튼
		JButton btnNewButton = new JButton("중복검사");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 12));
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
		btnNewButton.setBounds(376, 90, 91, 23);
		layeredPane.add(btnNewButton);
		
		//회원가입 만들기 버튼
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
				String pwd = new String(pwdCheck.getPassword());
				String name = textname.getText();
				String tel = texttel.getText();
				String dogname = textdogname.getText();
				String dogsex = joinVo.getDogsex();
				String yeared = (String) year.getSelectedItem();
				String monthed = (String) month.getSelectedItem();
				String dayed = (String) day.getSelectedItem();
				String dogbirth = yeared + monthed + dayed;

				joinVo.setId(id);
				joinVo.setPwd(pwd);
				joinVo.setName(name);
				joinVo.setTel(tel);
				joinVo.setDogname(dogname);
				joinVo.setDogsex(dogsex);
				joinVo.setDogbirth(dogbirth);
				
//				System.out.println(dogbirth);
				
				boolean res = joinDAO.insertData(joinVo);
				if(res == true) {
					joinDAO.insertData(joinVo);
					JOptionPane.showMessageDialog(btnCreate, "회원가입에 성공했습니다.");
					frame.dispose();
					LoginMain view2 = new LoginMain();
					view2.showWindow();
				
				}else {
					JOptionPane.showMessageDialog(btnCreate, "회원가입 실패했습니다.");
				}
			}
		});
		layeredPane.add(btnCreate);
		
		txtId = new JTextField();
		txtId.setBackground(new Color(255, 255, 255));
		txtId.setEditable(false);
		txtId.setText("아이디");
		txtId.setBounds(152, 96, 43, 21);
		layeredPane.add(txtId);
		txtId.setColumns(10);
		txtId.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtPwd = new JTextField();
		txtPwd.setEditable(false);
		txtPwd.setBackground(new Color(255, 255, 255));
		txtPwd.setText("패스워드");
		txtPwd.setColumns(10);
		txtPwd.setBounds(135, 136, 60, 21);
		layeredPane.add(txtPwd);
		txtPwd.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtPwd2 = new JTextField();
		txtPwd2.setEditable(false);
		txtPwd2.setBackground(new Color(255, 255, 255));
		txtPwd2.setText("패스워드확인");
		txtPwd2.setColumns(10);
		txtPwd2.setBounds(116, 176, 79, 21);
		layeredPane.add(txtPwd2);
		txtPwd2.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBackground(new Color(255, 255, 255));
		txtName.setText("이름");
		txtName.setColumns(10);
		txtName.setBounds(161, 216, 34, 21);
		layeredPane.add(txtName);
		txtName.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtTel = new JTextField();
		txtTel.setEditable(false);
		txtTel.setBackground(new Color(255, 255, 255));
		txtTel.setText("연락처");
		txtTel.setColumns(10);
		txtTel.setBounds(152, 256, 43, 21);
		layeredPane.add(txtTel);
		txtTel.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtDogName = new JTextField();
		txtDogName.setEditable(false);
		txtDogName.setBackground(new Color(255, 255, 255));
		txtDogName.setText("반려견 이름");
		txtDogName.setColumns(10);
		txtDogName.setBounds(123, 296, 67, 21);
		layeredPane.add(txtDogName);
		txtDogName.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtDogSex = new JTextField();
		txtDogSex.setEditable(false);
		txtDogSex.setBackground(new Color(255, 255, 255));
		txtDogSex.setText("반려견 성별");
		txtDogSex.setColumns(10);
		txtDogSex.setBounds(128, 336, 67, 21);
		layeredPane.add(txtDogSex);
		txtDogSex.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtDogBirth = new JTextField();
		txtDogBirth.setEditable(false);
		txtDogBirth.setBackground(new Color(255, 255, 255));
		txtDogBirth.setText("반려견 출생일");
		txtDogBirth.setColumns(10);
		txtDogBirth.setBounds(116, 376, 79, 21);
		layeredPane.add(txtDogBirth);
		txtDogBirth.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		//라디오버튼 남
		JRadioButtonMenuItem rd1 = new JRadioButtonMenuItem("남");
		rd1.setBackground(new Color(255, 255, 255));
		rd1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		rd1.setBounds(202, 331, 60, 26);
		layeredPane.add(rd1);
		rd1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String man = "남";				
				joinVo.setDogsex(man);
			}
			
		});
		
		//라디오버튼 여
		JRadioButtonMenuItem rd2 = new JRadioButtonMenuItem("여");
		rd2.setBackground(new Color(255, 255, 255));
		rd2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		rd2.setBounds(274, 331, 60, 26);
		layeredPane.add(rd2);
		rd2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String girl = "여";
				joinVo.setDogsex(girl);
			}
			
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(rd1); group.add(rd2);


		 ArrayList<String> yeararray;
		 ArrayList<String> montharray;
		 ArrayList<String> dayarray;

		 Calendar oCalendar = Calendar.getInstance();
		 
		 int toyear = oCalendar.get(Calendar.YEAR);
		 int tomonth = oCalendar.get(Calendar.MONTH);
		 int today = oCalendar.get(Calendar.DAY_OF_MONTH);

		 yeararray = new ArrayList<String>();
		 montharray = new ArrayList<String>();
		 dayarray = new ArrayList<String>();
		 
		 for(int i = toyear; i >= toyear-50; i--) {
		 	yeararray.add(String.valueOf(i));
		 }
		 year = new JComboBox<String>(yeararray.toArray(new String[yeararray.size()]));
		 year.setBackground(new Color(255, 255, 255));
		 year.setFont(new Font("굴림", Font.PLAIN, 12));
		 year.setBounds(202, 375, 79, 23);
		 year.setSelectedItem(String.valueOf(toyear));
		 layeredPane.add(year);
		 
		 for(int i = 1; i <= 12; i++) {
			 montharray.add(addZeroString(i));
		 }
		 month = new JComboBox<String>(montharray.toArray(new String[montharray.size()]));
		 month.setBackground(new Color(255, 255, 255));
		 month.setFont(new Font("굴림", Font.PLAIN, 12));
		 month.setBounds(302, 375, 50, 23);
		 String mcom = tomonth >= 10?String.valueOf(tomonth):"0"+tomonth;
		 month.setSelectedItem(mcom);
		 layeredPane.add(month);
		 
		 for(int i =1; i <= 31; i++) {
			 dayarray.add(addZeroString(i));
		 }
		 day = new JComboBox<String>(dayarray.toArray(new String[dayarray.size()]));
		 day.setBackground(new Color(255, 255, 255));
		 day.setFont(new Font("굴림", Font.PLAIN, 12));
		 day.setBounds(364, 375, 50, 23);
		 String dcom = today >= 10?String.valueOf(today):"0"+today;
		 month.setSelectedItem(dcom);
		 layeredPane.add(day);
		 


	
		 lblNewLabel_1 = new JLabel("회원가입");
		 lblNewLabel_1.setBackground(new Color(255, 255, 255));
		 lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 39));
		 lblNewLabel_1.setBounds(202, 28, 162, 49);
		 layeredPane.add(lblNewLabel_1);
		 
		 textField = new JTextField();
		 textField.setText("패스워드를 입력해주세요");
		 textField.setFont(new Font("굴림", Font.PLAIN, 10));
		 textField.setEditable(false);
		 textField.setColumns(10);
		 textField.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
		 textField.setBackground(Color.WHITE);
		 textField.setBounds(376, 176, 147, 21);
		 layeredPane.add(textField);
		 
        
	}
	 private String addZeroString(int k) {
		 String value=Integer.toString(k);
		 if(value.length()==1) {
			 value="0"+value;
		 }
		 return value;
	 }
	 
	 public void showWindow() {
		frame.setVisible(true);
	}
}
