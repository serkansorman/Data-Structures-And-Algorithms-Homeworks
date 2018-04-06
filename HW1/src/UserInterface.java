import java.io.IOException;

/**
 * This interface will use for hotel management system users.
 * Every user has common specifications. We will enforce users to override this methods
 */
public interface UserInterface {

    /**
     * Reserve a room
     * @param hotel holds room
     * @return true if book successfully,false otherwise
     * @throws IOException
     */
     boolean book(Hotel hotel) throws IOException;

    /**
     * Cancel reservation
     * @param hotel
     * @return true if cancel successfully,false otherwise
     * @throws IOException
     */
     boolean cancel(Hotel hotel) throws IOException;
}
