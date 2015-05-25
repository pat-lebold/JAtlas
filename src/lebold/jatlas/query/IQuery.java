/**
 * 
 */
package lebold.jatlas.query;

import lebold.jatlas.file.FileSystem;
import lebold.jatlas.query.error.NoResultsFoundException;
import lebold.jatlas.query.result.IQueryResult;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public interface IQuery {

    public IQueryResult<?> execute(FileSystem system, String queryString) throws NoResultsFoundException;
    public boolean isApplicable(String queryString);
}