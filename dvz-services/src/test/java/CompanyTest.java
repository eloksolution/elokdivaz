
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.junit.Test;

public class CompanyTest {

	@Test
	public void add() {
		String json="{\"name\":\"Siri\",\"descriptioin\":\"New Add Siri Beauty Parlour\",\"code\":\"SIRI\",\"address\":\"EAST ANANDBAGH\",\"officePhone\":\"98989898\",\"ownerPhone\":\"8989898\",\"customerCarePhone\":\"787878\",\"status\":\"Y\",\"email\":\"info@siri.com\",\"password\":\"SIRI\"}";
		Response r=given().contentType("application/json").body(json).when().post("http://localhost:8080/company/add");
		String body = r.getBody().asString();
    	System.out.println(body);
	}
	
	@Test
	public void getAllCompanies() {
		given().when().get("http://localhost:8080/company/getAll").then().statusCode(200);
	}

	@Test
	public void list() {
		given().when().get("http://localhost:8080/company/list/10").then().statusCode(200);
	}
	
	@Test
	public void companyUpdate() {
		String json="{\"id\":1,\"name\":\"Siri\",\"descriptioin\":\"New Siri Beauty Parlour\",\"code\":\"SIRI\",\"address\":\"EAST ANANDBAGH\",\"officePhone\":\"98989898\",\"ownerPhone\":\"8989898\",\"customerCarePhone\":\"787878\",\"status\":\"Y\",\"email\":\"info@newsiri.com\",\"password\":\"SIRI\"}";
		Response r=given().contentType("application/json").body(json).when().post("http://localhost:8080/company/update");
		String body = r.getBody().asString();
    	System.out.println(body);
	}


}