package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.learning.model.Rate;
import com.learning.repository.RateRepository;

@Service
@Primary
public class RateServiceJpaImpl implements RateService{

	@Autowired
	RateRepository raterepository;
	@Override
	public List<Rate> findAll() {
		// TODO Auto-generated method stub
		
		
		return this.raterepository.findAll();
	}

	@Override
	public Optional<Rate> findById(Integer id) {
		// TODO Auto-generated method stub
		return this.raterepository.findById(id);
	}

	@Override
	public Rate insert(Rate rate) {
		// TODO Auto-generated method stub
		
		return this.raterepository.save(rate);

	}

	@Override
	public Rate edit(Rate rate) {
		
		return this.raterepository.save(rate);
		}



	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		this.raterepository.deleteById(id);
	}

}
