package q3;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JPanelDecorator extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	int Selectedrow = 0;
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			Selectedrow = table.rowAtPoint(e.getPoint());
		}
	};

	AquaPanel panel;
	// AquaPanel panel1;
	private HashSet<Swimmable> temp_ani = new HashSet<Swimmable>();
	private JTable table;
	private JDialog ColorChooser;
	JComboBox<String> ColorBox;
	JButton ChangeColor;
	JButton SaveButton;

	public JPanelDecorator(AquaPanel p, HashSet<Swimmable> animals) {
		setTitle("JPanelDecorator");
		panel = p;
		this.temp_ani = animals;
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBounds(10, 11, 564, 187);
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
		table.addMouseListener(mouseAdapter);
		scrollPane.setViewportView(table);

		ChangeColor = new JButton("Change Color");
		ChangeColor.setBounds(185, 390, 157, 48);
		getContentPane().add(ChangeColor);
		setSize(600, 532);
		ChangeColor.addActionListener(this);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}

	private void JColorChooser() {
		ColorChooser = new JDialog();
		ColorChooser.setTitle("JColorChooser");
		ColorChooser.setSize(300, 400);
		ColorChooser.setLayout(null);

		ColorBox = new JComboBox<String>();
		ColorBox.addActionListener(this);
		ColorBox.setBackground(SystemColor.control);
		ColorBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink" }));

		ColorBox.setBounds(70, 60, 75, 23);
		ColorChooser.add(ColorBox);

		JLabel ColorLabel = new JLabel("Select Color:");
		ColorLabel.setBounds(10, 11, 103, 32);
		ColorChooser.add(ColorLabel);

		SaveButton = new JButton("Save Color");
		SaveButton.setBounds(70, 150, 157, 48);
		SaveButton.addActionListener(this);
		ColorChooser.add(SaveButton);
		ColorChooser.setLocationRelativeTo(null);
		ColorChooser.setVisible(true);
		ColorChooser.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(ChangeColor)) {
			JColorChooser();
		}
		if (e.getSource().equals(SaveButton)) {
			Fish temp1 = null;
			Jellyfish temp2 = null;
			if (temp_ani.isEmpty()) {
				JOptionPane.showMessageDialog(this, "You Need To insert animals first!");
			} else if (temp_ani.toArray()[Selectedrow].toString() == "Fish") {
				temp1 = (Fish) temp_ani.toArray()[Selectedrow];

				MarineAnimalDecorator x = new MarineAnimalDecorator();
				temp1.PaintFish(ColorBox.getSelectedIndex() + 1);

			} else if (temp_ani.toArray()[Selectedrow].toString() == "Jellyfish") {
				MarineAnimalDecorator x = new MarineAnimalDecorator();
				temp2 = (Jellyfish) temp_ani.toArray()[Selectedrow];
				temp2.PaintFish(ColorBox.getSelectedIndex() + 1);
			}

		}
	}
}