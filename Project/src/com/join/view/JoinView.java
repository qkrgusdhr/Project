package com.join.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.join.VO.JoinVo;
import com.join.dao.JoinDAO;

public class JoinView{

	private JFrame frame;
	private JTextField textID;
	private JButton btnCreate;
	private JPasswordField pwdCheck;
	private JTextField pwdinfo;
	private JPasswordField name;
	private JPasswordField tel;
	private JPasswordField pwd;
	private JPasswordField dogName;
	private JTextField txtId;
	private JTextField txtPwd;
	private JTextField txtPwd2;
	private JTextField txtName;
	private JTextField txtTel;
	private JTextField txtDogName;
	private JTextField txtDogSex;
	private JTextField txtDogBirth;
	


	
	
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
		
		
		//ID 입력칸
		textID = new JTextField();
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
		pwd.setHorizontalAlignment(SwingConstants.LEFT);
		pwd.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		pwd.setBounds(202, 127, 162, 30);
		layeredPane.add(pwd);
		
		//비번 입력값 정보
		pwdinfo = new JTextField();
		pwdinfo.setFont(new Font("굴림", Font.PLAIN, 10));
		pwdinfo.setText("(영문,숫자 포함 8~20자 이내)");
		pwdinfo.setBounds(376, 132, 147, 21);
		layeredPane.add(pwdinfo);
		pwdinfo.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
		pwdinfo.setColumns(10);
		
		//비번 확인하는 칸
		pwdCheck = new JPasswordField();
		pwdCheck.setHorizontalAlignment(SwingConstants.LEFT);
		pwdCheck.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		pwdCheck.setBounds(202, 167, 162, 30);
		layeredPane.add(pwdCheck);
		
		//이름칸
		name = new JPasswordField();
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		name.setBounds(202, 207, 162, 30);
		layeredPane.add(name);
		
		//전화번호
		tel = new JPasswordField();
		tel.setHorizontalAlignment(SwingConstants.LEFT);
		tel.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		tel.setBounds(202, 247, 162, 30);
		layeredPane.add(tel);
		
		//강아지 이름		
		dogName = new JPasswordField();
		dogName.setHorizontalAlignment(SwingConstants.LEFT);
		dogName.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		dogName.setBounds(202, 287, 162, 30);
		layeredPane.add(dogName);
		
		// 중복검사 버튼
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
				String pw = new String(pwdCheck.getPassword());
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
		
		txtId = new JTextField();
		txtId.setText("아이디");
		txtId.setBounds(152, 96, 43, 21);
		layeredPane.add(txtId);
		txtId.setColumns(10);
		txtId.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtPwd = new JTextField();
		txtPwd.setText("패스워드");
		txtPwd.setColumns(10);
		txtPwd.setBounds(135, 136, 60, 21);
		layeredPane.add(txtPwd);
		txtPwd.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtPwd2 = new JTextField();
		txtPwd2.setText("패스워드확인");
		txtPwd2.setColumns(10);
		txtPwd2.setBounds(116, 176, 79, 21);
		layeredPane.add(txtPwd2);
		txtPwd2.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtName = new JTextField();
		txtName.setText("이름");
		txtName.setColumns(10);
		txtName.setBounds(161, 216, 34, 21);
		layeredPane.add(txtName);
		txtName.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtTel = new JTextField();
		txtTel.setText("연락처");
		txtTel.setColumns(10);
		txtTel.setBounds(152, 256, 43, 21);
		layeredPane.add(txtTel);
		txtTel.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtDogName = new JTextField();
		txtDogName.setText("반려견 이름");
		txtDogName.setColumns(10);
		txtDogName.setBounds(123, 296, 67, 21);
		layeredPane.add(txtDogName);
		txtDogName.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtDogSex = new JTextField();
		txtDogSex.setText("반려견 성별");
		txtDogSex.setColumns(10);
		txtDogSex.setBounds(128, 336, 67, 21);
		layeredPane.add(txtDogSex);
		txtDogSex.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		txtDogBirth = new JTextField();
		txtDogBirth.setText("반려견 출생일");
		txtDogBirth.setColumns(10);
		txtDogBirth.setBounds(116, 376, 79, 21);
		layeredPane.add(txtDogBirth);
		txtDogBirth.setBorder(new MatteBorder(0, 0, 0, 0, Color.black));
		
		JRadioButtonMenuItem rd1 = new JRadioButtonMenuItem("남");
		rd1.setBounds(202, 331, 60, 26);
		layeredPane.add(rd1);
		
		JRadioButtonMenuItem rd2 = new JRadioButtonMenuItem("여");
		rd2.setBounds(274, 331, 60, 26);
		layeredPane.add(rd2);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rd1); group.add(rd2);
 
 JComboBox<Integer> comboBox = new JComboBox<Integer>();
 comboBox.setBounds(202, 375, 79, 23);
 layeredPane.add(comboBox);
 
 JComboBox comboBox_1 = new JComboBox();
 comboBox_1.setBounds(302, 375, 50, 23);
 layeredPane.add(comboBox_1);
 
 JComboBox comboBox_1_1 = new JComboBox();
 comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"26"}));

 layeredPane.add(comboBox_1_1);
 
 JComboBox comboBox_1_2 = new JComboBox();
 comboBox_1_2.setBounds(364, 375, 50, 23);
 layeredPane.add(comboBox_1_2);

        
	}
	
	public void showWindow() {
		frame.setVisible(true);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
