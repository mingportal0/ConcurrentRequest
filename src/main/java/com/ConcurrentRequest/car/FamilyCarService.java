package com.ConcurrentRequest.car;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamilyCarService {

	private final FamilyCarRepository repository;
	private final JdbcTemplate jdbcTemplate;
	
	/*
	 * 패밀리카 저장
	 */
	public void insert(FamilyCar car) throws Exception {
		//기존 예약된 패밀리카가 있는지 체크
		if(!checkAvailibleCar(car)) {
			throw new Exception("기존에 예약된 패밀리카가 있어서 예약을 할 수 없습니다.");
		}
		
		//10초 지연
		Thread.sleep(10000);
		
		Long savedId = getSaveId();
		car.setId(savedId);
		car.setCDate(new Date());
		
		repository.save(car);
	}
	
	/*
	 * ID 채번
	 */
	public Long getSaveId() {
		return jdbcTemplate.queryForObject("SELECT COUNT(1) + 1 AS SAVEDID FROM FAMILY_CAR", Long.class);
	}
	
	/*
	 * 남은 패밀리카가 있는지 체크
	 */
	public boolean checkAvailibleCar(FamilyCar car) {
		boolean isAvailible = false;
		
		int cntCar = jdbcTemplate.queryForObject("SELECT count(1) AS ISCAR FROM FAMILY_CAR WHERE END_DATE >= '"
		+ car.getStdDate() 
		+ "'  AND STD_DATE<= '" 
		+ car.getEndDate() + "' ", Integer.class);
		
		System.out.println("### checkAvailibleCar cntCar : " + cntCar);
		
		if(cntCar == 0) {
			isAvailible = true;
		}
		
		return isAvailible;
	}
}
