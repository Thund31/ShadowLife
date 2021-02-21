import java.lang.Exception;

/** This Exception is threw when the coordinate entered can not snap to a tile / is not a multiple of the tile width.
 * @author Siyang Qiu
 * @version 1.0
 */
public class InvalidTileException extends Exception {

    /** The default constructor that does not store the coordinate entered in the exception message.
     */
    public InvalidTileException() {
        super("The coordinate is not on a tile.");
    }

    /** The constructor that stores the error coordinates entered in the exception message.
     * @param x The x coordinate entered.
     * @param y The y coordinate entered.
     */
    public InvalidTileException(double x, double y) {
        super("The coordinate (" + x + ", " + y + ") is not on a tile.");
    }
}
