package q3;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class AddAnimalDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	JComboBox<String> ColorBox;

	public static JSlider SizeSlider;
	JButton addbtn;
	public static JRadioButton Fishrdbutton; // fish
	public static JRadioButton Jellyfishrdbutton; // jellyfish
	public static JSlider Verticalspeedslider; // vertical Speed
	public static JSlider Horazionalspeedslider;

	AquaPanel panel;
	private JTextPane txtpnSelectFoodFreq;
	public static JSlider FoodFreqSlider;

	private static AnimalFactory AF;

	/**
	 * Add animal Dialog builder get panel P from type aqua panel
	 * build the dialog window with sliders to get the required
	 * speed/size/type/color
	 * and add the animals to the aqua panel
	 * 
	 * @param p
	 */

	public AddAnimalDialog(AquaPanel p) {
		setTitle("Dialog - Animal");

		panel = p;

		getContentPane().setLayout(null);
		/**
		 * Size Slider build and add to animal dialog
		 */

		SizeSlider = new JSlider();
		SizeSlider.setPaintLabels(true);
		SizeSlider.setMinorTickSpacing(10);
		SizeSlider.setMajorTickSpacing(20);
		SizeSlider.setValue(100);
		SizeSlider.setMinimum(20);
		SizeSlider.setMaximum(320);
		SizeSlider.setPaintTicks(true);
		SizeSlider.setBounds(0, 275, 484, 49);
		getContentPane().add(SizeSlider);
		/**
		 * Radio button to select animal type Fish
		 */
		Fishrdbutton = new JRadioButton("Fish");
		buttonGroup.add(Fishrdbutton);
		Fishrdbutton.setBounds(53, 33, 65, 23);
		getContentPane().add(Fishrdbutton);
		/**
		 * Radio button to select animal type JellyFish
		 */
		Jellyfishrdbutton = new JRadioButton("Jellyfish");
		buttonGroup.add(Jellyfishrdbutton);
		Jellyfishrdbutton.setBounds(123, 33, 75, 23);
		getContentPane().add(Jellyfishrdbutton);
		/**
		 * Slider to select the Vertical speed of the animal
		 */
		Verticalspeedslider = new JSlider();
		Verticalspeedslider.setValue(1);
		Verticalspeedslider.setPaintLabels(true);
		Verticalspeedslider.setMajorTickSpacing(1);
		Verticalspeedslider.setMinorTickSpacing(1);
		Verticalspeedslider.setMinimum(1);
		Verticalspeedslider.setMaximum(10);
		Verticalspeedslider.setPaintTicks(true);
		Verticalspeedslider.setBounds(20, 104, 209, 39);
		getContentPane().add(Verticalspeedslider);
		/**
		 * slider to select the horazional speed of the animal
		 */
		Horazionalspeedslider = new JSlider();
		Horazionalspeedslider.setValue(1);
		Horazionalspeedslider.setPaintTicks(true);
		Horazionalspeedslider.setPaintLabels(true);
		Horazionalspeedslider.setMinorTickSpacing(1);
		Horazionalspeedslider.setMinimum(1);
		Horazionalspeedslider.setMaximum(10);
		Horazionalspeedslider.setMajorTickSpacing(1);
		Horazionalspeedslider.setBounds(20, 205, 209, 39);
		getContentPane().add(Horazionalspeedslider);
		/**
		 * jamboBox to select color of the animal
		 */
		ColorBox = new JComboBox<String>();
		ColorBox.addActionListener(this);
		ColorBox.setBackground(SystemColor.control);
		ColorBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink" }));

		ColorBox.setBounds(300, 104, 75, 23);
		getContentPane().add(ColorBox);
		/**
		 * text fields to write the required fields for the user
		 */
		JTextPane txtpnSelectHorzionalSpeed = new JTextPane();
		txtpnSelectHorzionalSpeed.setBackground(SystemColor.control);
		txtpnSelectHorzionalSpeed.setText("Select Horzional Speed:");
		txtpnSelectHorzionalSpeed.setBounds(20, 183, 140, 20);
		getContentPane().add(txtpnSelectHorzionalSpeed);

		JTextPane txtpnSelectVerticalSpeed = new JTextPane();
		txtpnSelectVerticalSpeed.setText("Select Vertical Speed:");
		txtpnSelectVerticalSpeed.setBackground(SystemColor.menu);
		txtpnSelectVerticalSpeed.setBounds(20, 83, 133, 20);
		getContentPane().add(txtpnSelectVerticalSpeed);

		JTextPane txtpnSelectTypeOf = new JTextPane();
		txtpnSelectTypeOf.setText("Select Type Of Animal:");
		txtpnSelectTypeOf.setBackground(SystemColor.menu);
		txtpnSelectTypeOf.setBounds(60, 11, 133, 20);
		getContentPane().add(txtpnSelectTypeOf);

		JTextPane txtpnSelectAnimalColor = new JTextPane();
		txtpnSelectAnimalColor.setText("Select Animal Color:");
		txtpnSelectAnimalColor.setBackground(SystemColor.menu);
		txtpnSelectAnimalColor.setBounds(300, 83, 133, 20);
		getContentPane().add(txtpnSelectAnimalColor);

		JTextPane txtpnSelectAnimalSize = new JTextPane();
		txtpnSelectAnimalSize.setText("Select Animal Size:");
		txtpnSelectAnimalSize.setBackground(SystemColor.menu);
		txtpnSelectAnimalSize.setBounds(0, 255, 133, 20);
		getContentPane().add(txtpnSelectAnimalSize);
		/**
		 * add Animal Button at the bottom of the dialog window to add the created
		 * animal
		 */
		addbtn = new JButton("Add Animal");
		addbtn.addActionListener(this);

		addbtn.setBounds(179, 335, 114, 23);
		getContentPane().add(addbtn);

		txtpnSelectFoodFreq = new JTextPane();
		txtpnSelectFoodFreq.setText("Select Food Freq:");
		txtpnSelectFoodFreq.setBackground(SystemColor.menu);
		txtpnSelectFoodFreq.setBounds(265, 183, 140, 20);
		getContentPane().add(txtpnSelectFoodFreq);

		FoodFreqSlider = new JSlider();
		FoodFreqSlider.setValue(1);
		FoodFreqSlider.setPaintTicks(true);
		FoodFreqSlider.setPaintLabels(true);
		FoodFreqSlider.setMinorTickSpacing(1);
		FoodFreqSlider.setMinimum(5);
		FoodFreqSlider.setMaximum(10);
		FoodFreqSlider.setMajorTickSpacing(1);
		FoodFreqSlider.setBounds(265, 205, 209, 39);
		getContentPane().add(FoodFreqSlider);
		setSize(500, 400);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * connect the Add Button to action and create the animal the user create
	 */
	public void actionPerformed(ActionEvent e) {
		/**
		 * if the add button was clicked do the flowing
		 */
		if (e.getSource().equals(addbtn)) {
			AquaPanel.reset_aqua = false;
			if (Fishrdbutton.isSelected()) {
				/**
				 * create new fish by the class Fish
				 */
				// Fish new_fish = new Fish(panel,SizeSlider.getValue(), (AquaPanel.wd)/2,
				// (AquaPanel.hi)/2, Horazionalspeedslider.getValue(),
				// Verticalspeedslider.getValue(), ColorBox.getSelectedIndex() +
				// 1,FoodFreqSlider.getValue());
				// new_fish.setPanel(panel);
				AF = new AnimalFactory(this);
				Swimmable x = AF.produceSeaCreature("Fish");
				panel.add_ani(x); // add it to the panel

			}

			if (Jellyfishrdbutton.isSelected()) {
				/**
				 * create new jellyfish by the class jellyfish
				 */
				// Jellyfish new_jellyfish = new Jellyfish(panel,SizeSlider.getValue(),
				// (AquaPanel.wd)/2, (AquaPanel.hi)/2,
				// Horazionalspeedslider.getValue(), Verticalspeedslider.getValue(),
				// ColorBox.getSelectedIndex() + 1,FoodFreqSlider.getValue());
				// new_jellyfish.setPanel(panel);
				// panel.add_ani(new_jellyfish); //add the jellyfish to the panel

				AF = new AnimalFactory(this);
				Swimmable x = AF.produceSeaCreature("Jellyfish");
				panel.add_ani(x);

			}

		}

	}
}