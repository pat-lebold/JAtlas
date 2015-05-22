/**
 * 
 */
package lebold.jatlas.query;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public abstract class AQuery implements IQuery {

    private String name;
    
    public AQuery(String name){
	this.name = name;
    }
    
    @Override
    public String toString(){
	return "Query [$: " + this.name + "]";
    }

}
