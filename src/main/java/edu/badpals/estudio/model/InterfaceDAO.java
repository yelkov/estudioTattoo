package edu.badpals.estudio.model;

import java.util.Optional;

public interface InterfaceDAO<T> {
    void create(T t);
    void update(T t);
    void delete(T t);
    Optional<T> findById(int id);
}
