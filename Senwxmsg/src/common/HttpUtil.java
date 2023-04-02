package common;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpUtil {

    private static final CloseableHttpClient httpClient;
	static {
		httpClient = HttpClients.createDefault();
	}
 
	/**
	 * get请求
	 * 
	 * @return
	 */
	public static String doGet(String url) {
		CloseableHttpResponse response = null;
		try {
			// 发送get请求
			HttpGet httpGet = new HttpGet(url);
			// 配置请求参数
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
					.setConnectionRequestTimeout(1000).setSocketTimeout(6000).build();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			// 建立的http连接，仍旧被response保持着，允许我们从网络socket中获取返回的数据
			// 为了释放资源，我们必须手动消耗掉response或者取消连接（使用CloseableHttpResponse类的close方法）
			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String strResult = EntityUtils.toString(entity, "UTF-8");
					/** 消耗掉entity对象 **/
					EntityUtils.consume(entity);
			        
					return strResult;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
 
	/**
	 * post请求(请求参数是 name1=value1&name2=value2 的形式)
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, String params) {
		System.out.println("开始post请求");
		System.out.println("请求参数："+params);
		// 创建httpPost
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "*/*");
		httpPost.setHeader("Accept-Charset", "UTF-8");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		StringEntity entity = new StringEntity(params, "UTF-8");
		httpPost.setEntity(entity);
		// 配置请求参数
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
				.setConnectionRequestTimeout(1000).setSocketTimeout(6000).build();
		httpPost.setConfig(requestConfig);
		// 执行请求
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				if (responseEntity != null) {
					String jsonString = EntityUtils.toString(responseEntity, "UTF-8");
					/** 消耗掉entity对象 **/
					EntityUtils.consume(responseEntity);
					return jsonString;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
 
	/**
	 * post请求（用于请求json格式的参数）
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doJsonPost(String url, String params) throws Exception {
		// 创建httpPost
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		StringEntity entity = new StringEntity(params, "UTF-8");
		httpPost.setEntity(entity);
		// 配置请求参数
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
				.setConnectionRequestTimeout(1000).setSocketTimeout(6000).build();
		httpPost.setConfig(requestConfig);
		// 执行请求
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				if (responseEntity != null) {
					String jsonString = EntityUtils.toString(responseEntity, "UTF-8");
					/** 消耗掉entity对象 **/
					EntityUtils.consume(responseEntity);
					return jsonString;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
 
	/**
	 * 将map对象转换为URL参数格式
	 * 
	 * @param params
	 * @return
	 */
	public static String buildParam(Map<String, Object> params) {
		if (params.isEmpty()) {
			return "";
		}
		StringBuilder param = new StringBuilder();
		Iterator<String> keyItor = params.keySet().iterator();
		try {
			while (keyItor.hasNext()) {
				String key = keyItor.next();
				Object o = params.get(key);
				if (o == null) {
					param.append(key + "=&");
				} else {
					param.append(key + "=" + URLEncoder.encode(String.valueOf(o), "UTF-8") + "&");
				}
			}
			if (param.length() != 0) {
				param.deleteCharAt(param.length() - 1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return param.toString();
	}
	
	
	public static String getHtml(HttpServletRequest request,  
			HttpServletResponse response,String urlString) throws IOException {
			response.sendRedirect(urlString); 
		
		            return null;
	       
	}
	
	/*
	public static void main(String[] args) {
		String  urlstr="http://api.tbk.dingdanxia.com/vip/goodsList?apikey=yYQzHZw9Mx9Tff6xOcTLoky5d9Xe7urM&channelType=0&queryStock=true&page=1&pageSize=30&sourceType=1&jxCode=4hm6c35w&fieldName=COMMISSION";
	String  urlhtml=doGet(urlstr);
	
	System.out.println(urlhtml);
		
	}*/


}
