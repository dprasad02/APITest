import java.util.HashMap;
import java.util.Map;

public class HeaderConfig {


    public Map<String,String> defaultHeaders(){
        Map<String,String> defaultHeaders = new HashMap<String,String>();

        defaultHeaders.put("Content-Type","application/json");

        return defaultHeaders;

    }

    public Map<String,String> defaultHeaderswithToken(){
        Map<String,String> defaultHeaders = new HashMap<String,String>();
        defaultHeaders.put("Content-Type","application/json");
        defaultHeaders.put("Authorization","Bearer a13ef3cb5fb9cb438c7e5b13eb76e3ca94e8ed99e8f8aa53045a6786ed09538e");

        return defaultHeaders;

    }
}
