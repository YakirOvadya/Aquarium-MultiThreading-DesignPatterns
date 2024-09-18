package q3;

import java.awt.Color;

public class PlantFactory implements AbstractSeaFactory {

    private AddPlantDialog AP;

    public PlantFactory(AddPlantDialog AP) {
        this.AP = AP;
    }

    public Immobile produceSeaCreature(String type) {
        if (type.equals("Laminaria"))
            return new Laminaria(AP.panel, AP.SizeSlider.getValue(), AP.x_pos.getValue(), AP.y_pos.getValue());

        else if (type.equalsIgnoreCase("Zostera")) {
            return new Zostera(AP.panel, AP.SizeSlider.getValue(), AP.x_pos.getValue(), AP.y_pos.getValue());
        }
        return null;

    }

}