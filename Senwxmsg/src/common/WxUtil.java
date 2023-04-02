package common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson2.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Description: 访问外部接口工具类
 * @Author: zdj
 * @Date: 2021/12/08
 * @version: 1.0.0
 */
public class WxUtil {
    
    /**
     * 一物一码
     * 获取小程序二维码数据流
     * @param url 请求路径
     * @param paraMap 其他参数
     * @return
     */
    public static byte[] getQrCodeByParam(String url, Map<String, Object> paraMap) {
        byte[] result = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        try {
            // 设置请求的参数
            JSONObject postData = new JSONObject();
            for (Map.Entry<String, Object> entry : paraMap.entrySet()) {
                postData.put(entry.getKey(), entry.getValue());
            }
            httpPost.setEntity(new StringEntity(postData.toString(), "UTF-8"));
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toByteArray(entity);
            
        } catch (ConnectionPoolTimeoutException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        return result;
    }
    
    /**获取微信access_token,用于控制器内部使用**/
    public String getAccessToken2(String APPID,String SECRET) throws IOException {

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&" + "appid=" + APPID + "&secret=" + SECRET;
    //使用HttpClient发送请求
    CloseableHttpClient httpclient = HttpClients.createDefault();
    //发送Get请求
    HttpGet request = new HttpGet(url);
    request.addHeader("Content-Type", "application/json");
    //获得响应
    CloseableHttpResponse response = httpclient.execute(request);
    //拿到响应体
    HttpEntity httpEntity = response.getEntity();
    //使用工具转换
    String result;
	try {
		result = EntityUtils.toString(httpEntity, "UTF-8");
		System.out.println(result);
		// 转成string
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);//拿到的所有内容
        String access_token = jsonObject.get("access_token").toString();

        return access_token;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return null;
	}
	
	

    }
    
    /**
     * 测试删除
     * @param url 指定的文件路径
     * @param s   指定的特殊字符-时间
     */
    public static void delFileByName(String url, String s) {
    	// 创建文件
    	File grandpaFile = new File(url);
    	// 检查该对象是否是文件夹
    	if(grandpaFile.isDirectory()) {
    		// 返回该目录中的文件和目录	
    		File[] fatherFiles = grandpaFile.listFiles();

    		if (fatherFiles.length > 0) {
    		    // 循环返回的文件
    			for (File sonFile : fatherFiles) {
    				// 继续调用自身进行判断
    				delFileByName(sonFile.getPath(),s);
    			}
    		} else {
    			// 判断自己是否包含特殊字符
    			if(grandpaFile.getName().contains(s)) {
    				// 删除包含特殊字符的文件
    				grandpaFile.delete();
    			}
    		}
    	} else {
    		if(grandpaFile.getName().contains(s)) {
    			grandpaFile.delete();
    		}
    	}
    }
    
    
    
    /**
     * 删除指定文件夹下的全部内容
     * @param file
     * File basefile = new File(path);   //path为给定的文件夹地址
     *  remove(basefile);
     */
    public static void remove(File file) {
    
        File[] files = file.listFiles();//将file子目录及子文件放进文件数组
        if (files != null) {//如果包含文件进行删除操作
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {//删除子文件
                    files[i].delete();
                } else if (files[i].isDirectory()) {//通过递归方法删除子目录的文件
                    remove(files[i]);
                }
                files[i].delete();//删除子目录
            }
        }
        
        System.out.println("删除成功");
    }
    
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	/**
	 * 可逆的的加密解密方法；两次是解密，一次是加密
	 * @param inStr
	 * @return
	 */
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	
	public static void main(String[] args) {
		File basefile = new File("C:\\lftx\\imgtemp"); 
		remove(basefile);

	}
}
