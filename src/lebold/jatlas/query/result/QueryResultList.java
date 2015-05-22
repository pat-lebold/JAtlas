/**
 * 
 */
package lebold.jatlas.query.result;

import java.util.List;

import lebold.jatlas.query.IQuery;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class QueryResultList<T extends List<R>,R> extends AQueryResult<T> {
    
    /**
     * @param query, result
     */
    public QueryResultList(IQuery query,T result) {
	super(query,result);
    }
    
    public boolean contains(R object){
	for(R type:super.getResult()){
	    if(type.equals(object))
		return true;
	}
	return false;
    }
    
    public boolean isEmpty(){
	return super.getResult().isEmpty();
    }

}
