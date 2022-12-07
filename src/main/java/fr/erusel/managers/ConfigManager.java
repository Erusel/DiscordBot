package fr.erusel.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    Properties properties;

    public static String BOT_TOKEN;
    public static String BOT_ACTIVITY;
    public static long NEWS_CHANNEL_ID;
    public static long JOIN_LEAVE_CHANNEL_ID;
    public void loadConfig(){
        properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("src/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BOT_TOKEN = properties.getProperty("BOT_TOKEN");
        BOT_ACTIVITY = properties.getProperty("BOT_ACTIVITY");
        NEWS_CHANNEL_ID = Long.parseLong(properties.getProperty("NEWS_CHANNEL_ID"));
        JOIN_LEAVE_CHANNEL_ID = Long.parseLong(properties.getProperty("JOIN_LEAVE_CHANNEL_ID"));

    }


}
