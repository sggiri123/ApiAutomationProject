package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test (priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String firstName, String lastName, String email, String password, String phone)
	{
        User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response= UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test (priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String userName)
	{
		Response response= UserEndpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	

}
