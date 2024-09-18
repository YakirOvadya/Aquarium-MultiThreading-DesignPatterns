package q3;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class AddPlantDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    JSlider SizeSlider;
    JButton addbtn;

    JRadioButton Lamirdbutton; // fish
    JRadioButton Zosrdbutton; // jellyfish

    AquaPanel panel;

    JSlider x_pos;

    JSlider y_pos;

    private static PlantFactory PF;

    /**
     * Add animal Dialog builder get panel P from type aqua panel
     * build the dialog window with sliders to get the required
     * speed/size/type/color
     * and add the animals to the aqua panel
     * 
     * @param p
     */

    public AddPlantDialog(AquaPanel p) {
        setTitle("Dialog - Plant");

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
         * Radio button to select animal type Laminaria
         */
        Lamirdbutton = new JRadioButton("Laminaria");
        buttonGroup.add(Lamirdbutton);
        Lamirdbutton.setBounds(150, 33, 95, 23);
        getContentPane().add(Lamirdbutton);
        /**
         * Radio button to select animal type Zostera
         */
        Zosrdbutton = new JRadioButton("Zostera");
        buttonGroup.add(Zosrdbutton);
        Zosrdbutton.setBounds(270, 33, 75, 23);
        getContentPane().add(Zosrdbutton);

        x_pos = new JSlider();
        x_pos.setPaintLabels(true);
        x_pos.setMajorTickSpacing(100);
        x_pos.setMinorTickSpacing(50);
        x_pos.setValue(100);
        x_pos.setMinimum(0);
        x_pos.setMaximum(panel.getWidth());
        x_pos.setPaintTicks(true);
        x_pos.setBounds(0, 104, 484, 49);
        getContentPane().add(x_pos);

        y_pos = new JSlider();
        y_pos.setPaintLabels(true);
        y_pos.setMajorTickSpacing(50);
        y_pos.setMinorTickSpacing(25);
        y_pos.setValue(100);
        y_pos.setMinimum(panel.getHeight() / 2);
        y_pos.setMaximum(panel.getHeight());
        y_pos.setPaintTicks(true);
        y_pos.setBounds(0, 205, 484, 49);
        getContentPane().add(y_pos);

        JTextPane txtpnSelectHorzionalSpeed = new JTextPane();
        txtpnSelectHorzionalSpeed.setBackground(SystemColor.control);
        txtpnSelectHorzionalSpeed.setText("Select Y position:");
        txtpnSelectHorzionalSpeed.setBounds(20, 183, 140, 20);
        getContentPane().add(txtpnSelectHorzionalSpeed);

        JTextPane txtpnSelectVerticalSpeed = new JTextPane();
        txtpnSelectVerticalSpeed.setText("Select X position:");
        txtpnSelectVerticalSpeed.setBackground(SystemColor.menu);
        txtpnSelectVerticalSpeed.setBounds(20, 83, 133, 20);
        getContentPane().add(txtpnSelectVerticalSpeed);

        JTextPane txtpnSelectTypeOf = new JTextPane();
        txtpnSelectTypeOf.setText("Select Type Of Plant:");
        txtpnSelectTypeOf.setBackground(SystemColor.menu);
        txtpnSelectTypeOf.setBounds(175, 11, 133, 20);
        getContentPane().add(txtpnSelectTypeOf);

        JTextPane txtpnSelectAnimalSize = new JTextPane();
        txtpnSelectAnimalSize.setText("Select Plant Size:");
        txtpnSelectAnimalSize.setBackground(SystemColor.menu);
        txtpnSelectAnimalSize.setBounds(0, 255, 133, 20);
        getContentPane().add(txtpnSelectAnimalSize);
        /**
         * add Animal Button at the bottom of the dialog window to add the created
         * animal
         */
        addbtn = new JButton("Add Plant");
        addbtn.addActionListener(this);

        addbtn.setBounds(179, 335, 114, 23);
        getContentPane().add(addbtn);
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
            if (Lamirdbutton.isSelected()) {

                /**
                 * create new Laminaria by the class Laminaria
                 */

                // Laminaria new_lami = new
                // Laminaria(panel,SizeSlider.getValue(),x_pos.getValue(),y_pos.getValue());
                // panel.add_plant_p(new_lami);

                PF = new PlantFactory(this);
                Immobile x = PF.produceSeaCreature("Laminaria");
                panel.add_plant_p(x);

            }

            if (Zosrdbutton.isSelected()) {
                /**
                 * create new Zostera by the class Zostera
                 */
                // Zostera new_zos = new
                // Zostera(panel,SizeSlider.getValue(),x_pos.getValue(),y_pos.getValue());
                // panel.add_plant_p(new_zos);

                PF = new PlantFactory(this);
                Immobile x = PF.produceSeaCreature("Zostera");
                panel.add_plant_p(x);

            }

        }

    }
}