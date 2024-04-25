import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame{
    public LogIn(){
        JPanel panel = new JPanel();
        JTextField txtID = new JTextField(10);
        JLabel label = new JLabel("ID : ");
        JLabel pswrd = new JLabel("Password : ");
        JPasswordField txtPass = new JPasswordField(10);
        JButton logBtn = new JButton("Log In");


        panel.add(label);
        panel.add(txtID);
        panel.add(pswrd);
        panel.add(txtPass);
        panel.add(logBtn);

        logBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String id = "Dan";
                String pass = "1234";

                if(id.equals(txtID.getText()) && pass.equals(txtPass.getText())){
                    JOptionPane.showMessageDialog(null, "Login Successful");
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong ID or Password");
            }
            }
        })
        add(panel);

        setVisible(true);
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new LogIn();
    }
}
