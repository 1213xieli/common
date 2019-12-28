package com.xieli.framework.common.util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;


public class HttpsUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpsUtil.class);

	 private static final class DefaultTrustManager implements X509TrustManager {
	        @Override
	        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	        }

	        @Override
	        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	        }

	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            return null;
	        }

	    }

	 public static String doGet(String url,Map params) {
        	int index = url.indexOf("?");
        	if(index==-1){
        		url =url + "?testnumber="+Math.random();
        	}else{
        		url =url + "&testnumber="+Math.random();
        	}

        	//设置参数
        	if(params!=null){
        		for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
        			String name = (String) iter.next();
        			String value = String.valueOf(params.get(name));
        			url = url+ "&"+name+"="+value;
        		}
        	}
        	return httpsRequest(url,"GET",null);

		}

	/**
     * 发送https请求
     *
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param outputStr
     *            提交的数据
     * @return 返回服务器响应的信息
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr)
    {
        try
        {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            //TrustManager[] tm =
            //{ new DefaultTrustManager() };
            //SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            //sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            //SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            // conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr)
            {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null)
            {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce)
        {
            // log.error("连接超时：{}", ce);
        } catch (Exception e)
        {
            // log.error("https请求异常：{}", e);
        }
        return null;
    }

    /**
     * 发送https请求获取文件
     *
     * @param requestUrl
     *            请求地址
     * @return 返回
     */
    public static HttpsURLConnection getFile(String requestUrl)
    {
        try
        {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            /*TrustManager[] tm =
            { new DefaultTrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();*/
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            // conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod("GET");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");


            return conn;
        } catch (ConnectException ce)
        {
            ce.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
