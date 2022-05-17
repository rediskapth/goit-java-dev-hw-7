package ua.goit.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Repository<T> {

    void save(T t);

    Optional<T> findById(int id);

    List<T> findByIds(Set<Integer> id);

    List<T> findAll();

    void update(T t);

    void remove(T t);
}
