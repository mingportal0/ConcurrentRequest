package com.ConcurrentRequest.car;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ConcurrentRequest.car.FamilyCar;
import com.ConcurrentRequest.car.FamilyCarRepository;
import com.ConcurrentRequest.car.FamilyCarService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyCarTest {
	@Autowired
	private FamilyCarRepository repository;
	@Autowired
	private FamilyCarService service;
	
	@Test
	public void insertFamilyCarTest() throws Exception {
		//given
		FamilyCar familyCar = new FamilyCar(1L, "승합차", "2023-02-20", "2023-02-20", "요청자1", new Date());
		service.insert(familyCar);
		
		//when
		FamilyCar savedfamilyCar = repository.findAll().get(0);
		
		//then
		Assert.assertEquals(savedfamilyCar.getStdDate(), "2023-02-20");
		Assert.assertEquals(savedfamilyCar.getEndDate(), "2023-02-20");
		Assert.assertEquals(savedfamilyCar.getProvided(), "요청자1");
	}
}
