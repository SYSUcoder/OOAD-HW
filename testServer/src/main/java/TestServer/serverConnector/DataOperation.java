package TestServer.serverConnector;

/**
 * Created by ljkis_000 on 2015/9/30.
 */
public class DataOperation {
    static private String baseUrl = "http://localhost:8080";
//    static private String baseUrl = "http://42.96.205.36:8080";

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        DataOperation.baseUrl = baseUrl;
    }
}
