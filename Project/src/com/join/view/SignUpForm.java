package com.join.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;

public class SignUpForm extends JFrame implements ActionListener {
    private JComboBox<Integer> yearCombo;
    private JComboBox<Integer> monthCombo;
    private JComboBox<Integer> dayCombo;

    public SignUpForm() {
        setTitle("회원가입");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("이름:");
        JTextField nameField = new JTextField();

        JLabel dobLabel = new JLabel("생년월일:");

        // 현재 날짜 정보 가져오기
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        // 년도 ComboBox 설정 (현재 년도부터 100년 전까지)
        yearCombo = new JComboBox<>();
        for (int year = currentYear; year >= currentYear - 100; year--) {
            yearCombo.addItem(year);
        }
        yearCombo.setSelectedItem(currentYear); // 현재 년도 선택

        // 월 ComboBox 설정 (1월부터 12월까지)
        monthCombo = new JComboBox<>();
        for (int month = 1; month <= 12; month++) {
            monthCombo.addItem(month);
        }
        monthCombo.setSelectedItem(currentMonth); // 현재 월 선택

        // 일 ComboBox 설정 (선택된 년도와 월에 따라 최대 일수 동적 설정)
        dayCombo = new JComboBox<>();
        updateDayComboBox(currentYear, currentMonth); // 현재 년도, 월에 맞는 일 목록 설정

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(dobLabel);
        mainPanel.add(yearCombo);
        mainPanel.add(new JLabel("년"));
        mainPanel.add(monthCombo);
        mainPanel.add(new JLabel("월"));
        mainPanel.add(dayCombo);
        mainPanel.add(new JLabel("일"));

        JButton signUpButton = new JButton("가입");
        signUpButton.addActionListener(this);

        mainPanel.add(signUpButton);

        add(mainPanel);
        setVisible(true);
    }

    private void updateDayComboBox(int year, int month) {
        dayCombo.removeAllItems(); // 기존 아이템 삭제

        // 선택된 년도, 월에 따라 최대 일수 계산
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int maxDays = yearMonthObject.lengthOfMonth();

        // 일 ComboBox 다시 채우기
        for (int day = 1; day <= maxDays; day++) {
            dayCombo.addItem(day);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("가입")) {
            String name = JOptionPane.showInputDialog(this, "이름을 입력하세요:");
            int year = (int) yearCombo.getSelectedItem();
            int month = (int) monthCombo.getSelectedItem();
            int day = (int) dayCombo.getSelectedItem();

            String dob = year + "년 " + month + "월 " + day + "일";
            JOptionPane.showMessageDialog(this, "가입 정보\n이름: " + name + "\n생년월일: " + dob);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignUpForm();
        });
    }
}
