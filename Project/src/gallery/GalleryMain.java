package gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GalleryMain {

	private JFrame frmGallery;
	private JTextField st;
	private GalleryDAO gdao = new GalleryDAO();
	private GalleryVO gvo = new GalleryVO();
	private JPanel panel_1;

	public GalleryMain() {
		initialize();
	}

	private void initialize() {
		frmGallery = new JFrame();
		frmGallery.setResizable(false);
		frmGallery.setTitle("Gallery");
		frmGallery.setBounds(new Rectangle(500, 300, 1000, 600));
		frmGallery.setDefaultCloseOperation(frmGallery.DISPOSE_ON_CLOSE);
		frmGallery.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 95, 117, 436);
		frmGallery.getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("menu");
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(125, 95, 847, 436);
		frmGallery.getContentPane().add(scrollPane);

		panel_1 = new JPanel();
		panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(0, 4, 5, 10));

		JButton inpimageB = new JButton("+"); // 사진 추가 버튼 및 메소드
		inpimageB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadImage();
			}
		});

		inpimageB.setBackground(new Color(255, 255, 255));
		inpimageB.setPreferredSize(new Dimension(200, 200));
		panel_1.add(inpimageB);

		JButton btn[] = new JButton[gdao.getmino()]; // 불러올 이미지 변경
		for (int i = 0; i < gdao.getmino(); i++) {
			btn[i] = new JButton();
			panel_1.add(btn[i]);

			try {
				ImageIcon icon = new ImageIcon(gdao.outputImage(gvo)[i]);
				Image img = icon.getImage();
				Image nimg = img.getScaledInstance(210, 210, 0);
				ImageIcon nicon = new ImageIcon(nimg);
				btn[i].setIcon(nicon);
				btn[i].setBackground(new Color(255, 255, 255));
				btn[i].setPreferredSize(new Dimension(200, 200));
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clickImage(icon);
					}
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		st = new JTextField();
		st.setBounds(125, 64, 331, 21);
		frmGallery.getContentPane().add(st);
		st.setColumns(10);

		JButton btnNewButton = new JButton("searchB");
		btnNewButton.setBounds(468, 64, 86, 21);
		frmGallery.getContentPane().add(btnNewButton);

	}

	private void uploadImage() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "JPG", "JPEG", "PNG");
		chooser.setFileFilter(filter);
		chooser.setMultiSelectionEnabled(false); // 다중선택 불가
		try {
			chooser.showOpenDialog(null);
			gvo.setImagename(chooser.getSelectedFile().getName());
			gvo.setIpath(chooser.getSelectedFile() + "");
			gvo.setIno(gdao.getmino());
			JButton nib = new JButton(); // 추가 된 이미지 화면에 표시
			panel_1.add(nib);
			ImageIcon icon = new ImageIcon(chooser.getSelectedFile() + "");
			Image img = icon.getImage();
			Image nimg = img.getScaledInstance(210, 210, 0);
			ImageIcon nicon = new ImageIcon(nimg);
			nib.setIcon(nicon);
			nib.setBackground(new Color(255, 255, 255));
			nib.setPreferredSize(new Dimension(200, 200));
			panel_1.invalidate();
			gdao.insertImage(gvo);
			nib.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clickImage(icon);
				}
			});
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void clickImage(ImageIcon icon) {
		JFrame cf = new JFrame();
		cf.setVisible(true);
		cf.setBounds(new Rectangle(400, 100, icon.getIconWidth(), icon.getIconHeight()));
		JPanel np = new JPanel();
		cf.getContentPane().add(np, BorderLayout.CENTER);
		np.setLayout(new BorderLayout(5, 5));
		JLabel popupi = new JLabel("");
		np.add(popupi, BorderLayout.CENTER);

		popupi.setIcon(icon);
		JButton deleteb = new JButton("Delete Photo");
		np.add(deleteb, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GalleryMain window = new GalleryMain();
					window.frmGallery.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}