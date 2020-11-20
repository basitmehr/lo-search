package com.lo.search.service.es;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchOps {
	
	@Autowired
	RestHighLevelClient restHighLevelClient;
	
	public void saveBulk(BulkRequest bulkRequest) throws Exception {
		
		restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
	}

}
