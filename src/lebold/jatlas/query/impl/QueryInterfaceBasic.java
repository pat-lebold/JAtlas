/**
 * 
 */
package lebold.jatlas.query.impl;

import java.util.LinkedList;
import java.util.List;

import lebold.jatlas.file.Directory;
import lebold.jatlas.file.FileSystem;
import lebold.jatlas.query.AQuery;
import lebold.jatlas.query.QueryParser;
import lebold.jatlas.query.error.NoResultsFoundException;
import lebold.jatlas.query.result.IQueryResult;
import lebold.jatlas.query.result.QueryResultList;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class QueryInterfaceBasic extends AQuery {

    public QueryInterfaceBasic(){
	super("INTERFACE <interface name>");
    }
    
    /**
     * @see lebold.jatlas.query.IQuery#execute(lebold.jatlas.file.FileSystem, java.lang.String)
     */
    @Override
    public IQueryResult<List<Class<?>>> execute(FileSystem system, String queryString) throws NoResultsFoundException{
	String[] splitQuery = QueryParser.splitQuery(queryString);
	String className = splitQuery[1];

	Directory root = system.getRootDirectory();
	List<Class<?>> applicableClasses = this.pullApplicableClassesFromTree(root,className);
	if(applicableClasses.size()==0)
	    throw new NoResultsFoundException("No results found!");
	return new QueryResultList<List<Class<?>>,Class<?>>(this,applicableClasses);
    }

    /**
     * @see lebold.jatlas.query.IQuery#isApplicable(java.lang.String)
     */
    @Override
    public boolean isApplicable(String queryString) {
	String[] splitQuery = QueryParser.splitQuery(queryString);
	if(splitQuery.length == 2){
	    String query = splitQuery[0];
	    if(query.equalsIgnoreCase("INTERFACE")){
		String className = splitQuery[1];
		return QueryParser.isClassFormat(className);
	    }
	    else
		return false;
	}
	else
	    return false;
    }

    private List<Class<?>> pullApplicableClassesFromTree(Directory dir, String className){
	List<Class<?>> applicableClasses = new LinkedList<Class<?>>();

	applicableClasses.addAll(this.pullApplicableClassesFromDir(dir,className));
	for(Directory subdir: dir.getSubDirectories()){
	    List<Class<?>> classes = this.pullApplicableClassesFromTree(subdir,className);
	    applicableClasses.addAll(classes);
	}
	return applicableClasses;
    }

    private List<Class<?>> pullApplicableClassesFromDir(Directory dir, String className){
	List<Class<?>> applicableClasses = new LinkedList<Class<?>>();

	for(Class<?> type: dir.getClasses()){
	    if(type.isInterface()){
		if(className.equals(type.getName()))
		    applicableClasses.add(0,type);
		if(type.getName().contains(className))
		    applicableClasses.add(type);
	    }
	}
	return applicableClasses;
    }
}
