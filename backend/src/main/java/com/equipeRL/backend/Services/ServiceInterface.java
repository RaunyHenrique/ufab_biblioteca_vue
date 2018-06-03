package com.equipeRL.backend.Services;

import java.util.List;

public interface ServiceInterface<T> {

    List<T> getAll();
    boolean isExist(T model);
    void save(T model);
    T findById(long id);
    void update(T model);
    void deleteById(long id);

}
