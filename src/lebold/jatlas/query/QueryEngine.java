/**
 * 
 */
package lebold.jatlas.query;

import java.util.ArrayList;
import java.util.List;

import lebold.jatlas.file.FileSystem;
import lebold.jatlas.query.error.NoResultsFoundException;
import lebold.jatlas.query.impl.QueryClassBasic;
import lebold.jatlas.query.impl.QueryClassExplicit;
import lebold.jatlas.query.result.IQueryResult;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class QueryEngine {

    private FileSystem system;
    private List<IQuery> queryList;
    
    public QueryEngine(FileSystem system){
	this.system = system;
	this.queryList = new ArrayList<IQuery>();
	this.initializeQueries();
    }
    
    public IQueryResult<?> executeQuery(String queryString) throws NoResultsFoundException{
	for(IQuery query: this.queryList){
	    if(query.isApplicable(queryString)){
    		return query.execute(this.system,queryString);
	    }
	}
	return null;
    }
    
    public boolean isQueryValid(String queryString){
	for(IQuery query: this.queryList){
	    if(query.isApplicable(queryString)){
		return true;
	    }
	}
	return false;
    }
    
    private void initializeQueries(){
	this.queryList.add(new QueryClassBasic());
	this.queryList.add(new QueryClassExplicit());
    }
}
