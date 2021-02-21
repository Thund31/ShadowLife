import bagel.*;
import bagel.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/** This class creates and runs a game of Shadow life. Currently only runs 1 world at a time.
 * @author Siyang Qiu
 * @version 1.0
 */
public class ShadowLife extends AbstractGame {
    // constants
    private static final String[] ACTOR_NAMES = new String[]{Tree.NAME, GoldenTree.NAME, Stockpile.NAME, Hoard.NAME, Pad.NAME,
            Fence.NAME, SignLeft.NAME, SignRight.NAME, SignUp.NAME, SignDown.NAME, MitosisPool.NAME, Gatherer.NAME, Thief.NAME};
    private static final int WINDOW_WIDTH = 1024, WINDOW_HEIGHT = 768;
    private static final Tile ORIGIN = new Tile();
    private static final int INITIAL_VALUE = 0, NONE = 0, FAILURE = -1, SUCCESS = 0;
    private static final int TYPE = 0, X_COORDINATE = 1, Y_COORDINATE = 2;
    private static final int NUM_OF_ARGS = 3, TICK_RATE = 0, MAX_NUM_TICKS = 1, WORLD_FILE = 2;
    private static final String BG_IMAGE = "res/images/background.png";

    // static variables
    private static long tickTime;
    private static int tickRate, maxNumTicks, tickCount;
    private static String worldFile;

    // instance variables
    private final Image backgroundImage;
    private final HashMap<String, ArrayList<Actor>> actors = new HashMap<>();
    private final ArrayList<Actor> actorsToAddBuffer = new ArrayList<>();
    private final ArrayList<Actor> actorsToRemoveBuffer = new ArrayList<>();

    // create a new world

    /** Creates a new Shadow life world.
     * @param worldFile The world file of the world that need to be created.
     */
    public ShadowLife(String worldFile) {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, "Shadow Life");
        backgroundImage = new Image(BG_IMAGE);

        // initialise actors hashmap
        for (String actorName : ACTOR_NAMES) actors.put(actorName, new ArrayList<>());

        // read CSV file and initialise actors
        try (BufferedReader br = new BufferedReader(new FileReader(worldFile))) {
            String line;
            int lineNumber = INITIAL_VALUE;

            while ((line = br.readLine()) != null) {
                String actorType;
                int x, y;
                lineNumber++;

                // read new line
                try {
                    String[] cells = line.split(",");
                    actorType = cells[TYPE];
                    x = Integer.parseInt(cells[X_COORDINATE]);
                    y = Integer.parseInt(cells[Y_COORDINATE]);

                    // add new actor to actors list
                    newActor(actorType, x, y, lineNumber);

                } catch (Exception e) {
                    System.out.println("error: in file \"" + worldFile + "\" at line " + lineNumber);
                    System.exit(FAILURE);
                }
            }
        } catch (IOException e) {
            System.out.println("error: file \"" + worldFile + "\" not found");
            System.exit(FAILURE);
        }

        // initialise tick timer
        tickTime = System.currentTimeMillis();
        tickCount = INITIAL_VALUE;
    }

    /** Entry point of the game.
     * @param args Config information to the game:
     *             <tick rate> <max number of ticks> <path of the world file>
     */
    public static void main(String[] args) {

        // read command line arguments
        try {
            // confirm the number of command line arguments entered is as expected
            checkNumArgs(args);

            // store information in appropriate variables
            tickRate = Integer.parseUnsignedInt(args[TICK_RATE]);
            maxNumTicks = Integer.parseUnsignedInt(args[MAX_NUM_TICKS]);
            worldFile = args[WORLD_FILE];
        } catch (Exception e) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(FAILURE);
        }

        // start game
        ShadowLife game = new ShadowLife(worldFile);
        game.run();
    }

    /** Refreshes the screen and updates the actors every tick.
     * @param input The input from the external devices (mouse and keyboard)
     */
    @Override
    public void update(Input input) {

        // check if time for next tick
        long currentTime = System.currentTimeMillis();
        if (currentTime - tickTime > tickRate) {
            tickTime = currentTime;

            // check if timeout
            if (++tickCount > maxNumTicks) {
                System.out.println("Timed out");
                System.exit(FAILURE);
            }

            // perform actions for actors
            updateAllActors();

            // end the game
            if (checkGameEnd()) endGame();
        }

        // render everything onto screen
        drawAll();
    }

    private static void checkNumArgs(String[] args) throws IllegalArgumentException {
        if (args.length != NUM_OF_ARGS) throw new IllegalArgumentException();
    }

    /** update all actors in the current world
     */
    private void updateAllActors() {
        for (String actorType: actors.keySet()) {
            for (Actor actor: actors.get(actorType)) {
                actor.action(this);
            }

            // add all actors that need to be added
            if (actorsToAddBuffer.size() > NONE) {
                for (Actor actor : actorsToAddBuffer) {actors.get(actorType).add(actor);}
                actorsToAddBuffer.clear();
            }

            // remove all actors that need to be removed
            if (actorsToRemoveBuffer.size() > NONE) {
                for (Actor actor : actorsToRemoveBuffer) {actors.get(actorType).remove(actor);}
                actorsToRemoveBuffer.clear();
            }
        }
    }

    /** draw background and actors to the gui of the game
     */
    private void drawAll() {
        backgroundImage.drawFromTopLeft(ORIGIN.x, ORIGIN.y);
        for (String actorType: ACTOR_NAMES) {
            for (Actor actor: actors.get(actorType)) {
                actor.draw();
            }
        }
    }

    /** Check if all gatherers and thieves are inactive, return true if game need to end
     * @return boolean True if game need to be ended.
     */
    private boolean checkGameEnd() {
        for (Actor actor: actors.get(Gatherer.NAME)) {
            Gatherer gatherer = (Gatherer)actor;
            if (gatherer.isActive()) return false;
        }

        for (Actor actor: actors.get(Thief.NAME)) {
            Thief thief = (Thief)actor;
            if (thief.isActive()) return false;
        }
        return true;
    }

    /** Last actions to complete and finish/close the game.
     */
    private void endGame() {
        // print ticks passed
        System.out.println(tickCount + " ticks");

        // sort Stockpiles and Hoards in order of world file
        ArrayList<Actor> listStockHoards = actors.get(Stockpile.NAME);
        listStockHoards.addAll(actors.get(Hoard.NAME));
        Collections.sort(listStockHoards);

        // print numFruit for all stockpiles and hoards
        for (Actor actor: listStockHoards) {
            FruitStoringActors fruitStoringActors = (FruitStoringActors)actor;
            System.out.println(fruitStoringActors.getNumFruits());
        }
        System.exit(SUCCESS);
    }

    /** Remove an actor from the current world.
     * @param actor The actor to be removed.
     */
    public void removeActor(Actor actor) {
        actorsToRemoveBuffer.add(actor);
    }

    /** Add a new actor to the current world.
     * @param actor The actor to the added.
     */
    public void addActor(Actor actor) { actorsToAddBuffer.add(actor); }

    private void newActor(String type, int x, int y, int rowNum) throws InvalidTileException,
            InvalidActorTypeException {
        Tile location = new Tile(x, y);
        Actor newActor;
        switch (type) {
            case Tree.NAME:
                newActor = new Tree(location);
                break;
            case GoldenTree.NAME:
                newActor =  new GoldenTree(location);
                break;
            case Stockpile.NAME:
                newActor =  new Stockpile(location, rowNum);
                break;
            case Hoard.NAME:
                newActor =  new Hoard(location, rowNum);
                break;
            case Pad.NAME:
                newActor =  new Pad(location);
                break;
            case Fence.NAME:
                newActor =  new Fence(location);
                break;
            case SignLeft.NAME:
                newActor =  new SignLeft(location);
                break;
            case SignRight.NAME:
                newActor =  new SignRight(location);
                break;
            case SignUp.NAME:
                newActor =  new SignUp(location);
                break;
            case SignDown.NAME:
                newActor =  new SignDown(location);
                break;
            case MitosisPool.NAME:
                newActor =  new MitosisPool(location);
                break;
            case Gatherer.NAME:
                newActor =  new Gatherer(location);
                break;
            case Thief.NAME:
                newActor =  new Thief(location);
                break;
            default:
                throw new InvalidActorTypeException();
        }
        actors.get(type).add(newActor);
    }

    /** check if this location has a specific type of actor object
     * @param location The location to check.
     * @param withType The type of actor to check.
     * @return Actor Return the object if found, or null if not found.
     */
    public Actor checkCollision(Tile location, String withType) {
        for (Actor actor: actors.get(withType)) {
            if (actor.location.equals(location)) {
                return actor;
            }
        }
        return null;
    }
}