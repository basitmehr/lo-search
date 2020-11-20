package com.lo.search.service.dao;

import java.util.List;

import com.lo.search.service.domain.GeoCity;

public interface CityDao {
	List<GeoCity> findByState(String stateId, int limit, int offset);
	List<GeoCity> findAll(int limit, int offset);
}
