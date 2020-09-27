package com.personal.elasticsearch.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Getter
@Setter
@ComponentScan(basePackageClasses=ESClientSpringFactory.class)
public class ElasticsearchRestClient {

    private  final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchRestClient.class);


    @Value("${elasticSearch.client.connectNum}")
    private Integer connectNum;

    @Value("${elasticSearch.client.connectPerRoute}")
    private Integer connectPerRoute;

    @Value("${elasticSearch.hostlist}")
    private String hostlist;

    @Bean
    public HttpHost[] httpHost(){
        //解析hostlist配置信息
        String[] split = hostlist.split(",");
        //创建HttpHost数组，其中存放es主机和端口的配置信息
        HttpHost[] httpHostArray = new HttpHost[split.length];
        for(int i=0;i<split.length;i++){
            String item = split[i];
            httpHostArray[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
        }
        LOGGER.info("init HttpHost", JSONObject.toJSONString(httpHostArray));
        return httpHostArray;
    }

    @Bean(initMethod="init",destroyMethod="close")
    public ESClientSpringFactory getFactory(){
        LOGGER.info("ESClientSpringFactory 初始化");
        return ESClientSpringFactory.
                build(httpHost(), connectNum, connectPerRoute);
    }

    @Bean(name = "restClient")
    @Scope("singleton")
    public RestClient getRestClient(){
        LOGGER.info("RestClient 初始化");
        return getFactory().getClient();
    }
    @Bean
    public String test(){
        LOGGER.info("===测试bean===");
        return "测试bean";
    }
    @Bean(name = "restHighLevelClient")
    @Scope("singleton")
    public RestHighLevelClient getRHLClient(){
        LOGGER.info("RestHighLevelClient 初始化");
        return getFactory().getRhlClient();
    }

}