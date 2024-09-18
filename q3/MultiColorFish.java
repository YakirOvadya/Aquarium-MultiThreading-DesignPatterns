package q3;

public class MultiColorFish extends Fish {

	/**
	 * Constructor for MultiColorFish class
	 * 
	 * @param size
	 * @param x_front
	 * @param y_front
	 * @param horSpeed
	 * @param verSpeed
	 * @param col
	 */
	public MultiColorFish(AquaPanel panel, int size, int x_front, int y_front, int horSpeed, int verSpeed, int col,
			int food_freq) {
		super(panel, size, x_front, y_front, horSpeed, verSpeed, col, food_freq);
	}

	/**
	 * Default constructor of MultiColorFish class
	 */
	public MultiColorFish() {
		super(null, 0, 0, 0, 0, 0, 0, 0);
	}

	/**
	 * Copy constructor for MultiColorFish class
	 * 
	 * @param other
	 */
	public MultiColorFish(MultiColorFish other) {
		super(other.getPanel(), other.getSize(), other.getX_front(), other.getY_front(), other.getHorSpeed(),
				other.getVerSpeed(), other.getCol(), other.getFood_freq());
	}

	/**
	 * the function increase the amount of eatCount
	 * the function changing the size of the fish if the fish reached to max Eat
	 * Distance
	 */
	@Override
	public void eatInc() {

		this.setEatCount(getEatCount() + 1);
		if (this.getEatCount() == this.getEAT_DISTANCE()) {
			this.setSize(this.getSize() + 1);
			this.setEatCount(0);
			this.changeColor();

		}
	}

	/**
	 * the function returns the kind of the creature as "MultiColorFish"
	 */
	@Override
	public String getAnimalName() {
		return "MultiColorFish";
	}

	/**
	 * override of toString method the functions returns the kind of the creature as
	 * "MultiColorFish"
	 */
	@Override
	public String toString() {
		return "MultiColorFish";
	}

	/**
	 * override equals operator the operator checks the type of the creatures
	 * if they have the same type the operator checks the size of the creatures
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof MultiColorFish) {
			if (this.getSize() == ((MultiColorFish) o).getSize()) {
				return true;
			}
		}
		return false;
	}
}