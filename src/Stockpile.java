import bagel.Font;

/** This class represents Stockpile actors in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public class Stockpile extends FruitStoringActors {
    // constants
    /** This is the name of this class written in the world file.
     */
    public static final String NAME = "Stockpile";
    private static final String FILENAME = "res/images/cherries.png";
    private static final int INITIAL_FRUITS = 0;
    private final Font font = new Font("res/VeraMono.ttf", 24);

    // constructors
    /** This constructor creates a new object at the provided tile, with the row number this object is on
     * in the world file.
     * @param location The tile the new object will be on.
     * @param rowNum The row number this object is on in the world file.
     */
    public Stockpile(Tile location, int rowNum) {
        super(FILENAME, NAME, location, INITIAL_FRUITS, rowNum);
    }

    /** This method adds 1 fruit from the current object.
     */
    @Override
    public void addFruit() {
        this.numFruits++;
    }

    /** This method removes 1 fruit from the current object.
     */
    @Override
    public void removeFruit() {
        this.numFruits--;
    }

    /** This method draws the given object into gui of the world it belongs to, along with the number of fruits it
     * has.
     */
    @Override
    public void draw() {
        super.draw();
        font.drawString(Integer.toString(numFruits), location.x, location.y);
    }
}
