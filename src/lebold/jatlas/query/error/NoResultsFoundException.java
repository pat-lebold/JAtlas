/**
 * 
 */
package lebold.jatlas.query.error;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class NoResultsFoundException extends Exception {
    private static final long serialVersionUID = 423697096035290461L;
    public NoResultsFoundException(String message){
	super(message);
    }
}
