package apiService;

import apiUtils.UtilMethods;
import io.restassured.response.Response;
import org.json.JSONObject;


public class ApiServices extends UtilMethods{
     Response response;

    public io.restassured.response.Response getUserDetails(String endpoint) {
        response=getUserData(endpoint);
        return response;
    }

    public Response createUser(String url, JSONObject request){
        response = postCreateUser(url,request);
        return  response;
    }

    public  Response getUser(String endpoint, String id){
        response=getUserDetailsById(endpoint,id);
        return response;
    }


}