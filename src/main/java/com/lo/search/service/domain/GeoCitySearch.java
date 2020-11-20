package com.lo.search.service.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class GeoCitySearch {
	
	private String location;
	private String zips;
	private PinLocation pin;
	private String lat;
	private String lon;

}
