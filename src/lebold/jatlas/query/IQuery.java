/**
 * 
 */
package lebold.jatlas.query;

import lebold.jatlas.FileSystem;

/**
 * JAtlas
 *
 * @author Patrick Lebold
 *
 */
public interface IQuery {

    public IQueryResult<?> execute(FileSystem system, String queryString);
    public boolean isApplicable(String queryString);
}