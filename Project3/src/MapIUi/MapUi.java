package MapIUi;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MapUi extends JFrame {
	private JTextField addressTextField;
	private JButton searchButton;
	private JLabel imageLabel;
	private JLabel textLabel;

	public MapUi() {
		setTitle("Map App");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addressTextField = new JTextField(20);
		addressTextField.setText("위치를 입력하세요.");
		searchButton = new JButton("Search");

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		panel.setBackground(new Color(251, 244, 216));
		textLabel = new JLabel("[DB-DogName]와 동물병원 방문하기");
		textLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		panel.add(textLabel);
		
				imageLabel = new JLabel();
				imageLabel.setPreferredSize(new Dimension(350, 200)); 
				loadImage(imageLabel, "OIG2.jpg", 350, 200); //
				panel.add(imageLabel);

		panel.add(addressTextField); // 텍스트 필드 추가
		
		panel.add(searchButton); // 검색 버튼 추가

		getContentPane().add(panel);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String address = addressTextField.getText().trim();
					String searchQuery = address + "%20동물병원";
					String searchURL = "https://map.kakao.com/?q=" + searchQuery;

					openURL(searchURL);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	// 이미지를 로드하여 JLabel에 추가하고 크기에 맞게 스케일링하는 메서드
	private void loadImage(JLabel label, String imagePath, int width, int height) {
		try {
			URL imageURL = getClass().getResource(imagePath);
			if (imageURL != null) {
				ImageIcon originalIcon = new ImageIcon(imageURL); 
				Image originalImage = originalIcon.getImage(); 
				Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH); 
				ImageIcon scaledIcon = new ImageIcon(scaledImage); 
				label.setIcon(scaledIcon); 
			} else {
				System.err.println("Image not found: " + imagePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void openURL(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MapUi().setVisible(true);
			}
		});
	}
}
