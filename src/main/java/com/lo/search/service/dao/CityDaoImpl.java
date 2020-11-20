package com.lo.search.service.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lo.search.service.domain.GeoCity;

@Repository
public class CityDaoImpl implements CityDao {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	@Value("${mysql.city}")
	private String citySQL;

	@Override
	public List<GeoCity> findByState(String stateId, int limit, int offset) {
		
		Object[] args = new Object[] {stateId, limit, offset};
		int[] argTypes = new int[] {Types.VARCHAR, Types.INTEGER, Types.INTEGER};
		
		StringBuilder cityByStateid = new StringBuilder(citySQL)
				.append(" where state_id = ? limit ? offset ");
		
		List<GeoCity> geoCities = new ArrayList<>();
				
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(cityByStateid.toString(), args, argTypes);
		if(rows != null) {
			for(Map<String, Object> row : rows) {
				geoCities.add(GeoCity.builder()
						.city(row.get("city")+"")
						.stateId(row.get("state_id")+"")
						.stateName(row.get("state_name")+"")
						.lat(row.get("lat")+"")
						.lon(row.get("lon")+"")
						.zips(row.get("zips")+"")
						.countryCode(row.get("country_code")+"")
						.build());
				
			}
		}
		
		return geoCities;
	}

	@Override
	public List<GeoCity> findAll(int limit, int offset) {
		Object[] args = new Object[] {limit, offset};
		int[] argTypes = new int[] {Types.INTEGER, Types.INTEGER};
		
		StringBuilder cityByStateid = new StringBuilder(citySQL)
				.append("  limit ? offset ?");
		
		List<GeoCity> geoCities = new ArrayList<>();
				
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(cityByStateid.toString(), args, argTypes);
		if(rows != null) {
			for(Map<String, Object> row : rows) {
				geoCities.add(GeoCity.builder()
						.city(row.get("city")+"")
						.stateId(row.get("state_id")+"")
						.stateName(row.get("state_name")+"")
						.lat(row.get("lat")+"")
						.lon(row.get("lon")+"")
						.zips(row.get("zips")+"")
						.countryCode(row.get("country_code")+"")
						.build());
				
			}
		}
		
		return geoCities;
	}

}
