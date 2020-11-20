package com.lo.search.service.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class GeoCity {

	private long usCityId;
	private String city;
	private String stateId;
	private String stateName;
	private String countyFips;
	private String countyName;
	private String lat;
	private String lon;
	private String timezone;
	private String zips;
	private String countryCode;
	
	
}
