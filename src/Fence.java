/** This class represents a Fence actor in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public class Fence extends Actor {
    // constants
    /** This is the name of this class written in the world file.
     */
    public static final String NAME = "Fence";
    private static final String FILENAME = "res/images/fence.png";

    // constructors
    /** This constructor creates a new object at the provided tile.
     * @param location The tile the new object will be on
     */
    public Fence(Tile location) {
        super(FILENAME, NAME, location);
    }
}
