package safron;

import io.restassured.RestAssured;
import io.restassured.internal.util.IOUtils;
import io.restassured.response.Response;
//import io.spring.guides.gs_producing_web_service.GetCountryResponse;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringReader;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SoapTest {

    @Test
    void getCountriesTest() throws Exception {

     //   JAXBContext jbCtx = JAXBContext.newInstance(GetCountryResponse.class);
    //    Unmarshaller unmarshaller = jbCtx.createUnmarshaller();

        InputStream is = SoapTest.class.getClassLoader().getResourceAsStream("getCountryRequest.xml");
        final String request = new String(IOUtils.toByteArray(is));

        RestAssured.baseURI = "http://localhost:8080/ws";

        Response response=given()
                .header("Content-Type", "text/xml")
                .and()
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(response.asString());
        assertTrue(response.asString().contains("EUR"));



       // GetCountryResponse getCountryResponse = (GetCountryResponse) unmarshaller.unmarshal(new StringReader(response.asString()));
      //  assertTrue(getCountryResponse.getCountry().getName().equals("Spain"));

    }
}
