package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;

public class XUrlImpl implements XUrl {
    Map<String, String> map = new HashMap();
    String host = "http://short.url/";
    Map<String, String> mapNew = new HashMap();
    Map<String, Integer> mapCount = new HashMap();

    @Override
    public String registerNewUrl(String longUrl) {
        String shortUrl = new String();
        String sample = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for(int i = 0; i < 9; i++) {
            int index =(int) (Math.floor(Math.random()*62));
            char a = sample.charAt(index);   
            shortUrl += a;
        }
        map.put(longUrl, host+shortUrl); 
        mapNew.put(host+shortUrl,longUrl);
        return map.getOrDefault(longUrl,null);
    }

    public String decode(String shortUrl) {
        return map.getOrDefault(shortUrl,null);
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if(map.containsValue(shortUrl)){
            return null;
        }
        map.put(longUrl,shortUrl);
        mapNew.put(shortUrl,longUrl);
        return map.getOrDefault(longUrl,null);
    }

    @Override
    public String getUrl(String shortUrl) {
        String longUrl = mapNew.getOrDefault(shortUrl,null);
        mapCount.put(longUrl,mapCount.getOrDefault(longUrl,0)+1);
        return longUrl;
    }

    @Override
    public String delete(String longUrl) {
        String shortUrl = map.getOrDefault(longUrl,null);
        map.remove(longUrl);
        map.put(null, shortUrl);
        mapNew.put(shortUrl, null);
        return map.get(shortUrl);
    }

    @Override
    public Integer getHitCount(String longUrl) {
        return mapCount.getOrDefault(longUrl,0);
    }

}