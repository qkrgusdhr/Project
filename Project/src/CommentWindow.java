import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommentWindow {
    private JFrame frame;
    private JPanel commentPanel;
    private JTextField inputField;
    private JButton addButton;
    private JLabel lblNewLabel;
    private JButton btnNewButton;
    private JButton btnNewButton_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CommentWindow window = new CommentWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CommentWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 619, 522);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        commentPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(commentPanel);
        commentPanel.setLayout(null);
        
        lblNewLabel = new JLabel("sadsaddssadadsasdads");
        lblNewLabel.setBounds(43, 22, 140, 15);
        commentPanel.add(lblNewLabel);
        
        btnNewButton = new JButton("New button");
        btnNewButton.setBounds(456, 18, 76, 23);
        commentPanel.add(btnNewButton);
        
        btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setBounds(369, 18, 76, 23);
        commentPanel.add(btnNewButton_1);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        inputField = new JTextField();
        frame.getContentPane().add(inputField, BorderLayout.SOUTH);

        addButton = new JButton("추가");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComment(inputField.getText());
                inputField.setText("");
            }
        });
        frame.getContentPane().add(addButton, BorderLayout.EAST);
    }

    private void addComment(String comment) {
        JPanel commentItem = new JPanel(new BorderLayout());
        JLabel commentLabel = new JLabel(comment);
        JButton deleteButton = new JButton("삭제");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commentPanel.remove(commentItem);
                frame.revalidate();
                frame.repaint();
            }
        });

        commentItem.add(commentLabel, BorderLayout.CENTER);
        commentItem.add(deleteButton, BorderLayout.EAST);
        commentPanel.add(commentItem);
        frame.revalidate();
        frame.repaint();
    }
}
