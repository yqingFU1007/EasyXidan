package com.example.asa.easyxiian;

import android.net.Uri;
import android.util.Log;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by asa on 2018/4/21.
 */

public class NetworkUtils {


    static HttpClient mClient;

    final static String QUERY_PARAM = "ticket";

    private static final String BASE_URL = "http://jwxt.xidian.edu.cn/caslogin.jsp";

    public static GetMethod getAction(HttpClient client, String url) throws IOException {

        mClient = client;
        GetMethod getMethod = new GetMethod(url);

        mClient.executeMethod(getMethod);

        return getMethod;
    }

    public static GetMethod getMethodUseCookie(HttpClient client, String JSESSION, String url) throws IOException {

        GetMethod getMethod = new GetMethod(url);
        getMethod.setFollowRedirects(false);
        getMethod.addRequestHeader(new Header("Cookie", "JSESSION=" + JSESSION));
        client.executeMethod(getMethod);
        return getMethod;
    }

    public static String[] getData(GetMethod getMethod) throws IOException {

        String[] results = new String[2];
        String JSESSION;
        org.jsoup.nodes.Document document;
        String lt = "";

        InputStream inputStream = getMethod.getResponseBodyAsStream();
        StringBuilder output = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gbk");
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = reader.readLine();
        while (line != null) {
            output.append(line);
            line = reader.readLine();
        }

        document = Jsoup.parseBodyFragment(output.toString());
        org.jsoup.nodes.Element body = document.body();
        lt = body.select("[name=lt]").attr("value");

        getMethod.releaseConnection();


        JSESSION = getMethod.getResponseHeader("Set-Cookie").getValue().trim().
                split(";")[0].split(",")[1].trim().split("=")[1];
        results[0] = JSESSION;
        results[1] = lt;
        return results;
    }

    public static PostMethod postAction(HttpClient client, String url, String userName, String password,
                                        String JSESSION, String lt) throws IOException, URIException {
        mClient = client;
        PostMethod postMethod = new PostMethod();
        URI uri = new URI(url);
        postMethod.setURI(uri);
        postMethod.addRequestHeader(new Header("Cookie", "JSESSION=" + JSESSION));
        postMethod.addParameter(new NameValuePair("_eventId", "submit"));
        postMethod.addParameter(new NameValuePair("username", userName));
        postMethod.addParameter(new NameValuePair("password", password));
        postMethod.addParameter(new NameValuePair("lt", lt));
        postMethod.addParameter(new NameValuePair("execution", "e1s1"));
        postMethod.addParameter(new NameValuePair("rmShown", "1"));
        postMethod.addParameter(new NameValuePair("submit", ""));
        mClient.executeMethod(postMethod);
        return postMethod;
    }

    public static String buildUrl(String locationQuery) {

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, locationQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v("NetworkUtils : ", "Built URI " + url);

        return url.toString();
    }


}
