package q3;

public class Singleton {
    private static Singleton instance = null;

    /*
     * A private Constructor prevents any other
     * class from instantiating.
     */
    private Singleton() {
    }

    /* Static 'instance' method */
    public static Singleton getInstance() {
        if (instance == null)
            System.out.println("Singleton callback");
        instance = new Singleton();

        return instance;
    }

    // set function to reset the instance after the fish ate the worm
    public static void set() {
        if (instance != null) {
            instance = null;
        }
    }
}
