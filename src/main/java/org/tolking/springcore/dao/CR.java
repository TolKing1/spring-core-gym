package org.tolking.springcore.dao;

import java.util.List;

public interface CR<T>{
    List<T> getAll();
    void create(T entity) throws NullPointerException;
}
