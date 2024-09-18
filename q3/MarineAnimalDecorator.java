package q3;

public class MarineAnimalDecorator implements MarineAnimal {

	private MarineAnimal Manimal;

	public MarineAnimalDecorator(MarineAnimal Manimal) {
		this.Manimal = Manimal;
	}

	public MarineAnimalDecorator() {
	};

	@Override
	public void PaintFish(int col) {
	}

}
