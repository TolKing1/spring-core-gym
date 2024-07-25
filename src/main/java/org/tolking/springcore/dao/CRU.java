package org.tolking.springcore.dao;

import org.tolking.springcore.exception.UserNotFoundException;

public interface CRU<T> extends CR<T>{
    void update(T entity) throws UserNotFoundException;
}
