import bagel.Image;

/** This class contains all the common components of all actors in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public abstract class Actor implements Comparable<Actor> {

    // constants
    private static final int NA_ROW_NUMBER = -1;

    // variables
    /** The tile which the actor object is on.
     */
    protected Tile location;

    /** The image of the actor object.
     */
    protected final Image image;

    /** The exact type of the actor object.
     */
    public final String type;

    /** The row number which the actor object is on in the world file, equals -1 if row number is not needed.
     */
    protected int rowNumOnWorldFile;

    // constructors
    /** This constructor creates a new object of an actor, along with the row number this object is on in the world file.
     * @param fileName The file which contains the image of the actor object.
     * @param type The type of the new actor object.
     * @param location The tile which the new object will be on.
     * @param rowNumOnWorldFile The row number this object is on in the world file.
     */
    protected Actor(String fileName, String type, Tile location, int rowNumOnWorldFile) {
        this.image = new Image(fileName);
        this.type = type;
        this.location = location;
        this.rowNumOnWorldFile = rowNumOnWorldFile;
    }

    /** This constructor creates a new object of an actor.
     * @param fileName The file which contains the image of the actor object.
     * @param type The type of the new actor object.
     * @param location The tile which the new object will be on.
     */
    protected Actor(String fileName, String type, Tile location) {
        this.image = new Image(fileName);
        this.type = type;
        this.location = location;
        this.rowNumOnWorldFile = NA_ROW_NUMBER;
    }

    /** This method draws the given object into gui of the world it belongs to.
     */
    public void draw() {
        image.drawFromTopLeft(location.x, location.y);
    }

    /** The action which the actor object performs every tick. Default to stationary and does nothing.
     * @param world The world which this actor object is in.
     */
    public void action(ShadowLife world) {}

    /** Compares how this object relates another object on the world file, in the order of row number on the world file.
     * @param o The other object to compare this object with.
     * @return int The relationship between this actor and the other actor:
     *      <0 if before the other actor
     *      =0 if on the same row
     *      >0 if after the other actor
     */
    @Override
    public int compareTo(Actor o) {
        return this.rowNumOnWorldFile - o.rowNumOnWorldFile;
    }
}
