import java.lang.Exception;

/** This Exception is threw when the actor type to be created can not be recognised.
 * @author Siyang Qiu
 * @version 1.0
 */
public class InvalidActorTypeException extends Exception {

    /** The default constructor that does not store the actor type entered in the exception message.
     */
    public InvalidActorTypeException() {
        super("The actor type can not be recognised.");
    }

    /** The constructor that stores the error actor type entered in the exception message.
     * @param actorType The actor type entered.
     */
    public InvalidActorTypeException(String actorType) {
        super(actorType + " can not be recognised.");
    }
}
