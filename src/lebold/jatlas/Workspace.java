/**
 * 
 */
package lebold.jatlas;

import lebold.jatlas.file.FileSystem;
import lebold.jatlas.query.QueryEngine;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class Workspace {

    private FileSystem system;
    private QueryEngine queryEngine;
    
    public Workspace(FileSystem system){
	this.system = system;
	this.queryEngine = new QueryEngine(this.system);
    }
    
    public QueryEngine getQueryEngine(){
	return this.queryEngine;
    }
}
