/**
 * 
 */
package lebold.jatlas.query.result;

import lebold.jatlas.query.IQuery;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public abstract class AQueryResult<T> implements IQueryResult<T> {
    
    private IQuery query;
    private T result;
    
    public AQueryResult(IQuery query,T result){
	this.query = query;
	this.result = result;
    }
    
    public T getResult(){
	return this.result;
    }
    
    public IQuery getQuery(){
	return this.query;
    }
}
