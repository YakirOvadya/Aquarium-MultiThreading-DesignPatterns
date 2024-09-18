package q3;

import java.awt.Color;

public class AnimalFactory implements AbstractSeaFactory {

    AddAnimalDialog AD;

    public AnimalFactory(AddAnimalDialog AD) {
        this.AD = AD;
    }

    @Override
    public Swimmable produceSeaCreature(String type) {
        if (type.equals("Fish")) {
            return new Fish(AD.panel, AD.SizeSlider.getValue(), AD.panel.getWidth() / 2, AD.panel.getHeight() / 2,
                    AD.Horazionalspeedslider.getValue(), AD.Verticalspeedslider.getValue(),
                    AD.ColorBox.getSelectedIndex() + 1, AD.FoodFreqSlider.getValue());
        }

        if (type.equals("Jellyfish")) {
            return new Jellyfish(AD.panel, AD.SizeSlider.getValue(), AD.panel.getWidth() / 2, AD.panel.getHeight() / 2,
                    AD.Horazionalspeedslider.getValue(), AD.Verticalspeedslider.getValue(),
                    AD.ColorBox.getSelectedIndex() + 1, AD.FoodFreqSlider.getValue());
        }

        return null;
    }

}