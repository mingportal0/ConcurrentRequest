package com.ConcurrentRequest.common;

import java.util.List;

import com.ConcurrentRequest.car.FamilyCarDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GridObject {

	private List<FamilyCarDto> contents;
	private PageObject pagination;
}
