package com.lms.service;

import java.util.List;

public interface CommonService<T> {

    public T add(T t);

    public List<T> getAll();

    public T update (Long id,T t);

    public void delete (Long id);

    public T getById(Long id);

    public List<T> getByName(String name);

}
