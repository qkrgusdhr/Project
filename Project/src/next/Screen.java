package next;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.user.dao.LoginDAO;
import com.user.vo.LoginVO;

public class Screen {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen window = new Screen(null);
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
	public Screen(String id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(String id) {
		frame = new JFrame();
		frame.setBounds(100, 100, 665, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(341, 68, 116, 21);
		textField.setText(id);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	public void showWindow() {
		frame.setVisible(true);
	}
}
