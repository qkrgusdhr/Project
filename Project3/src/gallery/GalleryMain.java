package gallery;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
	private JPanel panel_1;
	private JButton btn[] = null;
	private JButton nib;

	public GalleryMain() {
		initialize();
	}

	private void initialize() {
		frmGallery = new JFrame();
		frmGallery.setResizable(false);
		frmGallery.setTitle("Gallery");
		frmGallery.setSize(1000, 600);
		frmGallery.getContentPane().setLayout(null);
		GalleryDAO gdao = new GalleryDAO();
		GalleryVO gvo = new GalleryVO();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimension2 = frmGallery.getSize();
		int x = (dimension.width - dimension2.width) / 2;
		int y = (dimension.height - dimension2.height) / 2;
		frmGallery.setLocation(x, y);

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

		st = new JTextField();
		st.setBounds(125, 64, 331, 21);
		frmGallery.getContentPane().add(st);
		st.setColumns(10);
		JButton btnNewButton = new JButton("searchB");
		btnNewButton.setBounds(468, 64, 86, 21);
		frmGallery.getContentPane().add(btnNewButton);
		JButton inpimageB = new JButton("+"); // 사진 추가 버튼 및 메소드
		inpimageB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadImage();
			}
		});
		inpimageB.setBackground(new Color(255, 255, 255));
		inpimageB.setPreferredSize(new Dimension(200, 200));
		panel_1.add(inpimageB);

		btn = new JButton[gdao.getCount()]; // 불러올 이미지 변경
		for (int i = 0; i < gdao.getCount(); i++) {
			btn[i] = new JButton();
			panel_1.add(btn[i]);
			int bn = i;
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
						clickImage(icon, bn);
					}
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		frmGallery.setVisible(true);
	}

	private void uploadImage() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "JPG", "JPEG", "PNG");
		chooser.setFileFilter(filter);
		chooser.setMultiSelectionEnabled(false); // 다중선택 불가
		try {
			GalleryDAO gdao = new GalleryDAO();
			GalleryVO gvo = new GalleryVO();
			chooser.showOpenDialog(null);
			gvo.setImagename(chooser.getSelectedFile().getName());
			gvo.setIpath(chooser.getSelectedFile() + "");
			gvo.setIno(gdao.getmaxIno());
			gdao.insertImage(gvo);

			nib = new JButton(); // 추가 된 이미지 화면에 표시
			panel_1.add(nib);
			ImageIcon icon = new ImageIcon(chooser.getSelectedFile() + "");
			Image img = icon.getImage();
			Image nimg = img.getScaledInstance(210, 210, 0);
			ImageIcon nicon = new ImageIcon(nimg);
			nib.setIcon(nicon);
			nib.setBackground(new Color(255, 255, 255));
			nib.setPreferredSize(new Dimension(200, 200));
			panel_1.invalidate();
			int bn = gdao.getCount() - 1;
			nib.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("NEW BUTTON NUMBER : " + bn);
					clickImage(icon, bn);
					System.out.println(" nib clicked!!!");

				}
			});
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void clickImage(ImageIcon icon, int bn) {
		JFrame cf = new JFrame();
		cf.setVisible(true);
		cf.setSize(icon.getIconWidth(), icon.getIconHeight());
		cf.setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimension2 = cf.getSize();
		int x = (dimension.width - dimension2.width) / 2;
		int y = (dimension.height - dimension2.height) / 2;
		// 너무 작은 사진이 있을시 버튼이 가려 프레임 크기 최소값 설정.
		if (dimension2.width < 150 || dimension2.height < 150) {
			cf.setSize(200, 200);
		}

		System.out.println("Button Number " + bn + " Clicked!!");

		cf.setLocation(x, y);
		JPanel np = new JPanel();
		cf.getContentPane().add(np, BorderLayout.CENTER);
		np.setLayout(new BorderLayout(5, 5));

		JLabel popupi = new JLabel("");
		np.add(popupi, BorderLayout.CENTER);

		JPanel menu = new JPanel();
		popupi.setIcon(icon);
		np.add(menu, BorderLayout.SOUTH);

		JButton editb = new JButton("Edit");
		menu.add(editb, BorderLayout.SOUTH);

		JButton deleteb = new JButton("Delete Photo");
		menu.add(deleteb, BorderLayout.SOUTH);

		deleteb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setResizable(false);
				frame.setBackground(new Color(255, 255, 255));
				frame.setBounds(100, 100, 250, 180);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
				Dimension dimension2 = frame.getSize();
				int x = (dimension.width - dimension2.width) / 2;
				int y = (dimension.height - dimension2.height) / 2;
				frame.setLocation(x, y);

				JPanel panel = new JPanel();
				panel.setBackground(new Color(255, 255, 255));
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(null);

				JLabel lblNewLabel = new JLabel("이미지를 삭제하시겠습니까?");
				lblNewLabel.setBounds(36, 23, 201, 52);
				panel.add(lblNewLabel);

				JButton btnrd = new JButton("삭제");
				btnrd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnrd.setBounds(36, 85, 65, 23);
				panel.add(btnrd);

				btnrd.addActionListener(new ActionListener() {			//2024.05.03 수정 필요.
					public void actionPerformed(ActionEvent e) {		//out of bounds 해결 필요.
						GalleryDAO gdao = new GalleryDAO();
						int point = gdao.getCount()-1;
						System.out.println(point);
						if (bn >= point) {							// 조건문 수정. 
							System.out.println("Try delete New Button");
							nib.setVisible(false);
							panel_1.remove(nib);
						} else {
							btn[bn].setVisible(false);
							panel_1.remove(btn[bn]);

						}
						panel_1.invalidate();
						frame.dispose();
						cf.dispose();
						gdao.deleteImage(gdao.getDbino()[bn]);	
						point--;
						
					}
				});

				JButton btnNewButton_1 = new JButton("취소");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnNewButton_1.setBounds(128, 85, 65, 23);
				panel.add(btnNewButton_1);
			}
		});
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