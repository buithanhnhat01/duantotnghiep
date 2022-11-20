package com.fptpoly.main.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptpoly.main.Dao.AccessoriesRepository;
import com.fptpoly.main.Entity.Accessories;
import com.fptpoly.main.service.AccessoriesService;

@Service
public class AccessoriesServiceImpl implements AccessoriesService{
	
	@Autowired 
	AccessoriesRepository accessoriesRepository;

	@Override
	public List<Accessories> getAllAccessories() {
		return accessoriesRepository.findAll();
	}

	@Override
	public void saveAccessories(Accessories accessories) {
		this.accessoriesRepository.save(accessories);
	}

	@Override
	public Accessories getAccessoriesByMaLk(String malk) {
		Optional<Accessories> optional = accessoriesRepository.findById(malk);
		Accessories accessories = null;
		if (optional.isPresent()) {
			accessories = optional.get();
		}else {
			throw new RuntimeException("Không tìm thấy mã linh kiện: :" + malk);
		}
		
		return accessories;
	}

	@Override
	public void deleteAccessories(String malk) {
		this.accessoriesRepository.deleteById(malk);
		
	}

}
