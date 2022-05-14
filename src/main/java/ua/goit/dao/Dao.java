package ua.goit.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Dao<T> {

    void save(T entity);

    Optional<T> findById(int id);

    List<T> findByIds(Set<Integer> id);

    List<T> findAll();

    void update(T entity);

    void remove(T entity);
}
