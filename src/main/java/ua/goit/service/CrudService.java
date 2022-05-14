package ua.goit.service;

import java.util.List;
import java.util.Set;

public interface CrudService<T> {

    void save(T entity);

    T findById(Integer id);

    Set<T> findByIds(Set<Integer> ids);

    List<T> findAll();

    void update(T entity);

    void remove(T entity);
}
