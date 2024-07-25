package org.tolking.springcore.dao;

import org.tolking.springcore.exception.UserNotFoundException;

public interface CRUD<T> extends CRU<T> {
    void delete(long id) throws UserNotFoundException;
}
