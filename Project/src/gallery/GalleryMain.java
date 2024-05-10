package gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GalleryMain {

	private JFrame frmGallery;
	private JTextField st;
	private JPanel panel_1;
	private JButton btn[];
	private int sc;
	private ActionListener listener;

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
		
		gvo.setId("rictsu");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimension2 = frmGallery.getSize();
		int x = (dimension.width - dimension2.width) / 2;
		int y = (dimension.height - dimension2.height) / 2;
		frmGallery.setLocation(x, y);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 95, 960, 436);
		frmGallery.getContentPane().add(scrollPane);

		panel_1 = new JPanel();
		panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(0, 4, 5, 10));

		st = new JTextField();
		st.setBounds(443, 64, 331, 21);
		frmGallery.getContentPane().add(st);
		st.setColumns(10);

		JButton btnSearch = new JButton("searchB");
		btnSearch.setBounds(786, 64, 86, 21);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GalleryDAO gdao = new GalleryDAO();
				String search = st.getText();
				System.out.println(search);
				gvo.setId(search);
				if(gdao.outputImage(gvo) == null) {
					System.out.println("해당 유저가 없습니다.");
				}
				
				
			}
		});

		frmGallery.getContentPane().add(btnSearch);
		JButton inpimageB = new JButton("+"); // 사진 추가 버튼 및 메소드
		inpimageB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadImage();
			}
		});
		inpimageB.setBackground(new Color(255, 255, 255));
		inpimageB.setPreferredSize(new Dimension(200, 200));
		panel_1.add(inpimageB);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(875, 63, 97, 23);
		frmGallery.getContentPane().add(btnNewButton_2);
		btn = new JButton[gdao.getCount()]; // 불러올 이미지 변경
		sc = gdao.getmaxIno();
		System.out.println(sc + " = sc");
		for (int i = 0; i < gdao.getCount(); i++) {
			btn[i] = new JButton();
			panel_1.add(btn[i]);
			int bn = i;
			int ino = gdao.getDbino()[i];
			try {
				ImageIcon icon = new ImageIcon(gdao.outputImage(gvo)[i]);
				Image img = icon.getImage();
				Image nimg = img.getScaledInstance(235, 210, 0);
				ImageIcon nicon = new ImageIcon(nimg);
				btn[i].setIcon(nicon);
				btn[i].setBackground(new Color(255, 255, 255));
				btn[i].setPreferredSize(new Dimension(200, 200));
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clickImage(btn[bn], icon, ino, gdao.getFileName(ino));
						System.out.println("listener = " + listener);
					}
				});

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		frmGallery.setVisible(true);
		frmGallery.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
			if (gdao.getCount() == 0)
				gvo.setIno(0);
			else
				gvo.setIno(gdao.getmaxIno() + 1);
			gvo.setIpath(chooser.getSelectedFile() + "");
			gdao.insertImage(gvo);

			JButton nib = new JButton(); // 추가 된 이미지 화면에 표시
			panel_1.add(nib);
			ImageIcon icon = new ImageIcon(chooser.getSelectedFile() + "");
			Image img = icon.getImage();
			Image nimg = img.getScaledInstance(235, 210, 0);
			ImageIcon nicon = new ImageIcon(nimg);
			nib.setIcon(nicon);
			nib.setBackground(new Color(255, 255, 255));
			nib.setPreferredSize(new Dimension(200, 200));
			panel_1.invalidate();

			nib.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clickImage(nib, icon, gvo.getIno(), chooser.getSelectedFile().getName());
					System.out.println("uploadImage ActionListener");
					System.out.println();
				}
			});
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void clickImage(JButton c, ImageIcon icon, int ino, String name) {
		JFrame cf = new JFrame(name);

		cf.setVisible(true);
		if (icon.getIconWidth() > 900 || icon.getIconHeight() > 900) {
			Image img = icon.getImage();
			Image imp = img.getScaledInstance(900, 900, 0);
			icon = new ImageIcon(imp);
		}
		cf.setSize(icon.getIconWidth(), icon.getIconHeight());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimension2 = cf.getSize();
		if (dimension2.width < 150 || dimension2.height < 150) {
			cf.setSize(200, 200);
		}
		int x = (dimension.width - dimension2.width) / 2;
		int y = (dimension.height - dimension2.height) / 2;
		cf.setLocation(x, y);
		cf.setResizable(false);
		// 너무 작은 사진이 있을시 버튼이 가려 프레임 크기 최소값 설정.
		JPanel np = new JPanel();
		cf.getContentPane().add(np, BorderLayout.CENTER);
		np.setLayout(new BorderLayout(5, 5));

		JLabel popupi = new JLabel("");
		np.add(popupi, BorderLayout.CENTER);
		popupi.setIcon(icon);

		JPanel menu = new JPanel();
		np.add(menu, BorderLayout.SOUTH);

//		JButton editb = new JButton("사진 변경");
//		menu.add(editb, BorderLayout.SOUTH);

		JButton deleteb = new JButton("삭제");
		menu.add(deleteb, BorderLayout.SOUTH);

//		editb.addActionListener(new ActionListener() {	보류 ,, 추후 삭제 가능성.
//			int count = 0;
//
//			public void actionPerformed(ActionEvent e) {
//				JFileChooser chooser = new JFileChooser();
//				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "JPG", "JPEG", "PNG");
//				chooser.setFileFilter(filter);
//				chooser.setMultiSelectionEnabled(false);
//				try {
//					GalleryDAO gdao = new GalleryDAO();
//					GalleryVO gvo = new GalleryVO();
//					chooser.showOpenDialog(null);
//					gdao.deleteImage(ino);
//					gvo.setImagename(chooser.getSelectedFile().getName());
//					gvo.setIno(ino);
//					gvo.setIpath(chooser.getSelectedFile() + "");
//					gdao.insertImage(gvo);
//					ImageIcon icon = new ImageIcon(chooser.getSelectedFile() + "");
//					Image img = icon.getImage();
//					Image nimg = img.getScaledInstance(235, 210, 0);
//					ImageIcon nicon = new ImageIcon(nimg);
//					cf.setSize(icon.getIconWidth(), icon.getIconHeight());
//					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//					Dimension dimension2 = cf.getSize();
//					int x = (dimension.width - dimension2.width) / 2;
//					int y = (dimension.height - dimension2.height) / 2;
//					popupi.setIcon(icon);
//					cf.setLocation(x, y);
//					if (dimension2.width < 150 || dimension2.height < 150) {
//						cf.setSize(200, 200);
//					}
//					cf.setTitle(chooser.getSelectedFile().getName());
//					c.setIcon(nicon);
//					c.setBackground(new Color(255, 255, 255));
//					c.setPreferredSize(new Dimension(200, 200));
//					panel_1.invalidate();
//					c.invalidate();
//					c.addActionListener(listener = new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//							clickImage(c, nicon, ino, name);
//						}
//					});
//				} catch (NullPointerException e1) {
//					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
//				}
//			}
//		});

		deleteb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("경고");
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
				System.out.println("sc = " + sc + ", ino = " + ino);

				btnrd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GalleryDAO gdao = new GalleryDAO();
						if (ino == sc)
							sc = sc - 1;
						c.setVisible(false);
						panel_1.remove(c);
						gdao.deleteImage(ino);
						panel_1.invalidate();
						frame.dispose();
						cf.dispose();
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

	public void ShowWindow() {
		frmGallery.setVisible(true);
	}
}