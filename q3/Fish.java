package q3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Fish extends Swimmable implements MarineAnimal {
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

	private Thread timer;
	private Boolean suspended;

	private int x_flag = 1;
	private int y_flag = 1;

	private AquaPanel panel;

	private CyclicBarrier barrier;

	private int counter_food;

	private int food_freq;

	private HungerState myState;

	/**
	 * Fish class constructor
	 * 
	 * @param size
	 * @param x_front
	 * @param y_front
	 * @param horSpeed
	 * @param verSpeed
	 * @param col
	 */
	public Fish(AquaPanel panel, int size, int x_front, int y_front, int horSpeed, int verSpeed, int col,
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
		this.suspended = false;
		this.counter_food = 0;
		this.food_freq = food_freq;

		myState = new Satiated();
		timer = new Thread(this);
		timer.start();

	}

	/**
	 * @return barrier
	 */
	public CyclicBarrier getBarrier() {
		return barrier;
	}

	/**
	 * Default Constructor of Fish class
	 */
	public Fish() {
		super(0, 0);
		// this.EAT_DISTANCE = 0;
		// this.size = 0;
		// this.col = 0;
		// this.eatCount = 0;
		// this.x_front = 0;
		// this.y_front = 0;
		// this.x_dir = 0;
		// this.y_dir = 0;

	}

	/**
	 * Copy constructor of fish class
	 * 
	 * @param other
	 */
	public Fish(Fish other) {

		super(other.horSpeed, other.verSpeed);
		other.EAT_DISTANCE = this.EAT_DISTANCE;
		other.size = this.size;
		other.col = this.col;
		other.eatCount = this.eatCount;
		other.x_front = this.x_front;
		other.y_front = this.y_front;
		other.x_dir = this.x_dir;
		other.y_dir = this.y_dir;

	}

	/**
	 * 
	 * @return x_front value;
	 */
	public int getX_front() {

		return x_front;
	}

	/**
	 * set function for x_front variable
	 * 
	 * @param x_front
	 */

	public void setX_front(int x_front) {

		this.x_front = x_front;
	}

	/**
	 * 
	 * @return y_front value;
	 */
	public int getY_front() {
		return y_front;
	}

	/**
	 * set function for y_front variable
	 * 
	 * @param y_front
	 */
	public void setY_front(int y_front) {
		this.y_front = y_front;
	}

	/**
	 * 
	 * @return x_dir value
	 */
	public int getX_dir() {
		return x_dir;
	}

	/**
	 * set function for x_dir variable
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
	 * set function for y_dir variable
	 * 
	 * @param y_dir
	 */
	public void setY_dir(int y_dir) {
		this.y_dir = y_dir;
	}

	/**
	 * the function returns the color of the fish
	 */
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
	 * function returns eatCount value
	 */
	@Override
	public int getEatCount() {
		return this.eatCount;
	}

	/**
	 * the function returns the size of the fish
	 */

	@Override
	public int getSize() {
		return this.size;
	}

	/**
	 * 
	 * @return the size of the fish
	 */
	public int getSize_forUnFish() {
		return this.size;
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
	 * 
	 * @return getCol value;
	 */
	public int getCol() {
		return col;
	}

	/**
	 * set function for the color of the fish
	 * 
	 * @param col
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * set function for the size of the fish
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * set function for EAT_DISTANCE value;
	 * 
	 * @param eAT_DISTANCE
	 */
	public void setEAT_DISTANCE(int eAT_DISTANCE) {
		EAT_DISTANCE = eAT_DISTANCE;
	}

	/**
	 * 
	 * @return eat_distance value;
	 */
	public int getEAT_DISTANCE() {
		return EAT_DISTANCE;
	}

	/**
	 * the function changing the size of the fish;
	 * 
	 * @param
	 */
	public void changeFish() {
		this.size++;
	}

	/**
	 * the function changing the color of the fish;
	 */
	public void changeColor() {
		if (this.col == 9) {
			this.col = 1;
		}

		else {
			this.col++;
		}
	}

	/**
	 * the function returns the kind of the Swimmable creature "Fish"
	 */
	@Override
	public String getAnimalName() {
		return "Fish";
	}

	/**
	 * override for to_string function returns "Fish" as value;
	 */
	@Override
	public String toString() {
		return "Fish";
	}

	/**
	 * overriding for equals operator
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Fish) {
			if (this.getSize() == ((Fish) o).getSize()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * function to draw the fish animal
	 */
	public void drawAnimal(Graphics g) {
		// System.out.println(this.getFood_freq());
		int[] temp_color = getColor_arr();
		Color c = new Color(temp_color[0], temp_color[1], temp_color[2]);

		g.setColor(c);

		if (x_dir == 1) // fish swims to right side
		{
			// Body of fish
			g.fillOval(x_front - size, y_front - size / 4, size, size / 2);

			// Tail of fish
			int[] x_t = { x_front - size - size / 4, x_front - size - size / 4, x_front - size };
			int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
			Polygon t = new Polygon(x_t, y_t, 3);
			g.fillPolygon(t);

			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue()));
			g2.fillOval(x_front - size / 5, y_front - size / 10, size / 10, size / 10);

			// Mouth of fish
			if (size > 70)
				g2.setStroke(new BasicStroke(3));
			else if (size > 30)
				g2.setStroke(new BasicStroke(2));
			else
				g2.setStroke(new BasicStroke(1));
			g2.drawLine(x_front, y_front, x_front - size / 10, y_front + size / 10);
			g2.setStroke(new BasicStroke(1));

		} else // fish swims to left side
		{
			// Body of fish
			g.fillOval(x_front, y_front - size / 4, size, size / 2);

			// Tail of fish
			int[] x_t = { x_front + size + size / 4, x_front + size + size / 4, x_front + size };
			int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
			Polygon t = new Polygon(x_t, y_t, 3);
			g.fillPolygon(t);

			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue()));
			g2.fillOval(x_front + size / 10, y_front - size / 10, size / 10, size / 10);

			// Mouth of fish
			if (size > 70)
				g2.setStroke(new BasicStroke(3));
			else if (size > 30)
				g2.setStroke(new BasicStroke(2));
			else
				g2.setStroke(new BasicStroke(1));

			g2.drawLine(x_front, y_front, x_front + size / 10, y_front + size / 10);
			g2.setStroke(new BasicStroke(1));

		}
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

		Thread me = Thread.currentThread(); // get the current thread

		while (timer == me) { // while its the same thread as the thread who started

			if (panel.reset_aqua == true) {
				break;
			}

			if (suspended) { // if suspended flag is true put all the threads on wait
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

			if (panel.food_sign == false || myState instanceof Satiated) {
				// if (panel.food_sign == false){

				x_front += horSpeed * x_flag;
				y_front += verSpeed * y_flag;

				if (food_freq == counter_food) {
					panel.update(this);
					counter_food = 0;
					myState = new Hungry();
				}

			} else { // there is food on the screen

				if (barrier != null) {

					try {
						barrier.await();

					} catch (InterruptedException ex) {
						return;
					} catch (BrokenBarrierException ex) {
						return;
					}
				}

				barrier = null;

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

				counter_food++;

			} else if (x_front < 0) {
				x_flag = 1;
				x_front = 0;
				x_dir = 1;

				counter_food++;

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
					System.out.println("fish eat");
					this.eatInc();
					this.getPanel().setTotal_eat();
				}
				panel.food_sign = false;
				panel.setWormInstance();
				myState = new Satiated();
				counter_food = 0;

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

	public int getFood_freq() {
		return food_freq;
	}

	public void setFood_freq(int food_freq) {
		this.food_freq = food_freq;
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

	public void Clone(Fish x) {

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
		this.counter_food = 0;

		myState = new Satiated();
		timer = new Thread(this);
		timer.start();
	}
}
