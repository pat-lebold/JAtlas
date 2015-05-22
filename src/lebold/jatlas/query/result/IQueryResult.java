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
public interface IQueryResult<T> {

    public T getResult();
    public IQuery getQuery();
}
