package com.ConcurrentRequest.car;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ConcurrentRequest.common.GridObject;
import com.ConcurrentRequest.common.HttpObject;

import lombok.RequiredArgsConstructor;

@RequestMapping("/familyCar")
@RestController
@RequiredArgsConstructor
public class FamilyCarController {
	
	private final FamilyCarRepository repository;
	private final FamilyCarService service;

	@GetMapping("")
	public ModelAndView page() {
		ModelAndView model = new ModelAndView();
		model.setViewName("familyCar/familyCar");
		return model;
	}
	
	@PostMapping("")
	public String insert(@RequestBody FamilyCar familyCar) throws Exception {
		System.out.println("### insert familyCar Param : " + familyCar);
		service.insert(familyCar);
		
		
		return "familyCar/familyCar";
	}
	
	@GetMapping("/listAll")
	public HttpObject listAll() {
		System.out.println("### listAll familyCar");
		List<FamilyCar> contents = repository.findAll();
		List<FamilyCarDto> list = new ArrayList<FamilyCarDto>();
		contents.forEach(familyCar -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			FamilyCarDto dto = new FamilyCarDto(
					familyCar.getId(), 
					familyCar.getType(), 
					familyCar.getStdDate(), 
					familyCar.getEndDate(), 
					familyCar.getProvided(), 
					sdf.format(familyCar.getCDate()));
			list.add(dto);
		});
		
		GridObject gridObject = new GridObject();
		gridObject.setContents(list);
		
		HttpObject httpObject = new HttpObject();
		httpObject.setResult(true);
		httpObject.setData(gridObject);
		
		return httpObject;
	}
	
	@PostMapping("/deleteAll")
	public void deleteAll() throws Exception {
		System.out.println("### deleteAll familyCar");
		repository.deleteAll();
		
	}
}
