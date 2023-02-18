package dev.skosarev.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {
    private final static String VK_API_BASE_URL = "https://api.vk.com/";
    private final static String VK_USERS_GET = "method/users.get/";
    private final static String PARAM_ACCESS_TOKEN = "access_token";
    private final static String PARAM_USER_ID = "user_ids";
    private final static String PARAM_VERSION = "v";


    public static URL generateURL(String userId) {
        Uri builtUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID, userId)
                .appendQueryParameter(PARAM_VERSION, "5.131")
                .appendQueryParameter(PARAM_ACCESS_TOKEN,
                        "063de336063de336063de3365b052f54700063d063de33665cd917d0afabff1d6a67e35")
                .build();

        URL url;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (UnknownHostException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }

    }
}
