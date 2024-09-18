package q3;

public class UnusualFish extends Fish {

	private int factor;

	/**
	 * Constructor of UnusualFish class
	 * 
	 * @param size
	 * @param x_front
	 * @param y_front
	 * @param horSpeed
	 * @param verSpeed
	 * @param col
	 * @param factor
	 */
	public UnusualFish(AquaPanel panel, int size, int x_front, int y_front, int horSpeed, int verSpeed, int col,
			int factor, int food_freq) {

		super(panel, size, x_front, y_front, horSpeed, verSpeed, col, food_freq);
		this.factor = factor;

	}

	/**
	 * Default constructor of UnusualFish class
	 */
	public UnusualFish() {

		super(null, 0, 0, 0, 0, 0, 0, 0);
		this.factor = 0;

	}

	/**
	 * Copy constructor of UnusualFish class
	 */
	public UnusualFish(UnusualFish other) {

		super(other.getPanel(), other.getSize(), other.getX_front(), other.getY_front(), other.getHorSpeed(),
				other.getVerSpeed(), other.getCol(), other.getFood_freq());
		other.factor = this.factor;

	}

	/**
	 * 
	 * @return factor value
	 */
	public int getFactor() {
		return factor;
	}

	/**
	 * set function for factor value
	 * 
	 * @param factor
	 */
	public void setFactor(int factor) {
		this.factor = factor;
	}

	/**
	 * the function returns the size value of UnusualFish
	 */
	public int getSize() {
		return (this.getSize_forUnFish()) * (this.factor);
	}

	/**
	 * the function returns the kind of the creature as "UnusualFish"
	 */
	@Override
	public String getAnimalName() {
		return "UnusualFish";
	}

	/**
	 * override of toString method the functions returns the kind of the creature as
	 * "UnusualFish"
	 */
	@Override
	public String toString() {
		return "UnusualFish";
	}

	/**
	 * override equals operator the operator checks the type of the creatures
	 * if they have the same type the operator checks the size of the creatures
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof UnusualFish) {
			if (this.getSize() == ((UnusualFish) o).getSize()) {
				return true;
			}
		}
		return false;
	}

}