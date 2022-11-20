package com.fptpoly.main.service;

import java.util.List;

import com.fptpoly.main.Entity.Accessories;

public interface AccessoriesService {
	List<Accessories> getAllAccessories();
	void saveAccessories(Accessories accessories);
	Accessories getAccessoriesByMaLk(String malk);
	void deleteAccessories(String malk);
}
