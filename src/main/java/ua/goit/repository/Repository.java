package ua.goit.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void save(T t);

    Optional<T> findById(int id);

    List<T> findAll();

    void update(T t);

    void remove(T t);
}
