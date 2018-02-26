import static io.restassured.RestAssured.given;

import org.junit.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServicesTest {

	
	@Test
	public void addServices() {
		RequestSpecification request=given();
		request.header("companyId", "1");
		request.header("Content-Type","application/json");
		String services="{\"name\":\"Facial\",\"description\":\"Facial\",\"price\":999,\"discount\":10,\"imagePath\":\"img_path\",\"imageIcon\":\"icon\"}";
		Response r=request.when().body(services).post("http://localhost:8080/services/add");
		System.out.println("**************addservices response "+r.getBody().asString());
		//System.out.println("status is "+r.then().statusCode(200));
	}

	@Test
	public void getAllCompanies() {
		RequestSpecification request=given();
		request.header("companyId", "1");
		Response r=request.when().get("http://localhost:8080/services/getAll");
		System.out.println("**************getAllCompanies response "+r.getBody().asString());
		//System.out.println("status is "+r.then().statusCode(200));
	}
	
	@Test
	public void getServicesById() {
		RequestSpecification request=given();
		request.header("companyId", "1");
		Response r=request.when().get("http://localhost:8080/services/getService/2");
		System.out.println("**************getservicesById response "+r.getBody().asString());
		//System.out.println("status is "+r.then().statusCode(200));
	}
	
	@Test
	public void updateServices() {
		RequestSpecification request=given();
		request.header("companyId", "1");
		request.header("Content-Type","application/json");
		String services="{\"id\":2,\"name\":\"Facial Updated\",\"description\":\"Facial Updated\",\"price\":1999,\"discount\":10,\"imagePath\":\"img_path\",\"imageIcon\":\"icon\"}";
		Response r=request.when().body(services).post("http://localhost:8080/services/update");
		System.out.println("**************updateservices response "+r.getBody().asString());
		//System.out.println("status is "+r.then().statusCode(200));
	}
}
