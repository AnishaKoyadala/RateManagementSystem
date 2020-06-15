package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.model.Rate;

public interface RateService {
	List<Rate> findAll();
//	public Page<Rate> findAllProducts(Integer page, Integer size);
//	public Page<Rate> getSearchProducts(String name, Integer page, Integer size);
//	Page<Product> listAllByPage(Pageable pageable);

	Optional<Rate> findById(Integer id);
	Rate insert(Rate product);
	Rate edit(Rate product);
    void deleteById(Integer id);	
    
    
}



