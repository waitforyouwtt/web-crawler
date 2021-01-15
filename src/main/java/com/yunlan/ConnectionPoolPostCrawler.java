package com.yunlan;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-08
 */
public class ConnectionPoolPostCrawler {

    public static void main(String[] args) throws IOException {
        //创建HttpClient对象 
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet请求 
        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");
        //设置请求参数://设置创建连接的最长时间  //设置获取连接的最长时间  //设置数据传输的最长时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10 * 1000)
                .build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求 
            response = httpClient.execute(httpGet);
            if (response.getStatusLine()
                    .getStatusCode() == 200) {
                //如果为200表示请求成功，获取返回数据 
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            if (response == null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpClient.close();
            }
        }
    }
}
