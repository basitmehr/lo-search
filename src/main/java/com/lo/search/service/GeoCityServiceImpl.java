package com.lo.search.service;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lo.search.service.dao.CityDao;
import com.lo.search.service.domain.GeoCity;
import com.lo.search.service.domain.GeoCitySearch;
import com.lo.search.service.domain.GeoLocation;
import com.lo.search.service.domain.PinLocation;
import com.lo.search.service.es.ElasticsearchOps;

@Service
public class GeoCityServiceImpl implements GeoCityService {
	
	Logger logger = LoggerFactory.getLogger(GeoCityServiceImpl.class);
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Autowired
	CityDao cityDao;
	
	@Autowired
	ElasticsearchOps elasticsearchOps;
	
	@Override
	public void handleRequest() throws Exception {
		
		logger.info("GeoCityService Started............");
		
		List<GeoCity> geoCities = null;
		
		BulkRequest bulkRequest = new BulkRequest();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		int limit = 3000;
		int offset = 0;
		int paging = 1;
		while((geoCities = cityDao.findAll(limit, offset)).size() != 0) {
			logger.info("geoCitiesSize:{} - limit: {} - offset: {} - paging: {}", geoCities.size(), limit, offset, paging);			
			offset = paging * limit;
			paging++;
			
			for(GeoCity geoCity : geoCities) {
				
				String location = new StringBuilder(geoCity.getCity()).append(", ")
						.append(geoCity.getStateId()).append(", ")
						.append(geoCity.getCountryCode()).toString();
				
				GeoCitySearch geoCitySearch = GeoCitySearch.builder()
						.location(location)
						.pin(PinLocation.builder().location(GeoLocation.builder()
				             .lat(geoCity.getLat())
				             .lon(geoCity.getLon()).build()).build())
						.zips(geoCity.getZips())
						.build();
				
				IndexRequest indexRequest = new IndexRequest("geo_city");
				String jsonString = objectMapper.writeValueAsString(geoCitySearch);
				indexRequest.source(jsonString, XContentType.JSON);
				
				bulkRequest.add(indexRequest);
			}
			
			elasticsearchOps.saveBulk(bulkRequest);
		}
		
		System.out.println();
	}

}
