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
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

	@Test  //创建索引
	public void createIndex() {
		boolean rcDepot = ElasticsearchUtil.createIndex("rc_depot");
		System.out.println(rcDepot);
	}
	@Test  //判断索引是否存在
	public void isIndexExist() {
        boolean rcDepot = ElasticsearchUtil.isIndexExist("rc_depot");
        System.out.println(rcDepot);
	}
	@Test   //新增数据
    public void addData(){

       /* XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id","0")
                    .field("DEPOT_ID","0")
                    .field("DEPOT_NAME","顶级仓库")
                    .field("DEPOT_TYPE",1)
                    .field("UPDATE_TIME",new Date())
                    .endObject();
            String depotId = ElasticsearchUtil.addData(mapping, "rc_depot", "0");
            System.out.println(depotId);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id","1")
                    .field("DEPOT_ID","1")
                    .field("DEPOT_NAME","仓库1号")
                    .field("DEPOT_TYPE",2)
                    .field("SUP_DEPOT_ID","0")
                    .field("UPDATE_TIME",new Date())
                    .endObject();
            String depotId = ElasticsearchUtil.addData(mapping, "rc_depot", "1");
            System.out.println(depotId);
        } catch (IOException e) {
            e.printStackTrace();
        }

    };
    @Test  //删除索引
    public void deleteIndex(){
        ElasticsearchUtil.deleteIndex("rc_depot");
    };

    @Test  //删除数据
    public void deleteData(){
        try {
            ElasticsearchUtil.deleteData("rc_depot","1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test  //修改数据
    public void updateData(){
        XContentBuilder mapping = null;
        try {
            String date = "2020-09-27 11:57:30";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id","1")
                    .field("DEPOT_ID","1")
                    .field("DEPOT_NAME","仓库1号")
                    .field("DEPOT_TYPE",2)
                    .field("SUP_DEPOT_ID","0")
                    .field("UPDATE_TIME",dateFormat.parse(date))
                    .endObject();
            String depotId = ElasticsearchUtil.updateData(mapping,"rc_depot","1");
            System.out.println(depotId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test  //查询数据
    public void queryDataByParam() {
        String date = "2020-09-27 11:57:30";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Map<String, Object> depot = ElasticsearchUtil.getDataByParam("rc_depot", "1", "2", dateFormat.parse(date));
            depot.forEach((key , value) ->{
                System.out.println(key + "--->" + value);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
