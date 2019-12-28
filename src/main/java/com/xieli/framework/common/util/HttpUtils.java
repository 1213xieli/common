package com.xieli.framework.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String doGet(String url, Map params) {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();

            int index = url.indexOf("?");
            if (index == -1) {
                url = url + "?testnumber=" + Math.random();
            } else {
                url = url + "&testnumber=" + Math.random();
            }

            if (params != null) {
                for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(params.get(name));
                    url = url + "&" + name + "=" + URLEncoder.encode(value, "utf-8");
                }
            }
            System.out.println(url);
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            //if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String strResult = EntityUtils.toString(response.getEntity());

            return strResult;
            //}
        } catch (IOException e) {
            logger.error("HttpUtils.doGet", e);
        }

        return null;

    }

    public static String doGet(String url, Map params, Map headers) {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();

            int index = url.indexOf("?");
            if (index == -1) {
                url = url + "?testnumber=" + Math.random();
            } else {
                url = url + "&testnumber=" + Math.random();
            }

            if (params != null) {
                for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(params.get(name));
                    url = url + "&" + name + "=" + URLEncoder.encode(value, "utf-8");
                }
            }

            HttpGet request = new HttpGet(url);
            if (headers != null) {
                for (Iterator iter = headers.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(headers.get(name));
                    request.addHeader(name, value);
                }
            }

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());

                return strResult;

            }
        } catch (IOException e) {
            logger.error("HttpUtils.doGet with header", e);
        }

        return null;

    }

    public static String postForm(String url, Map params) {
        BufferedReader in = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            logger.debug(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            logger.error("HttpUtils.postForm", e);
            return null;
        }

    }

    public static String postForm(String url, Map params, Map headers) {
        BufferedReader in = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            request.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            for (Iterator iter = headers.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(headers.get(name));
                request.addHeader(name, value);
            }

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            //if(code == 200){
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }

            in.close();
            logger.debug(sb.toString());
            return sb.toString();
            //}
        } catch (Exception e) {
            logger.error("HttpUtils.postForm with header", e);
            return null;
        }

    }

    public static String postFormWithJson(String url, String JSONObject, Map headers) {
        BufferedReader in = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

            StringEntity se = new StringEntity(JSONObject, "UTF-8");

            se.setContentType("text/json");

            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            for (Iterator iter = headers.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(headers.get(name));
                httpPost.addHeader(name, value);
            }

            httpPost.setEntity(se);

            HttpResponse response = client.execute(httpPost);

            int code = response.getStatusLine().getStatusCode();
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }

            in.close();
            logger.debug(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            logger.error("HttpUtils.postFormWithJson with header", e);
        }

        return null;
    }

    public static String postFormWithJson(String url, String JSONObject) {
        BufferedReader in = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

            StringEntity se = new StringEntity(JSONObject, "UTF-8");

            se.setContentType("text/json");

            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            httpPost.setEntity(se);

            HttpResponse response = client.execute(httpPost);

            int code = response.getStatusLine().getStatusCode();
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }

            in.close();
            logger.debug(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            logger.error("HttpUtils.postFormWithJson", e);
        }

        return null;
    }

    public static String postFormWithFile(String url, Map<String, File> fileMap, Map params) {
        BufferedReader in = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader(HTTP.CONTENT_TYPE, "multipart/form-data;charset=utf-8");

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            if (fileMap != null) {
                for (Iterator iter = fileMap.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    File file = fileMap.get(name);
                    multipartEntityBuilder.addBinaryBody(name, file);
                }
            }

            if (params != null) {
                for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(params.get(name));
                    multipartEntityBuilder.addTextBody(name, value);
                }
            }

            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            HttpResponse response = client.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }

            in.close();
            logger.debug(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            logger.error("HttpUtils.postFormWithFile", e);
        }

        return null;
    }

    public static String postFormWithFile(String url, File file, String fileName, Map params) {
        BufferedReader in = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
            HttpPost httpPost = new HttpPost(url);
            //httpPost.addHeader(HTTP.CONTENT_TYPE, "multipart/form-data;charset=utf-8");
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            if (file != null) {
                multipartEntityBuilder.addBinaryBody(fileName, file);
            }

            if (params != null) {
                for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(params.get(name));

                    multipartEntityBuilder.addTextBody(name, value);
                }
            }

            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            HttpResponse response = client.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }

            in.close();
            logger.debug(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            logger.error("HttpUtils.postFormWithFile1", e);
        }
        return null;
    }

    public static String postFormWithFile(String url, InputStream file, String fileName, Map params) {
        BufferedReader in = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
            HttpPost httpPost = new HttpPost(url);
            //httpPost.addHeader(HTTP.CONTENT_TYPE, "multipart/form-data;charset=utf-8");
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            if (file != null) {
                multipartEntityBuilder.addBinaryBody(fileName, file, ContentType.DEFAULT_BINARY, "hahahaha.txt");
            }

            if (params != null) {
                for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(params.get(name));

                    multipartEntityBuilder.addTextBody(name, value);
                }
            }

            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            HttpResponse response = client.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }

            in.close();
            logger.debug(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            logger.error("HttpUtils.postFormWithFile1", e);
        }
        return null;
    }

    public static HttpURLConnection getFile(String requestUrl)
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
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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

    public static void main(String[] args) throws FileNotFoundException {
    	File file = new File("E:/aaa.txt");
    	Map param = new HashMap();
    	param.put("key", "88888888.txt");
    	param.put("content", "asfasdfasdfasdfasgasfasdfasf");
    	HttpUtils.postFormWithFile("http://192.168.92.89:8807/uploadFile", new FileInputStream(file), "file", param);
	}
}

