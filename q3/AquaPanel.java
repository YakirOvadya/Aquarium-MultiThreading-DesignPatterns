package q3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

import java.util.concurrent.CyclicBarrier;

/**
 * 
 * aqua panel that from him we can access to all the button we need in
 * the program like add animal
 * sleep,wake up,food ,info ,exit
 */
public class AquaPanel extends JPanel implements ActionListener, Observer {

	static final private long serialVersionUID = 1L;
	/**
	 * Variable declaration of all needed
	 */
	JButton addAnimal;
	private final static String BUTTON_TEXT1 = "Add Animal";

	JButton addPlant;
	private final static String BUTTON_TEXT8 = "Add Plant";

	JButton duplicate;
	private final static String BUTTON_TEXT9 = "Duplicate";

	JButton momento;
	private final static String BUTTON_TEXT11 = "Momento";
	JButton sleep;
	private final static String BUTTON_TEXT2 = "Sleep";
	JButton wakeUp;
	private final static String BUTTON_TEXT3 = "Wake Up";
	JButton reset;
	private final static String BUTTON_TEXT4 = "Reset";
	JButton food;
	private final static String BUTTON_TEXT5 = "Food";
	JButton info;
	private final static String BUTTON_TEXT6 = "Info";
	JButton exit;
	private final static String BUTTON_TEXT7 = "Exit";

	JPanel p1;
	AquaFrame base;
	private Dimension dm;

	public static int wd;
	public static int hi;

	public static Boolean reset_aqua;
	public static Boolean food_sign;

	JScrollPane scrollPane;
	private Boolean info_sign;

	private CyclicBarrier barrier;

	private int total_eat;

	public static HashSet<Swimmable> animals = new HashSet<Swimmable>();

	public static HashSet<Immobile> planets = new HashSet<Immobile>();

	// part3:

	private Singleton wormInstance = null;

	JButton Decorator;
	private final static String BUTTON_TEXT10 = "Decorator";

	/**
	 * aqua panel builder that get the aqua frame from main frame as "Base"
	 * 
	 * @param base
	 */
	public AquaPanel(AquaFrame base) {
		/**
		 * create all the button we required to create in the mission
		 */
		this.base = base;
		total_eat = 0;
		info_sign = false; // to know if info window is open or not defualt to not open.
		this.setBackground(Color.WHITE);
		addAnimal = new JButton(BUTTON_TEXT1);
		sleep = new JButton(BUTTON_TEXT2);
		wakeUp = new JButton(BUTTON_TEXT3);
		reset = new JButton(BUTTON_TEXT4);
		food = new JButton(BUTTON_TEXT5);
		info = new JButton(BUTTON_TEXT6);
		exit = new JButton(BUTTON_TEXT7);

		// part3:
		addPlant = new JButton(BUTTON_TEXT8);
		duplicate = new JButton(BUTTON_TEXT9);
		Decorator = new JButton(BUTTON_TEXT10);
		momento = new JButton(BUTTON_TEXT11);

		food_sign = false; // to know if some animal is eat the food and stop the others from eat.
		/**
		 * create panel that put all the button on a row in bottom screan and leave with
		 * screen that will get all the
		 * animals we added to the program
		 */
		p1 = new JPanel(new BorderLayout());
		p1.setLayout(new GridLayout(1, 11));

		p1.add(addAnimal);
		addAnimal.addActionListener(this);

		p1.add(addPlant);
		addPlant.addActionListener(this);

		p1.add(duplicate);
		duplicate.addActionListener(this);

		p1.add(Decorator);
		Decorator.addActionListener(this);

		p1.add(momento);
		momento.addActionListener(this);

		p1.add(sleep);
		sleep.addActionListener(this);

		p1.add(wakeUp);
		wakeUp.addActionListener(this);

		p1.add(reset);
		reset.addActionListener(this);

		p1.add(food);
		food.addActionListener(this);

		p1.add(info);
		info.addActionListener(this);

		p1.add(exit);
		exit.addActionListener(this);

		this.setLayout(new BorderLayout());
		add("South", p1);

	}

	public CyclicBarrier getBarrier() {
		return barrier;
	}

	public void actionPerformed(ActionEvent e) {
		/**
		 * Exit button on bottom screen that will stop all the threads and clear them
		 * and then Exit from the program
		 */
		if (e.getSource().equals(exit)) {
			for (Swimmable swimm : animals) {
				swimm.interrupt();
			}
			animals.clear();
			planets.clear();
			reset_aqua = true;
			System.exit(0);
		}
		/**
		 * add animal button that create the add animal dialog to add animal if there
		 * are more then 5 animals
		 * dialog message while show up on the screen said that the aquarium build for 5
		 * animals top.
		 */
		if (e.getSource().equals(addAnimal)) {
			if (animals.size() >= 5) {
				JOptionPane.showMessageDialog(this, "This Aquarium can contain maximum 5 swimmable animals.\n");
			} else {

				AddAnimalDialog ani = new AddAnimalDialog(this);
				ani.setVisible(true);
			}
		}

		if (e.getSource().equals(addPlant)) {
			if (planets.size() >= 5) {
				JOptionPane.showMessageDialog(this, "This Aquarium can contain maximum 5 plantes.\n");
			} else {

				AddPlantDialog pla = new AddPlantDialog(this);
				pla.setVisible(true);
			}
		}

		if (e.getSource().equals(duplicate)) {
			if (animals.size() > 0) {
				DuplicateAnimal dupA = new DuplicateAnimal(this, animals);
				dupA.setVisible(true);

			} else {
				if (animals.size() <= 0) {
					JOptionPane.showMessageDialog(this, "There are no animals for duplicate.\n");

				}

			}

		}

		if (e.getSource().equals(Decorator)) {

			JPanelDecorator decP = new JPanelDecorator(this, animals);
			decP.setVisible(true);
			decP.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}

		/**
		 * suspend all the threads when the user click on sleep button
		 */
		if (e.getSource().equals(sleep)) {

			for (Swimmable swimm : animals) {
				swimm.setSuspend();
			}
		}
		/**
		 * resume all the threads when the user click on wakeup button
		 */
		if (e.getSource().equals(wakeUp)) {

			for (Swimmable swimm : animals) {
				swimm.setResume();
			}
		}
		/**
		 * rest the aquarium to default (to clean one) stop all the threads and clear
		 * the screen
		 * then set reset flags to true and food sign to false like in default
		 */
		if (e.getSource().equals(reset)) {
			for (Swimmable swimm : animals) {
				swimm.interrupt();
			}
			animals.clear();
			planets.clear();
			reset_aqua = true;
			food_sign = false;
			this.total_eat = 0;

		}
		/**
		 * food button that if clicked by the user worm will show up on the aquarium and
		 * all the animals
		 * will try to eat the worm, set the barrier for every thread to make the fair
		 * competition to the food
		 * the size of the barrier build with the size of animals in the tank
		 */
		if (e.getSource().equals(food)) {
			if (getWormInstance() == null) {

				int bari_size = 0;
				for (Swimmable swimm : animals) {
					if (swimm.getMyState() instanceof Hungry) {
						bari_size++;
					}
				}

				if (bari_size > 0) {
					wormInstance = Singleton.getInstance();
					food_sign = true;

					barrier = new CyclicBarrier(bari_size);

					for (Swimmable swimm : animals) {
						if (swimm.getMyState() instanceof Hungry) {
							swimm.setBarrier(barrier);
						}

					}
				}
			}
		}
		/**
		 * info button that show in a table all the data on the animals in the aquarium
		 */
		if (e.getSource().equals(info)) {
			if (info_sign == false) {
				bulid_table();
				base.setLocationRelativeTo(null);
				info_sign = true;

			} else {

				info_sign = false;
				scrollPane.setVisible(false);
				base.setSize(1200, 800);
				base.setLocationRelativeTo(null);
			}

		}

	}

	/**
	 * set the background of the aquarium to blue
	 */
	public void blue_color() {
		this.setBackground(Color.BLUE);

	}

	/**
	 * paint the image of the background using Dimension to get the full function of
	 * the window expend or Shrink the window
	 * and the image will show up perfectly
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		dm = getSize();
		hi = dm.height;
		wd = dm.width;
		/**
		 * Draw the background image
		 */
		if (AquaFrame.image_flag == 1) {

			final ImageIcon icon = new ImageIcon(AquaFrame.image_file_path);
			Image img = icon.getImage();
			g.drawImage(img, 0, 0, wd, hi, this);

		}
		/**
		 * Draw the worm
		 */
		if (food_sign) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.RED);
			g2.drawArc(this.getWidth() / 2, this.getHeight() / 2 - 5, 10, 10, 30, 210);
			g2.drawArc(this.getWidth() / 2, this.getHeight() / 2 + 5, 10, 10, 180, 270);
			g2.setStroke(new BasicStroke(1));
		}
		/**
		 * draw animals by the functions of their class if fish or jellyfish
		 */
		for (Swimmable swimm : animals) {
			swimm.drawAnimal(g);

		}

		for (Immobile plani : planets) {
			plani.drawCreatrue(g);

		}
		repaint();
	}

	/**
	 * function that get "temp" from type "Swimmable" that add the animals the user
	 * add to the HashSet<Swimmable> animals
	 * and check if there is no more then 5 animals added
	 * 
	 * @param temp
	 */
	public void add_ani(Swimmable temp) {
		if (animals.size() < 5) {
			animals.add(temp);
			temp.start();
		} else {
			JOptionPane.showMessageDialog(this, "This Aquarium can contain maximum 5 swimmable animals.\n");
		}

	}

	/**
	 * @return total eat of all the animals in the tank
	 */
	public int getTotal_eat() {
		return total_eat;
	}

	/**
	 * set the total eat of all the animals in the tank
	 */
	public void setTotal_eat() {

		this.total_eat += 1;
		// System.out.println(this.total_eat);
	}

	/**
	 * build the table for info button that contain the animal type,size,horzional
	 * speed and vertical speed
	 * and the number of worm that he eat
	 */
	public void bulid_table() {

		base.setSize(750, 210);
		scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		String[] columns = { "Animal", "Color", "Size", "Hor.Speed", "Ver.Speed", "Eat Counter" };
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		JTable table = new JTable(tableModel);
		for (Swimmable s : animals) {

			String[] Row = new String[] { s.getAnimalName(), s.getColor(), String.valueOf(s.getSize()),
					String.valueOf(s.getHorSpeed()), String.valueOf(s.getVerSpeed()), String.valueOf(s.getEatCount()) };
			scrollPane.setViewportView(table);
			tableModel.addRow(Row);
		}

		String[] Row = new String[] { "Total", null, null, null, null, String.valueOf(getTotal_eat()) };
		scrollPane.setViewportView(table);
		tableModel.addRow(Row);

	}

	// part 3:

	public Singleton getWormInstance() {
		return wormInstance;
	}

	public void setWormInstance() {
		Singleton.set();
		wormInstance = null;
	}

	public void add_plant_p(Immobile temp) {
		if (planets.size() < 5) {
			planets.add(temp);

		} else {
			JOptionPane.showMessageDialog(this, "This Aquarium can contain maximum 5 Plants.\n");
		}
	}

	@Override
	public void update(Fish f) {
		JOptionPane.showMessageDialog(this,
				"Iam a " + f.getAnimalName() + " My color is " + f.getColor() + " and Iam Hungry.\n");

	}

}
