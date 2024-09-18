package q3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Jellyfish extends Swimmable implements MarineAnimal {
	/**
	 * Variable declaration of all needed
	 */
	private int EAT_DISTANCE;
	private int size;
	private int col;
	private int eatCount;
	private int x_front;
	private int y_front;
	private int x_dir;
	private int y_dir;

	private int food_freq;

	private Thread timer;
	private Boolean suspended;

	private int x_flag = 1;
	private int y_flag = 1;

	private CyclicBarrier barrier;

	private AquaPanel panel;

	private HungerState myState;

	/**
	 * Constructor for Jellyfish class
	 * 
	 * @param size
	 * @param x_front
	 * @param y_front
	 * @param horSpeed
	 * @param verSpeed
	 * @param col
	 */
	public Jellyfish(AquaPanel panel, int size, int x_front, int y_front, int horSpeed, int verSpeed, int col,
			int food_freq) {

		super(horSpeed, verSpeed);
		this.EAT_DISTANCE = 4;
		this.size = size;
		this.col = col;
		this.eatCount = 0;
		this.x_front = x_front;
		this.y_front = y_front;
		this.x_dir = 1;
		this.y_dir = 1;
		this.panel = panel;
		this.food_freq = food_freq;
		this.suspended = false;
		timer = new Thread(this);
		timer.start();
	}

	/**
	 * Default constructor for Jellyfish class
	 */
	public Jellyfish() {
		super(0, 0);

	}

	/**
	 * Copy constructor for Jellyfish class
	 * 
	 * @param other
	 */
	public Jellyfish(Jellyfish other) {
		super(other.horSpeed, other.verSpeed);
		this.EAT_DISTANCE = other.EAT_DISTANCE;
		this.size = other.size;
		this.col = other.col;
		this.eatCount = other.eatCount;
		this.x_front = other.x_front;
		this.y_front = other.y_front;
		this.x_dir = other.x_dir;
		this.y_dir = other.y_dir;
	}

	/**
	 * return eatCount value
	 */
	@Override
	public int getEatCount() {
		return this.eatCount;
	}

	/**
	 * return Size value
	 */
	@Override
	public int getSize() {
		return this.size;
	}

	/**
	 * return the color of the fish
	 */
	@Override
	public String getColor() {
		if (col == 1)
			return "Black";
		if (col == 2)
			return "Red";
		if (col == 3)
			return "Blue";
		if (col == 4)
			return "Green";
		if (col == 5)
			return "Cyan";
		if (col == 6)
			return "Orange";
		if (col == 7)
			return "Yellow";
		if (col == 8)
			return "Magenta";
		if (col == 9)
			return "Pink";
		return null;
	}

	/**
	 * @return the color selected
	 */
	public int[] getColor_arr() {

		int[] colorArray = { 0, 0, 0 };

		if (col == 1)
			return colorArray;

		if (col == 2) {
			colorArray[0] = 255;
			return colorArray;
		}
		if (col == 3) {
			colorArray[2] = 255;
			return colorArray;
		}
		if (col == 4) {
			colorArray[1] = 255;
			return colorArray;
		}
		if (col == 5) {
			colorArray[1] = 255;
			colorArray[2] = 255;
			return colorArray;
		}
		if (col == 6) {
			colorArray[0] = 255;
			colorArray[1] = 165;
			return colorArray;
		}
		if (col == 7) {
			colorArray[0] = 255;
			colorArray[1] = 255;
			return colorArray;
		}
		if (col == 8) {
			colorArray[0] = 255;
			colorArray[2] = 255;
			return colorArray;
		}
		if (col == 9) {
			colorArray[0] = 255;
			colorArray[1] = 192;
			colorArray[2] = 203;
			return colorArray;
		}

		return null;

	}

	/**
	 * the function increase the amount of eatCount the function changing the size
	 * of the fish if the fish reached to max Eat Distance
	 */
	@Override
	public void eatInc() {
		this.eatCount++;
		if (this.eatCount == this.EAT_DISTANCE) {
			this.size++;
			this.eatCount = 0;
		}
	}

	/**
	 * the function changing the size of the fish
	 * 
	 * @param size
	 */
	public void changeJellyfish(int size) {
		this.size = size;
	}

	/**
	 * the function returns the kind of the swimmable creature as "Jellyfish"
	 */
	@Override
	public String getAnimalName() {

		return "Jellyfish";
	}

	/**
	 * override of toString for the swimmable creature as "Jellyfish"
	 */
	@Override
	public String toString() {
		return "Jellyfish";
	}

	/**
	 * 
	 * @return the EAT_DISTANCE value
	 */
	public int getEAT_DISTANCE() {
		return EAT_DISTANCE;
	}

	/**
	 * set function for eat_distance
	 * 
	 * @param eAT_DISTANCE
	 */
	public void setEAT_DISTANCE(int eAT_DISTANCE) {
		EAT_DISTANCE = eAT_DISTANCE;
	}

	/**
	 * 
	 * @return the color of the jellyfish
	 */
	public int getCol() {
		return col;
	}

	/**
	 * set function for the color of the jellyfish
	 * 
	 * @param col
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * get function for the x_front
	 * 
	 * @return x_front value
	 */
	public int getX_front() {
		return x_front;
	}

	/**
	 * set function for x_front
	 * 
	 * @param x_front
	 */
	public void setX_front(int x_front) {
		this.x_front = x_front;
	}

	/**
	 * get function for the y_front
	 * 
	 * @return y_front value
	 */
	public int getY_front() {
		return y_front;
	}

	/**
	 * set function for y_front
	 * 
	 * @param y_front
	 */
	public void setY_front(int y_front) {
		this.y_front = y_front;
	}

	/**
	 * get function for the x_dir
	 * 
	 * @return x_dir value
	 */
	public int getX_dir() {
		return x_dir;
	}

	/**
	 * set function for x_dir
	 * 
	 * @param x_dir
	 */
	public void setX_dir(int x_dir) {
		this.x_dir = x_dir;
	}

	/**
	 * 
	 * @return y_dir value
	 */
	public int getY_dir() {
		return y_dir;
	}

	/**
	 * set function for y_dir value
	 * 
	 * @param y_dir
	 */
	public void setY_dir(int y_dir) {
		this.y_dir = y_dir;
	}

	/**
	 * set function for size value
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * set function for eatCount value
	 * 
	 * @param eatCount
	 */
	public void setEatCount(int eatCount) {
		this.eatCount = eatCount;
	}

	/**
	 * override equals operator the operator checks the type of the creatures if
	 * they have the same type the operator checks the size of the creatures
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Jellyfish) {
			if (this.getSize() == ((Jellyfish) o).getSize()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * function to draw the fish animal
	 */
	public void drawAnimal(Graphics g) {

		int[] temp_color = getColor_arr();
		Color c = new Color(temp_color[0], temp_color[1], temp_color[2]);
		int numLegs;
		if (size < 40)
			numLegs = 5;
		else if (size < 80)
			numLegs = 9;
		else
			numLegs = 12;

		g.setColor(c);
		g.fillArc(x_front - size / 2, y_front - size / 4, size, size / 2, 0, 180);

		for (int i = 0; i < numLegs; i++)
			g.drawLine(x_front - size / 2 + size / numLegs + size * i / (numLegs + 1), y_front,
					x_front - size / 2 + size / numLegs + size * i / (numLegs + 1), y_front + size / 3);
	}

	/**
	 * set the flag suspend to true
	 */
	public void setSuspend() {
		suspended = true;
	}

	/**
	 * set the flag suspend to false and return all the threads to work again
	 */
	public void setResume() {
		suspended = false;
		synchronized (this) {
			notify();
		}
	}

	/**
	 * run function for every animal that is actually a thread to this function is
	 * the run of the thread we
	 * created
	 */
	public void run() {

		Thread me = Thread.currentThread();// get the current thread

		while (timer == me) {// while its the same thread as the thread who started

			if (panel.reset_aqua == true) {
				break;
			}

			if (suspended) {// if suspended flag is true put all the threads on wait
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (panel.food_sign == false) {
				x_front += horSpeed * x_flag;
				y_front += verSpeed * y_flag;

			} else { // there is food on the screen We need Barrier!

				if (this.barrier != null) {

					try {
						this.barrier.await();

					} catch (InterruptedException ex) {
						return;
					} catch (BrokenBarrierException ex) {
						return;
					}
				}

				this.barrier = null;

				if (x_front > panel.wd / 2) {// if to let the animal swim only in the borders of the window
					if (x_dir > 0) {
						x_flag = -1;
						x_dir = -1;
					}

					if (y_front > panel.hi / 2) {
						if (y_flag > 0) {
							y_flag = -1;
						}

					}

					if (y_front < panel.hi / 2) {
						if (y_flag < 0) {
							y_flag = 1;
						}
					}

				} else if (x_front < panel.wd / 2) {
					if (x_dir < 0) {
						x_flag = 1;
						x_dir = 1;
					}
					if (y_front > panel.hi / 2) {
						if (y_flag > 0) {
							y_flag = -1;
						}

					}

					if (y_front < panel.hi / 2) {
						if (y_flag < 0) {
							y_flag = 1;
						}
					}
				}

				if (!((x_front > (panel.wd / 2) - 5) && (x_front < (panel.wd / 2) + 5))) {
					x_front += horSpeed * x_flag;
				} // when the animals is 5 pixel close to the worm its will put out the x_flag or
					// the Y_flag

				if (!((y_front > (panel.hi / 2) - 5) && (y_front < (panel.hi / 2) + 5))) {
					y_front += verSpeed * y_flag;
				}

				this.check_eat();

			}

			if (x_front > panel.wd) {
				x_flag = -1;
				x_front = panel.wd;
				x_dir = -1;

			} else if (x_front < 0) {
				x_flag = 1;
				x_front = 0;
				x_dir = 1;

			}

			if (y_front > panel.hi) {
				y_flag = -1;
				y_front = panel.hi;

			} else if (y_front < 0) {
				y_flag = 1;
				y_front = 0;

			}

		}
	}

	public void setBarrier(CyclicBarrier b) {
		barrier = b;
	}

	/**
	 * function that check if the animal eat and if yeas its add it to the animal
	 * who eat and increase the total
	 * amount to eat
	 */
	public void check_eat() {
		if (((x_front > (panel.wd / 2) - 5) && (x_front < (panel.wd / 2) + 5))
				&& ((y_front > (panel.hi / 2) - 5) && (y_front < (panel.hi / 2) + 5))) {

			synchronized (panel) {
				if (panel.food_sign == true && panel.getWormInstance() != null) {
					System.out.println("jelly fish eat");
					this.eatInc();
					this.getPanel().setTotal_eat();
				}
				panel.food_sign = false;
				panel.setWormInstance();
			}
		}
	}

	/**
	 * @return the panel that we work on
	 */
	public AquaPanel getPanel() {
		return panel;
	}

	/**
	 * set the panel
	 * 
	 * @param panel
	 */
	public void setPanel(AquaPanel panel) {
		this.panel = panel;
	}

	public void drawCreatrue(Graphics g) {

		int[] temp_color = getColor_arr();
		Color c = new Color(temp_color[0], temp_color[1], temp_color[2]);
		int numLegs;
		if (size < 40)
			numLegs = 5;
		else if (size < 80)
			numLegs = 9;
		else
			numLegs = 12;

		g.setColor(c);
		g.fillArc(x_front - size / 2, y_front - size / 4, size, size / 2, 0, 180);

		for (int i = 0; i < numLegs; i++)
			g.drawLine(x_front - size / 2 + size / numLegs + size * i / (numLegs + 1), y_front,
					x_front - size / 2 + size / numLegs + size * i / (numLegs + 1), y_front + size / 3);
	}

	@Override
	public void setState(HungerState state) {
		myState = state;

	}

	@Override
	public void PaintFish(int col) {
		this.setCol(col);
	}

	public HungerState getMyState() {
		return myState;
	}

	public int getFood_freq() {
		return food_freq;
	}

	public void Clone(Jellyfish x) {

		this.panel = x.panel;
		this.size = x.size;
		this.x_front = x.x_front;
		this.y_front = x.y_front;
		this.horSpeed = x.horSpeed;
		this.verSpeed = x.verSpeed;
		this.col = x.col;
		this.food_freq = x.food_freq;
		this.EAT_DISTANCE = 4;

		this.x_dir = 1;
		this.y_dir = 1;

		this.suspended = false;

		myState = new Satiated();
		timer = new Thread(this);
		timer.start();
	}
}
