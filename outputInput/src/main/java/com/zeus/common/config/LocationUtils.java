package com.zeus.common.config;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.net.URL;

/**
 * @author:tumu
 * @date:2019/9/23
 * @ver:1.0
 **/
public class LocationUtils {

        public static void main(String[] args) {
            // lat 31.2990170   纬度
            //log 121.3466440    经度
            String add = getAdd("120", "30");
        }

    public static String getAdd(String log, String lat ){
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=43i65ceXZP9FFka1gYXcM9rqeZum19RV&output=json&coordtype=wgs84ll&location="+lat+","+log;
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        String[] a=res.split("\"city\":\"");
        String b=a[1];
        String[] c =b.split("\"");
        String d=c[0];
        return d;
    }

    }

