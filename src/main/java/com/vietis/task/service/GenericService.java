package com.vietis.task.service;

import java.util.List;

public interface GenericService<T> {
	
    List<T> findAll();
	
    T save(T t);
	
    T findOne(Integer id);
    
    String delete(Integer id);
    
}
