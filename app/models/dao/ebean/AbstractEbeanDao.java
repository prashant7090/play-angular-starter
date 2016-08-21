package models.dao.ebean;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model.Finder;
import models.dao.Dao;

/**
 * Created by prashant on 21/8/16.
 */
public class AbstractEbeanDao<K, T> implements Dao<K, T> {

    protected final Finder<K, T> finder;

    protected AbstractEbeanDao(Finder<K, T> finder) {
        this.finder = finder;
    }

    @Override
    public final T findById(K id) {
        return finder.byId(id);
    }

    @Override
    public final void delete(T obj) {
        Ebean.delete(obj);
    }

    @Override
    public final void save(T obj) {
        Ebean.save(obj);
    }
}
