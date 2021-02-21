/** This class represents up pointing sign actors in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public class SignUp extends Actor {
    // constants
    /** This is the name of this class written in the world file.
     */
    public static final String NAME = "SignUp";
    private static final String FILENAME = "res/images/up.png";

    // constructors
    /** This constructor creates a new object at the provided tile.
     * @param location The tile the new object will be on.
     */
    public SignUp(Tile location) {
        super(FILENAME, NAME, location);
    }
}
