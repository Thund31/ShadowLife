/** This abstract class contains all components common in all actors that can store fruits in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public abstract class FruitStoringActors extends Actor {

    /** This is the number of fruits that the fruit-storing actor object is keeping.
     */
    protected int numFruits;

    /** This constructor creates a new object of actor-type which can store infinity number of fruits.
     * @param fileName The file which contains the image of the actor object.
     * @param type The type of the new actor object.
     * @param location The tile which the new object will be on.
     */
    public FruitStoringActors(String fileName, String type, Tile location) {
        super(fileName, type, location);
        this.numFruits = -1;
    }

    /** This constructor creates a new object of actor-type which can store a set number of fruits.
     * @param fileName The file which contains the image of the actor object.
     * @param type The type of the new actor object.
     * @param location The tile which the new object will be on.
     * @param numFruits The number of fruits initially in the object.
     */
    public FruitStoringActors(String fileName, String type, Tile location, int numFruits) {
        super(fileName, type, location);
        this.numFruits = numFruits;
    }

    /** This constructor creates a new object of actor-type which can store a set number of fruits, along with the
     * row number this object is on in the world file.
     * @param fileName The file which contains the image of the actor object.
     * @param type The type of the new actor object.
     * @param location The tile which the new object will be on.
     * @param numFruits The number of fruits initially in the object.
     * @param rowNum The row number this object is on in the world file.
     */
    public FruitStoringActors(String fileName, String type, Tile location, int numFruits, int rowNum) {
        super(fileName, type, location, rowNum);
        this.numFruits = numFruits;
    }

    /** Getter for the number of fruits this object is keeping currently.
     * @return int Getter for the number of fruits in this object.
     */
    public int getNumFruits() {
        return numFruits;
    }

    /** The method to remove fruits in this object.
     * Currently does nothing when called. Need to override if there is remove-fruit logic in the
     * child class.
     */
    public void removeFruit() {}

    /** The method to add fruits in this object.
     * Currently does nothing when called. Need to override if there is add-fruit logic in the
     * child class.
     */
    public void addFruit() {}
}
