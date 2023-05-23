package apiStartPoints;

import apiEndPoints.reqresEndPoints;
import apiService.ApiServices;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UserTestClass implements reqresEndPoints {

    ApiServices apiServices = new ApiServices();

    @DataProvider(name="createData")
    public Object[][] createData(){
        return new Object[][]{
                {"sumit","lead"}
        };
    }

    @DataProvider(name="updateUserData")
    public Object[][] updateUserData(){
        return new Object[][]{
                {"sumit","manager"}
        };
    }

    @Test
    public void getUserData() throws Exception {
        Response response = apiServices.getUserDetails(getUser);

        JSONObject response1 = new JSONObject(response.getBody().prettyPrint());
        HashMap<String, String> userDetails = new HashMap<>();
        for (int i = 0; i < response1.getJSONArray("data").length(); i++) {
            String name = response1.getJSONArray("data").getJSONObject(i).getString("first_name");
            String email = response1.getJSONArray("data").getJSONObject(i).getString("email");
            userDetails.put(name, email);
        }
        System.out.println(userDetails);
        Assert.assertNotNull(response1.getJSONArray("data").getJSONObject(0).getInt("id"));
        Assert.assertNotNull(response1.getJSONArray("data").getJSONObject(0).getString("email"));
        Assert.assertNotNull(response1.getJSONArray("data").getJSONObject(0).getString("first_name"));
        Assert.assertNotNull(response1.getJSONArray("data").getJSONObject(0).getString("last_name"));
        Assert.assertNotNull(response1.getJSONArray("data").getJSONObject(0).getString("avatar"));
        Assert.assertEquals(response.getStatusCode(), 200, "it's not 200");

    }

    @Test(dataProvider = "createData")
    public JSONObject createUser(String name, String job) throws Exception{
        JSONObject request= new JSONObject();
        request.put("name",name);
        request.put("job",job);
        Response response = apiServices.createUser(createUser,request);
        JSONObject jsonResponse = new JSONObject(response.getBody().prettyPrint());
        return jsonResponse;
    }

    @Test
    public void getUserDetailsById() throws Exception{
        Response response =apiServices.getUserDetailsById(getUserById,"2");
       JSONObject response1= new JSONObject(response.getBody().prettyPrint());
        Assert.assertNotNull(response1.getJSONObject("data").getInt("id"));
        Assert.assertNotNull(response1.getJSONObject("data").getString("email"));
        Assert.assertNotNull(response1.getJSONObject("data").getString("first_name"));
        Assert.assertNotNull(response1.getJSONObject("data").getString("last_name"));
        Assert.assertNotNull(response1.getJSONObject("data").getString("avatar"));
        Assert.assertEquals(response.getStatusCode(), 200, "it's not 200");

    }

    @Test(dataProvider = "updateUserData")
    public void updateUser(String name, String job) throws Exception{
        JSONObject jsonResponse = createUser(name,job);
        String id = jsonResponse.getString("id");
        JSONObject request= new JSONObject();
        request.put("name",name);
        request.put("job",job);
        Response response = apiServices.updateUserDetails(updateUser, request, id);
        JSONObject response2= new JSONObject(response.getBody().prettyPrint());
        System.out.println("The status received: " + response2);

    }
}