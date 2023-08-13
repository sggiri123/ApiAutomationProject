package api.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(0);	
		
//	Generating Logs
		
		logger = LogManager.getLogger(this.getClass());
	
	}
	

	@Test(priority=1)
	public void testCreateUser()
	{
		logger.info("========Creating User=================");
		Response response= UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("========User created=================");
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		logger.info("========Getting User=================");
		Response response= UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("========User Got=================");
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("========Updating User info=================");
//		Updating the user data
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		logger.info("========User info Updated=================");
		
//		Validating after updating the data
		logger.info("========Validating user info=================");
		Response response= UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("========User info validated=================");
		
//		Checking the information is updated or not.
		Response responseAfterUpdate= UserEndpoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("========User info successfully updated=================");
		
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("========Deleting User=================");
		Response response= UserEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("========User Deleted=================");
	}
	
	
	
	
}
