package com.epam.tr.task04.paymentsapp.dao.connectionpool;

import java.util.Locale;
import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

     private ResourceBundle bundle = ResourceBundle.getBundle("config", Locale.ENGLISH);

     private DBResourceManager () {}

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
