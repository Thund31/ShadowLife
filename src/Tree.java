import bagel.Font;

/** This class represents Tree actors in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public class Tree extends FruitStoringActors {

    // constants
    /** This is the name of this class written in the world file.
     */
    public static final String NAME = "Tree";
    private static final String FILENAME = "res/images/tree.png";
    private static final int INITIAL_FRUIT = 3;
    private static final Font font = new Font("res/VeraMono.ttf", 24);

    // constructors

    /** This constructor creates a new object at the provided tile.
     * @param location The tile the new object will be on
     */
    public Tree(Tile location) {
        super(FILENAME, NAME, location, INITIAL_FRUIT);
    }

    /** This method removes 1 fruit from the current object.
     */
    @Override
    public void removeFruit() {
        numFruits--;
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
