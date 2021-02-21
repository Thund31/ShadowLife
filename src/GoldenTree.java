/** This class represents golden tree actors in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public class GoldenTree extends FruitStoringActors {
    // constants
    /** This is the name of this class written in the world file.
     */
    public static final String NAME = "GoldenTree";
    private static final String FILENAME = "res/images/gold-tree.png";

    // constructors
    /** This constructor creates a new object at the provided tile.
     * @param location The tile the new object will be on.
     */
    public GoldenTree(Tile location) {
        super(FILENAME, NAME, location);
    }
}
