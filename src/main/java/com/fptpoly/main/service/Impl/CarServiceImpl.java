package com.fptpoly.main.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptpoly.main.Dao.BrandRepository;
import com.fptpoly.main.Dao.CarRepository;
import com.fptpoly.main.Entity.Brand;
import com.fptpoly.main.Entity.Car;
import com.fptpoly.main.service.CarService;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired 
	CarRepository carRepository;
	@Autowired 
	BrandRepository brandRepository;

	@Override
	public List<Car> getAllCars() {
		
		return carRepository.findAll();
	}

	@Override
	public void saveCars(Car car) {
		this.carRepository.save(car);
	}

	@Override
	public Car getCarById(String id) {
		Optional<Car> optional = carRepository.findById(id);
		Car car = null;
		if (optional.isPresent()) {
			car = optional.get();
		}else {
			throw new RuntimeException("Không tìm thấy id: :" + id);	
		}
		
		return car;
	}

	@Override
	public void deleteCar(String id) {
		this.carRepository.deleteById(id);
		
	}

}
