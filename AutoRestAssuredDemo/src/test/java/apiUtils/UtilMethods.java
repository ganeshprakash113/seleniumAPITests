package apiUtils;

import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.given;


public class UtilMethods {

    Response response;

    public Response getUserDetailsById(String url,String id){
        try{
            response= given().pathParam("id",id).get(url);
            if(response!=null){
                System.out.println("getting response");
            }else{
                System.out.println("Not getting any response");
            }
        }catch(Exception e){
            System.out.println(e.getCause().getMessage());
        }
        return response;
    }

    public Response getUserData(String url){
        try{
            response= given().get(url);
            if(response!=null){
                System.out.println("getting response");
            }else{
                System.out.println("Not getting any response");
            }
        }catch(Exception e){
            System.out.println(e.getCause().getMessage());
        }
        return response;
    }

    public Response postCreateUser(String url, JSONObject request){
        try{
            response=given().header("Content-type", "application/json").when()
                    .body(request.toString()).post(url);
            if(response!=null){
                System.out.println("getting response");
            }else{
                System.out.println("Not getting any response");
            }
        }catch(Exception e){
            System.out.println(e.getCause().getMessage());
        }
        return response;
    }

    public Response updateUserDetails(String url, JSONObject request,String id){
        try{
            response=given().header("Content-type", "application/json").pathParam("id",id)
                    .when()
                    .body(request.toString()).put(url);
            if(response!=null){
                System.out.println("getting response");
            }else{
                System.out.println("Not getting any response");
            }
        }catch(Exception e){
            System.out.println(e.getCause().getMessage());
        }
        return response;
    }

}