
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;

public class CalendarPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private YearMonth currentYearMonth;

    public CalendarPanel() {
        currentYearMonth = YearMonth.now();
        setLayout(new BorderLayout());
        add(createCalendarPanel(), BorderLayout.CENTER);
    }

    private JPanel createCalendarPanel() {
        JPanel calendarPanel = new JPanel(new GridLayout(0, 7));
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            calendarPanel.add(label);
        }

        int numberOfDaysInMonth = currentYearMonth.lengthOfMonth()-2;
        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int i = 1; i <= numberOfDaysInMonth; i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            calendarPanel.add(label);
        }

        return calendarPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendar Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CalendarPanel());
        frame.pack();
        frame.setVisible(true);
    }
}