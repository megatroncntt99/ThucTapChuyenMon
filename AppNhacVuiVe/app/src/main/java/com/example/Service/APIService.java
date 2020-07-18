package com.example.Service;

public class APIService {
//    private static final String base_url="http://192.168.0.102/server/";
    private static final String base_url="https://megtroncntt99.000webhostapp.com/server/";


    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
