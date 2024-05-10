package com.login.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.join.VO.JoinVo;
import com.join.dao.JoinDAO;

public class PasswordSearchView {

   private JFrame frame;
   private JTextField IDtext;
   private JTextField DogNameText;
  
   JoinDAO joinDAO = new JoinDAO();
   JoinVo joinVo = new JoinVo();

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               PasswordSearchView window = new PasswordSearchView();
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
   public PasswordSearchView() {
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
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      
      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setIcon(new ImageIcon(PasswordSearchView.class.getResource("/img/diary.jpg")));
      lblNewLabel.setForeground(new Color(0, 0, 0));
      lblNewLabel.setFont(new Font("HY강M", Font.BOLD, 35));
      lblNewLabel.setBounds(89, 41, 164, 46);
      frame.getContentPane().add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("New label");
      lblNewLabel_1.setIcon(new ImageIcon(PasswordSearchView.class.getResource("/img/KakaoTalk_20240508_103653888.png")));
      lblNewLabel_1.setBounds(89, 82, 164, 135);
      frame.getContentPane().add(lblNewLabel_1);
      
      IDtext = new JTextField();
      IDtext.setBounds(89, 252, 164, 21);
      frame.getContentPane().add(IDtext);
      IDtext.setColumns(10);
      
      JLabel lblNewLabel_2 = new JLabel("Enter your ID");
      lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 10));
      lblNewLabel_2.setBounds(89, 227, 164, 15);
      frame.getContentPane().add(lblNewLabel_2);
      
      JLabel lblNewLabel_2_1 = new JLabel("당신의 반려견 이름은?");
      lblNewLabel_2_1.setFont(new Font("굴림", Font.PLAIN, 10));
      lblNewLabel_2_1.setBounds(89, 304, 164, 15);
      frame.getContentPane().add(lblNewLabel_2_1);
      
      DogNameText = new JTextField();
      DogNameText.setColumns(10);
      DogNameText.setBounds(89, 329, 164, 21);
      frame.getContentPane().add(DogNameText);
      
      JButton btnNewButton = new JButton("Complete");
      btnNewButton.setBackground(new Color(221, 160, 221));
      btnNewButton.setForeground(new Color(255, 255, 255));
      btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
      btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
      btnNewButton.setBounds(101, 404, 129, 23);
      btnNewButton.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String id = IDtext.getText();
            String DogName = DogNameText.getText();
            JoinVo vo = new JoinVo();
            vo.setId(id);
            vo.setDogname(DogName);
            JoinDAO dao = new JoinDAO();
            String pwd = dao.SearchPassword(vo);
            if(pwd != null) {
            	JOptionPane.showMessageDialog(btnNewButton, "비밀번호 : " + pwd);
            }else {
            	JOptionPane.showMessageDialog(btnNewButton, "ID 또는 강아지 이름을 확인해주세요.");
            }
         }
      });
      frame.getContentPane().add(btnNewButton);
   }

   public void showWindow() {
      // TODO Auto-generated method stub
      frame.setVisible(true);
         
   }

}