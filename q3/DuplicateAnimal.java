package q3;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DuplicateAnimal extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	JComboBox<String> ColorBox;

	JSlider SizeSlider;
	JSlider Verticalspeedslider; // vertical Speed
	JSlider Horazionalspeedslider;

	AquaPanel panel;
	private HashSet<Swimmable> temp_ani = new HashSet<Swimmable>();
	private JTable table;

	JButton btnDup, btnUpdate;
	JSlider IndexSlider;
	private JSlider FoodFreqSliderr;
	private JTextPane txtpnSelectFoodFreq;

	public DuplicateAnimal(AquaPanel p, HashSet animals) {
		setTitle("Dialog - Animal");
		panel = p;
		this.temp_ani = animals;
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
		SizeSlider.setBounds(0, 385, 484, 49);
		getContentPane().add(SizeSlider);
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
		Verticalspeedslider.setBounds(0, 205, 209, 39);
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
		Horazionalspeedslider.setBounds(0, 294, 209, 39);
		getContentPane().add(Horazionalspeedslider);
		/**
		 * jamboBox to select color of the animal
		 */
		ColorBox = new JComboBox<String>();
		ColorBox.addActionListener(this);
		ColorBox.setBackground(SystemColor.control);
		ColorBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink" }));

		ColorBox.setBounds(298, 351, 75, 23);
		getContentPane().add(ColorBox);
		/**
		 * text fields to write the required fields for the user
		 */
		JTextPane txtpnSelectHorzionalSpeed = new JTextPane();
		txtpnSelectHorzionalSpeed.setBackground(SystemColor.control);
		txtpnSelectHorzionalSpeed.setText("Select Horzional Speed:");
		txtpnSelectHorzionalSpeed.setBounds(0, 263, 140, 20);
		getContentPane().add(txtpnSelectHorzionalSpeed);

		JTextPane txtpnSelectVerticalSpeed = new JTextPane();
		txtpnSelectVerticalSpeed.setText("Select Vertical Speed:");
		txtpnSelectVerticalSpeed.setBackground(SystemColor.menu);
		txtpnSelectVerticalSpeed.setBounds(0, 174, 133, 20);
		getContentPane().add(txtpnSelectVerticalSpeed);

		JTextPane txtpnSelectAnimalColor = new JTextPane();
		txtpnSelectAnimalColor.setText("Select Animal Color:");
		txtpnSelectAnimalColor.setBackground(SystemColor.menu);
		txtpnSelectAnimalColor.setBounds(298, 320, 133, 20);
		getContentPane().add(txtpnSelectAnimalColor);

		JTextPane txtpnSelectAnimalSize = new JTextPane();
		txtpnSelectAnimalSize.setText("Select Animal Size:");
		txtpnSelectAnimalSize.setBackground(SystemColor.menu);
		txtpnSelectAnimalSize.setBounds(0, 354, 133, 20);
		getContentPane().add(txtpnSelectAnimalSize);
		/**
		 * add Animal Button at the bottom of the dialog window to add the created
		 * animal
		 */
		btnDup = new JButton("Duplicate");
		btnDup.addActionListener(this);

		btnDup.setBounds(158, 459, 114, 23);
		getContentPane().add(btnDup);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBounds(10, 11, 564, 129);
		getContentPane().add(scrollPane);
		String[] columns = { "Index", "Animal", "Color", "Size", "Hor.Speed", "Ver.Speed", "Eat Counter" };
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

		table = new JTable(tableModel);
		int i = 1;
		for (Swimmable s : temp_ani) {

			String[] Row = new String[] { Integer.toString(i), s.getAnimalName(), s.getColor(),
					String.valueOf(s.getSize()),
					String.valueOf(s.getHorSpeed()), String.valueOf(s.getVerSpeed()), String.valueOf(s.getEatCount()) };
			scrollPane.setViewportView(table);
			tableModel.addRow(Row);
			i++;
		}

		scrollPane.setViewportView(table);

		IndexSlider = new JSlider();
		IndexSlider.setValue(1);
		IndexSlider.setPaintTicks(true);
		IndexSlider.setPaintLabels(true);
		IndexSlider.setMinorTickSpacing(1);
		IndexSlider.setMinimum(1);
		IndexSlider.setMaximum(animals.size());
		IndexSlider.setMajorTickSpacing(1);
		IndexSlider.setBounds(298, 205, 209, 39);
		getContentPane().add(IndexSlider);

		JTextPane Indexlabel = new JTextPane();
		Indexlabel.setText("Select Animal index:");
		Indexlabel.setBackground(SystemColor.menu);
		Indexlabel.setBounds(298, 174, 165, 20);
		getContentPane().add(Indexlabel);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(298, 459, 114, 23);
		getContentPane().add(btnUpdate);

		FoodFreqSliderr = new JSlider();
		FoodFreqSliderr.setValue(1);
		FoodFreqSliderr.setPaintTicks(true);
		FoodFreqSliderr.setPaintLabels(true);
		FoodFreqSliderr.setMinorTickSpacing(1);
		FoodFreqSliderr.setMinimum(5);
		FoodFreqSliderr.setMaximum(10);
		FoodFreqSliderr.setMajorTickSpacing(1);
		FoodFreqSliderr.setBounds(298, 274, 209, 39);
		getContentPane().add(FoodFreqSliderr);

		txtpnSelectFoodFreq = new JTextPane();
		txtpnSelectFoodFreq.setText("Select Food Freq:");
		txtpnSelectFoodFreq.setBackground(SystemColor.menu);
		txtpnSelectFoodFreq.setBounds(298, 255, 165, 20);
		getContentPane().add(txtpnSelectFoodFreq);
		setSize(600, 532);
		btnUpdate.addActionListener(this);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * connect the Add Button to action and create the animal the user create
	 */
	public void actionPerformed(ActionEvent e) {

		int i = 1;
		Fish temp1 = null;
		Jellyfish temp2 = null;
		for (Swimmable s : temp_ani) {
			if (i == IndexSlider.getValue()) {
				if (s.getAnimalName() == "Fish") {
					temp1 = (Fish) s;
				}

				if (s.getAnimalName() == "Jellyfish") {
					temp2 = (Jellyfish) s;
				}
			}
			i++;
		}

		if (e.getSource().equals(btnDup)) {
			if (temp_ani.size() == 5) {
				JOptionPane.showMessageDialog(this, "This Aquarium can contain maximum 5 plantes.\n");
			} else {
				if (temp1 != null) {
					// Fish new_fish = new Fish(panel, temp1.getSize(), temp1.getX_front(),
					// temp1.getY_front(), temp1.getHorSpeed(),
					// temp1.getVerSpeed(), temp1.getCol(), temp1.getFood_freq());
					// new_fish.setPanel(panel);
					// panel.add_ani(new_fish);

					Fish new_fish = new Fish();
					new_fish.Clone(temp1);
					// new_fish.setPanel(panel);
					panel.add_ani(new_fish);

				}

				if (temp2 != null) {
					// Jellyfish new_jelly = new Jellyfish(panel, temp2.getSize(),
					// temp2.getX_front(), temp2.getY_front(), temp2.getHorSpeed(),
					// temp2.getVerSpeed(), temp2.getCol(),temp2.getFood_freq());
					// panel.add_ani(new_jelly);

					Jellyfish new_jelly = new Jellyfish();
					new_jelly.Clone(temp2);
					panel.add_ani(new_jelly);
				}

			}
		}
		if (e.getSource().equals(btnUpdate)) {

			if (temp1 != null) {
				temp1.setSize(SizeSlider.getValue());
				temp1.setHorSpeed(Horazionalspeedslider.getValue());
				temp1.setVerSpeed(Verticalspeedslider.getValue());
				temp1.setCol(ColorBox.getSelectedIndex() + 1);
				temp1.setFood_freq(FoodFreqSliderr.getValue());
			}

			if (temp2 != null) {
				temp2.setSize(SizeSlider.getValue());
				temp2.setHorSpeed(Horazionalspeedslider.getValue());
				temp2.setVerSpeed(Verticalspeedslider.getValue());
				temp2.setCol(ColorBox.getSelectedIndex() + 1);

			}
		}

	}

}