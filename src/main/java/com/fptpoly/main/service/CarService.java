package com.fptpoly.main.service;

import java.util.List;

import com.fptpoly.main.Entity.Brand;
import com.fptpoly.main.Entity.Car;

public interface CarService {
	List<Car> getAllCars();
	void saveCars(Car car);
	Car getCarById(String id);
	void deleteCar(String id);
}
