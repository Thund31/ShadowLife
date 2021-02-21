/** This class represents down pointing sign actors in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public class SignDown extends Actor {

    // constants
    /** This is the name of this class written in the world file.
     */
    public static final String NAME = "SignDown";
    private static final String FILENAME = "res/images/Down.png";

    // constructors
    /** This constructor creates a new object at the provided tile.
     * @param location tile the new object will be on
     */
    public SignDown(Tile location) {
        super(FILENAME, NAME, location);
    }
}
