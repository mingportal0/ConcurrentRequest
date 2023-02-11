package com.ConcurrentRequest.car;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
		@Index(name = "idx_type", columnList = "type"),
		@Index(name = "idx_stdDate_endDate", columnList = "stdDate, endDate"),
		@Index(name = "idx_provided", columnList = "provided")
})
public class FamilyCar {

	@Id
	private Long id;
	
	@Comment("타입")
	@Column
	private String type;
	
	@Comment("신청시작일")
	@Column
	private String stdDate;
	
	@Comment("신청종료일")
	@Column
	private String endDate;
	
	@Comment("요청자")
	@Column
	private String provided;

	@Comment("저장일")
	@Column
	private Date cDate;
	
}
