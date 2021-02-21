/** This class represents Gatherer actors in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public class Gatherer extends Actor implements Movable {
    // constants
    /** This is the name of this class written in the world file.
     */
    public static final String NAME = "Gatherer";
    private static final String FILENAME = "res/images/gatherer.png";
    private static final int POSITIVE_BY_1 = 1, NEGATIVE_BY_1 = -1, NONE = 0;

    // variables
    private String direction;
    private boolean carrying, active;

    // constructors

    /** This constructor creates a new object with default direction at the provided tile.
     * @param location The tile the object is on.
     */
    public Gatherer(Tile location) {
        super(FILENAME, NAME, location);
        this.direction = DIRECTIONS[0];
        this.carrying = false;
        this.active = true;
    }

    /** This constructor creates a new object with set direction.
     * @param location The tile the object is on.
     * @param direction The direction which the new object is facing.
     */
    public Gatherer(Tile location, String direction) {
        super(FILENAME, NAME, location);
        this.direction = direction;
        this.carrying = false;
        this.active = true;
    }

    /** Getter to check if this object is active.
     * @return boolean If object is active.
     */
    public boolean isActive() {
        return active;
    }

    /** Perform the action of this object every tick in specific set of logic
     * @param world The Shadow life world which this object is in.
     */
    @Override
    public void action(ShadowLife world) {

        // check if active
        if (!active) return;
        Tile oldPosition = this.location;
        move();

        // check if standing on tree
        if (world.checkCollision(location, Fence.NAME) != null) {
            active = false;
            this.location = oldPosition;
        }

        // check if on pool
        if (world.checkCollision(location, MitosisPool.NAME) != null) {

            // create first actor
            Gatherer firstActor = new Gatherer(this.location, turnAntiClockwise(this.direction));
            firstActor.move();
            world.addActor(firstActor);

            // create second actor
            Gatherer secActor = new Gatherer(this.location, turnClockwise(this.direction));
            secActor.move();
            world.addActor(secActor);

            // remove current actor
            world.removeActor(this);
            return;
        }

        // check if on sign
        if (world.checkCollision(location, SignLeft.NAME) != null) this.direction = "left";
        else if (world.checkCollision(location, SignUp.NAME) != null) this.direction = "up";
        else if (world.checkCollision(location, SignRight.NAME) != null) this.direction = "right";
        else if (world.checkCollision(location, SignDown.NAME) != null) this.direction = "down";

        // check if on tree
        Tree tree = (Tree)world.checkCollision(location, Tree.NAME);
        if (tree != null && !carrying) {
            if (tree.getNumFruits() > NONE) {
                tree.removeFruit();
                carrying = true;
                this.direction = turnAround(this.direction);
            }
        }

        // check if on golden tree
        GoldenTree goldenTree = (GoldenTree) world.checkCollision(location, GoldenTree.NAME);
        if (goldenTree != null && !carrying) {
            carrying = true;
            this.direction = turnAround(this.direction);
        }

        // check if on hoard
        Hoard hoard = (Hoard) world.checkCollision(location, Hoard.NAME);
        if (hoard != null) {
            if (carrying) {
                carrying = false;
                hoard.addFruit();
            }
            this.direction = turnAround(this.direction);
        }


        // check if on stockpile
        Stockpile stockpile = (Stockpile) world.checkCollision(location, Stockpile.NAME);
        if (stockpile != null) {
            if (carrying) {
                carrying = false;
                stockpile.addFruit();
            }
            this.direction = turnAround(this.direction);
        }
    }

    /** move the gatherer by 1 tile in the direction this object is facing now.
     */
    public void move() {
        switch (direction) {
            case "right":
                location = Tile.moveInX(location, POSITIVE_BY_1);
                break;
            case "left":
                location = Tile.moveInX(location, NEGATIVE_BY_1);
                break;
            case "down":
                location = Tile.moveInY(location, POSITIVE_BY_1);
                break;
            case "up":
                location = Tile.moveInY(location, NEGATIVE_BY_1);
                break;
        }
    }
}
