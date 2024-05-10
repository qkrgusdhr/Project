package example;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarSwing extends JFrame {
    private JPanel calendarPanel;
    private JButton[] dayButtons;
    private JLabel monthLabel;
    private Calendar currentCalendar;

    public CalendarSwing() {
        setTitle("Schedule Calendar");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentCalendar = Calendar.getInstance();

        calendarPanel = new JPanel(new GridLayout(0, 7));
        add(calendarPanel, BorderLayout.CENTER);

        monthLabel = new JLabel("", JLabel.CENTER);
        updateCalendar();
        add(monthLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("<");
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentCalendar.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });
        buttonPanel.add(prevButton);

        JButton nextButton = new JButton(">");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentCalendar.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateCalendar() {
        calendarPanel.removeAll();
        dayButtons = new JButton[42];

        int year = currentCalendar.get(Calendar.YEAR);
        int month = currentCalendar.get(Calendar.MONTH);

        currentCalendar.set(year, month, 1);

        monthLabel.setText(new SimpleDateFormat("MMMM yyyy").format(currentCalendar.getTime()));

        int startDay = currentCalendar.get(Calendar.DAY_OF_WEEK);
        int maxDays = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < 42; i++) {
            if (i >= startDay - 1 && i < startDay - 1 + maxDays) {
                int day = i - startDay + 2;
                JButton button = new JButton(String.valueOf(day));
                button.addActionListener(new DayClickListener(day));
                calendarPanel.add(button);
                dayButtons[i] = button;
            } else {
                calendarPanel.add(new JLabel(""));
            }
        }

        revalidate();
        repaint();
    }

    private class DayClickListener implements ActionListener {
        private int day;

        public DayClickListener(int day) {
            this.day = day;
        }

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(CalendarSwing.this, "Clicked on day " + day);
            // 여기에 해당 날짜의 일정을 추가하는 기능을 구현할 수 있습니다.
        }
    }

    public static void main(String[] args) {
        new CalendarSwing();
    }
}