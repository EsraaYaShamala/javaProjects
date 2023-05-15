package estore.dao;

import estore.models.Product;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(Integer id);
    
    List<T> getAll();
    
    void save(T t);
    
    T update(T t);
    
    void delete(T t);
    
    Optional<T> get( String name);
}
