package com.yunlan;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-08
 */
@Slf4j
public class SingleCrawler {

    public static void main(String[] args) throws IOException {

        //easyFetchData();
        //getMethodFetchData();
        //getMethodWithParamsFetchData();
        //postMethodFetchData();
        postMethodWithParamsFetchData();
    }

    /**
     * 初次尝试网络爬虫
     * @throws IOException
     */
    private static void easyFetchData() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("爬取黑马首页数据：{}",content);
        }
    }

    /**
     * get 方式抓取网络数据
     * @throws IOException
     */
    private static void getMethodFetchData() throws IOException {
        //创建HttpClient对象 
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet请求
        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");
        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求 
            response = httpClient.execute(httpGet);
            //判断响应状态码是否为200 
            if (response.getStatusLine().getStatusCode() == 200) {
                //如果为200表示请求成功，获取返回数据 
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //打印数据长度
                log.info("Get方式爬取黑马首页数据:{}",content);
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
            }
            httpClient.close();
        }
    }

    /**
     * get 方式抓取网络数据[带参数]
     * @throws IOException
     */
    private static void getMethodWithParamsFetchData() throws IOException {
        //创建HttpClient对象 
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet请求，带参数的地址https://www.baidu.com/s?wd=HttpClient
        String uri = "http://yun.itheima.com/search?keys=Java";
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求
            response = httpClient.execute(httpGet);
            //判断响应状态码是否为200 
            if (response.getStatusLine()
                    .getStatusCode() == 200) {
                //如果为200表示请求成功，获取返回数据 
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //打印数据长度
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

    /**
     * post 方式抓取网络数据
     * @throws IOException
     */
    private static void postMethodFetchData() throws IOException {
        //创建HttpClient对象 
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet请求
        HttpPost httpPost = new HttpPost("http://www.itcast.cn/");
        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求 
            response = httpClient.execute(httpPost);
            //判断响应状态码是否为200 
            if (response.getStatusLine()
                    .getStatusCode() == 200) {
                //如果为200表示请求成功，获取返回数据 
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //打印数据长度 
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

    /**
     * post 方式抓取网络数据[带参数]
     * @throws IOException
     */
    private static void postMethodWithParamsFetchData() throws IOException {
        //创建HttpClient对象 
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet请求
        HttpPost httpPost = new HttpPost("http://www.itcast.cn/");
        //声明存放参数的List集合 
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("keys", "java"));
        //创建表单数据Entity
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
        //设置表单Entity到httpPost请求对象中 
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求
            response = httpClient.execute(httpPost);
            //判断响应状态码是否为200 
            if (response.getStatusLine()
                    .getStatusCode() == 200) {
                //如果为200表示请求成功，获取返回数据 
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //打印数据长度
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
