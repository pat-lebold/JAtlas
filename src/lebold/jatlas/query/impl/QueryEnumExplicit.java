/**
 * 
 */
package lebold.jatlas.query.impl;

import lebold.jatlas.file.Directory;
import lebold.jatlas.file.FileSystem;
import lebold.jatlas.query.AQuery;
import lebold.jatlas.query.QueryParser;
import lebold.jatlas.query.error.InvalidDirectoryException;
import lebold.jatlas.query.error.NoResultsFoundException;
import lebold.jatlas.query.result.IQueryResult;
import lebold.jatlas.query.result.QueryResultSingle;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public class QueryEnumExplicit extends AQuery {

    public QueryEnumExplicit() {
	super("ENUM <interface package declaration>");
	// TODO Auto-generated constructor stub
    }

    /**
     * @see lebold.jatlas.query.IQuery#execute(lebold.jatlas.file.FileSystem, java.lang.String)
     */
    @Override
    public IQueryResult<?> execute(FileSystem system, String queryString) throws NoResultsFoundException{
	String[] splitQuery = QueryParser.splitQuery(queryString);
	String[] classDeclaration = QueryParser.splitPackage(splitQuery[1]);
	String[] packageArray = new String[classDeclaration.length-1];
	for(int n=0; n<packageArray.length; n++){
	    packageArray[n] = classDeclaration[n];
	}

	try {
	    Directory packageDir = QueryParser.fetchDirectory(system, packageArray);
	    String className = splitQuery[1];
	    for(Class<?> type: packageDir.getClasses()){
		if(type.getName().equals(className)){
		    if(type.isEnum())
			return new QueryResultSingle<Class<?>>(this,type);
		    else
			throw new NoResultsFoundException("No results found!");
		}
	    }
	    throw new NoResultsFoundException("No results found!");
	}
	catch (InvalidDirectoryException e) {
	    throw new NoResultsFoundException("No results found!");
	}

    }

    /**
     * @see lebold.jatlas.query.IQuery#isApplicable(java.lang.String)
     */
    @Override
    public boolean isApplicable(String queryString) {
	String[] splitQuery = QueryParser.splitQuery(queryString);
	if(splitQuery.length == 2){
	    String command = splitQuery[0];
	    if(command.equalsIgnoreCase("ENUM")){
		String classDeclaration = splitQuery[1];
		boolean isClass = QueryParser.isClassFormat(classDeclaration);
		boolean isPackage = QueryParser.isPackageFormat(classDeclaration);
		return !isClass && isPackage;
	    }
	    else
		return false;
	}
	else
	    return false;
    }

}
