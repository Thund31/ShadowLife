import java.util.Arrays;

/** This interface includes logic and components of all actors that are movable in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public interface Movable {

    /** This is all the directions that an actor can point at in Shadow life game.
     */
    String[] DIRECTIONS = {"left", "up", "right", "down"};

    /** Move the movable actor with some logic.
     */
    void move();

    /** Check if the movable actor is active or not.
     */
    boolean isActive();

    /** Turn the movable actor clockwise by 90 degrees.
     * @param inDirection The direction that the movable actor was pointing at
     * @return String The direction which the movable actor points at after rotation.
     */
    default String turnClockwise(String inDirection) {
        int direction = Arrays.asList(DIRECTIONS).indexOf(inDirection);
        return DIRECTIONS[(direction + 1) % 4];
    }

    /** Turn the movable actor anti-clockwise by 90 degrees.
     * @param inDirection The direction that the movable actor was pointing at
     * @return String The direction which the movable actor points at after rotation.
     */
    default String turnAntiClockwise(String inDirection) {
        int direction = Arrays.asList(DIRECTIONS).indexOf(inDirection);
        return DIRECTIONS[(direction + 3) % 4];
    }

    /** Turn the movable actor by 180 degrees.
     * @param inDirection The direction that the movable actor was pointing at
     * @return String The direction which the movable actor points at after rotation.
     */
    default String turnAround(String inDirection) {
        int direction = Arrays.asList(DIRECTIONS).indexOf(inDirection);
        return DIRECTIONS[(direction + 2)%4];
    }
}
