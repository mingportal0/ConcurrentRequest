package com.ConcurrentRequest.car;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.Comment;

import com.ConcurrentRequest.common.PageObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FamilyCarDto {

	private Long id;
	private String type;
	private String stdDate;
	private String endDate;
	private String provided;
	private String cDate;
}
