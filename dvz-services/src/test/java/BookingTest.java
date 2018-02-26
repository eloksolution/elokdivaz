import static io.restassured.RestAssured.given;

import org.junit.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingTest {
	@Test
	public void getAllCompanies() {
		RequestSpecification request=given();
		request.header("companyId", "1");
		Response r=request.when().get("http://localhost:8080/booking/getAll");
		System.out.println("getAllCompanies response "+r.getBody().asString());
		System.out.println("status is "+r.then().statusCode(200));
	}
	
	@Test
	public void getBookingById() {
		RequestSpecification request=given();
		request.header("companyId", "1");
		Response r=request.when().get("http://localhost:8080/booking/getBooking/4");
		System.out.println("getBookingById response "+r.getBody().asString());
		System.out.println("status is "+r.then().statusCode(200));
	}
	
	@Test
	public void addBooking() {
		RequestSpecification request=given();
		request.header("companyId", "1");
		request.header("Content-Type","application/json");
		String booking="{ \"customerName\": \"New Looks\", \"customerId\": 3, \"strOrderItems\": \"1\", \"totalPrice\": 300 }";
		Response r=request.when().body(booking).post("http://localhost:8080/booking/add");
		System.out.println("addBooking response "+r.getBody().asString());
		System.out.println("status is "+r.then().statusCode(200));
	}
	
	@Test
	public void updateBooking() {
		RequestSpecification request=given();
		request.header("companyId", "1");
		request.header("Content-Type","application/json");
		String booking="{ \"id\": 4, \"customerName\": \"Beaut Updated Edge\", \"customerId\": 3, \"strOrderItems\": \"1\", \"strOrderDate\": \"21/01/2018\", \"totalPrice\": 300, \"orderItems\": 1 }";
		Response r=request.when().body(booking).post("http://localhost:8080/booking/update");
		System.out.println("updateBooking response "+r.getBody().asString());
		System.out.println("status is "+r.then().statusCode(200));
	}
}
