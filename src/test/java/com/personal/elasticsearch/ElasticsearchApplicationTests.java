package com.personal.elasticsearch;

import com.personal.elasticsearch.utils.ElasticsearchUtil;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

	@Test
	public void createIndex() {
		boolean rcDepot = ElasticsearchUtil.createIndex("rc_depot");
		System.out.println(rcDepot);
	}
	@Test
	public void isIndexExist() {
        boolean rcDepot = ElasticsearchUtil.isIndexExist("rc_depot");
        System.out.println(rcDepot);
	}
	@Test
    public void addData(){
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder().field("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    @Test
    public void deleteIndex(){
        ElasticsearchUtil.deleteIndex("rc_depot");
    };

}
