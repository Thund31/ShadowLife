import bagel.util.Point;

/** This class represents a tile in the Shadow life world.
 * @author Siyang Qiu
 * @version 1.0
 */
public class Tile extends Point {
    private static final int TILE_WIDTH = 64, ON_THE_TILE = 0;

    // constructors
    /** Creates a new tile at the origin. (0, 0)
     */
    public Tile() {
        super();
    }

    /** Creates a new tile at the given x, y coordinate.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Tile(double x, double y) throws InvalidTileException {
        super(x, y);
        if (x%TILE_WIDTH != ON_THE_TILE || y%TILE_WIDTH != ON_THE_TILE) throw new InvalidTileException();
    }

    /** Move an actor across an amount of tiles in the x direction.
     * @param oldTile The original tile before moving.
     * @param tilesToMove Amount of tiles to move.
     * @return Tile The new tile that the actor will be on after moving.
     */
    public static Tile moveInX(Tile oldTile, int tilesToMove) {
        Tile newTile = null;
        try {
            newTile = new Tile(oldTile.x + tilesToMove*TILE_WIDTH, oldTile.y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTile;
    }

    /** Move an actor across an amount of tiles in the y direction.
     * @param oldTile The original tile before moving.
     * @param tilesToMove Amount of tiles to move.
     * @return Tile The new tile that the actor will be on after moving.
     */
    public static Tile moveInY(Tile oldTile, int tilesToMove) {
        Tile newTile = null;
        try {
            newTile = new Tile(oldTile.x, oldTile.y + tilesToMove*TILE_WIDTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTile;
    }

    /** Check if this tile is exactly the same as another tile.
     * @param other Another tile to compare with.
     * @return boolean Weather the two tiles are the same.
     */
    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }
}
