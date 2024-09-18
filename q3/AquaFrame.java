package q3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class AquaFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JMenu x, y, w;
	JMenuItem x1, y1, w1, w2, w3;
	JMenuBar z;

	AquaPanel Aquarium;

	JLabel pic;
	public static int image_flag;
	private File image_file;
	public static String image_file_path;

	/**
	 * builder of the frame with all the required conditions
	 * 
	 * @param string
	 */
	public AquaFrame(String string) {
		super(string);
		/**
		 * menu button on top right of the window things like File/Exit/Background..
		 * 
		 */
		x = new JMenu("File");
		w = new JMenu("Background");
		y = new JMenu("Help");

		x1 = new JMenuItem("Exit");
		x1.addActionListener(this);

		y1 = new JMenuItem("Help");
		y1.addActionListener(this);

		w1 = new JMenuItem("Image");
		w1.addActionListener(this);
		w2 = new JMenuItem("Blue");
		w2.addActionListener(this);
		w3 = new JMenuItem("None");
		w3.addActionListener(this);

		x.add(x1); // add every menu button/item that we create to to the current Jmenu that fit
					// for him

		w.add(w1);
		w.add(w2);
		w.add(w3);

		y.add(y1);

		z = new JMenuBar(); // add everything to a menu bar that will show on top of the window
		z.add(x);
		z.add(w);
		z.add(y);
		setJMenuBar(z);

		Aquarium = new AquaPanel(this); // build the aqua panel that will get the animals on it

		add(Aquarium, BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent e) {
		/**
		 * Exit if the user select file->Exit we stop all threads in active on the
		 * program and clear the aquarium then exit from the program
		 */
		if (e.getSource().equals(x1)) {
			for (Swimmable swimm : Aquarium.animals) {
				swimm.interrupt();
			}
			Aquarium.animals.clear();
			AquaPanel.reset_aqua = true;

			System.exit(0);
		}
		/**
		 * Help if the user select Help then a message dialog will open and write Home
		 * Work 2 \n GUI @ Threads
		 */
		if (e.getSource().equals(y1))
			JOptionPane.showMessageDialog(this, "Home Work 2 \n GUI @ Threads");
		/**
		 * image to the background of the aquarium you can select any image you want
		 * that exist on your computer
		 */
		if (e.getSource().equals(w1)) {
			FileDialog image_explorer = new FileDialog(new Frame(), "Choose Image Backround - Aquarium:",
					FileDialog.LOAD);
			image_explorer.setFile("*.jpg");
			image_explorer.setVisible(true);
			if (image_explorer.getFile() != null) {
				image_file = new File(image_explorer.getDirectory(), image_explorer.getFile());
				image_file_path = image_file.toPath().toString();
				image_flag = 1;
			}

		}
		/**
		 * put a blue color on the background of the aquarium
		 */
		if (e.getSource().equals(w2)) {
			Aquarium.setBackground(Color.BLUE);
			image_flag = 0;
		}
		/**
		 * put white color on the background of the aquarium
		 */
		if (e.getSource().equals(w3)) {
			Aquarium.setBackground(Color.WHITE);
			image_flag = 0;
		}
	}

	public static void main(String[] args) {
		AquaFrame frame1 = new AquaFrame("my Aquarium");
		frame1.pack();
		frame1.setSize(1200, 800);
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}
}
