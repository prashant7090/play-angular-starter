package models.dao;

/**
 * Created by prashant on 21/8/16.
 */
public interface Dao<K,T> {
    T findById(K id);
    void delete(T obj);
    void save(T obj);
}
