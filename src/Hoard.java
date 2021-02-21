import bagel.Font;

/** This class represents Hoard actors in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public class Hoard extends FruitStoringActors {
    // constants
    /** This is the name of this class written in the world file.
     */
    public static final String NAME = "Hoard";
    private static final String FILENAME = "res/images/hoard.png";
    private static final int INITIAL_FRUITS = 0;
    private final Font font = new Font("res/VeraMono.ttf", 24);

    // constructors
    /** This constructor creates a new object at the provided tile, with the row number this object is on
     * in the world file.
     * @param location tile the new object will be on.
     * @param rowNum the row number this object is on in the world file.
     */
    public Hoard(Tile location, int rowNum) {
        super(FILENAME, NAME, location, INITIAL_FRUITS, rowNum);
    }

    /** This method draws the given object into gui of the world it belongs to, along with the number of fruits it
     * has.
     */
    @Override
    public void draw() {
        super.draw();
        font.drawString(Integer.toString(numFruits), location.x, location.y);
    }

    /** This method adds 1 fruit for the current object.
     */
    @Override
    public void addFruit() {
        numFruits++;
    }

    /** This method removes 1 fruit for the current object.
     */
    @Override
    public void removeFruit() {
        numFruits--;
    }
}
