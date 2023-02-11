package com.ConcurrentRequest.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HttpObject {

	private boolean result;
	private GridObject data;
}
