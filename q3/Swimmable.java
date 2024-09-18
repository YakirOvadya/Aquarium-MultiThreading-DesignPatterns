package q3;

import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

public abstract class Swimmable extends Thread implements Comparable<Swimmable>, SeaCreature, Cloneable {

	protected int horSpeed;
	protected int verSpeed;

	/**
	 * Default constructor of Swimmable class
	 */
	public Swimmable() {
		horSpeed = 0;
		verSpeed = 0;
	}

	/**
	 * constructor of Swimmable class
	 */
	public Swimmable(int horSpeed, int verSpeed) {
		this.horSpeed = horSpeed;
		this.verSpeed = verSpeed;

	}

	/**
	 * the function returns the horSpeed value;
	 * 
	 * @return
	 */
	public int getHorSpeed() {
		return this.horSpeed;
	}

	/**
	 * the function set the HorSpeed value
	 * 
	 * @param horSpeed
	 */
	public void setHorSpeed(int horSpeed) {
		this.horSpeed = horSpeed;
	}

	/**
	 * the function returns the verSpeed value;
	 * 
	 * @return
	 */
	public int getVerSpeed() {
		return this.verSpeed;
	}

	/**
	 * the function set the verSpeed value
	 * 
	 * @param verSpeed
	 */
	public void setVerSpeed(int verSpeed) {
		this.verSpeed = verSpeed;
	}

	/**
	 * override for CompareTo method
	 * The method makes the comparison according to the size of the creature
	 */
	@Override
	public int compareTo(Swimmable o) {
		if (this.getSize() == o.getSize())
			return 0;
		else if (this.getSize() < o.getSize())
			return 1;
		return -1;

	}

	/* HW1 Methods: */
	public abstract String getAnimalName();

	public abstract int getEatCount();

	public abstract int getSize();

	public abstract String getColor();

	public abstract void eatInc();

	/* HW2 Methods: */

	abstract public void drawAnimal(Graphics g);

	abstract public void setSuspend();

	abstract public void setResume();

	abstract public void setBarrier(CyclicBarrier b);

	public abstract void setState(HungerState state);

	public abstract HungerState getMyState();
}
