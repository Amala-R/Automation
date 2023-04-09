package framework.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.exceptions.AutomationException;
import framework.globalConstants.HttpStatus;
import framework.propertiesManagement.TestProperties;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class RestUtil {
    private RequestSpecBuilder requestSpecBuilder;
    private RequestSpecification requestSpecification;
    private Response apiResponse;
    private HttpStatus expectedStatusCode = HttpStatus.OK;
    private String expectedResponseContentType;

    /**
     * Returns a new object of RestUtil class
     *
     * @return this
     */
    public static RestUtil init() throws AutomationException {
        return new RestUtil();
    }

    /**
     * RestUtil Default Constructor
     *
     */
    public RestUtil() throws AutomationException {
        initializeRequestSpec();
    }

    /**
     * Initializes Request Specifications including the Base URI Path from test.properties
     *
     */
    private void initializeRequestSpec() throws AutomationException {

        EncoderConfig encoderconfig = new EncoderConfig();
        requestSpecBuilder = new RequestSpecBuilder();

        /* ----- H E A D E R S ----- */
        requestSpecBuilder.setBaseUri(TestProperties.init().getProperty("app.url"));
        requestSpecBuilder.setConfig(RestAssured.config().encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));

    }

    /**
     * Defines API Endpoint Path to Request Specification
     *
     * @return this
     */
    public RestUtil path(String path) {
        requestSpecBuilder.setBasePath(path);
        return this;
    }

    /**
     * Defines Path Parameters to Request Specification
     *
     * @return this
     */
    public RestUtil pathParam(String key, String value) {
        requestSpecBuilder.addPathParam(key, value);
        return this;
    }

    public RestUtil contentType(ContentType contentType) {
        requestSpecBuilder.setContentType(contentType);
        return this;
    }

    /**
     * Defines Body to Request Specification
     *
     * @return this
     */
    public RestUtil body(Object body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    /**
     * Defines the Expected Status Code following successful api execution for validation
     *
     * @return this
     */
    public RestUtil expectedStatusCode(HttpStatus expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
        return this;
    }

    /**
     * Defines the Expected Response Content Type following successful api executio for validation
     *
     * @return this
     */
    public RestUtil expectedResponseContentType(ContentType contentType) {
        this.expectedResponseContentType = contentType.toString();
        return this;
    }

    /**
     * Hits the Pre-Defined Request Specification as PUT Request
     * <p>
     * On successful response, method validates:
     * -   Status Code against the Status Code provided in Request Specification
     * -   Content Type against the Content Type provided in Request Specification
     *
     * @return this
     */
    public RestUtil put() {
        requestSpecification = requestSpecBuilder.build();
        apiResponse =
                given()
                        .log().all()
                        .filter((requestSpec, responseSpec, ctx) -> ctx.next(requestSpec, responseSpec))
                        .spec(requestSpecification)
                        .when()
                        .put()
                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode.getCode())
                        .contentType(expectedResponseContentType)
                        .and()
                        .extract()
                        .response();

        return this;
    }

    /**
     * Hits the Pre-Defined Request Specification as DELETE Request
     * <p>
     * On successful response, method validates:
     * -   Status Code against the Status Code provided in Request Specification
     * -   Content Type against the Content Type provided in Request Specification
     *
     * @return this
     */
    public RestUtil delete() {
        requestSpecification = requestSpecBuilder.build();
        apiResponse =
                given()
                        .log().all()
                        .filter((requestSpec, responseSpec, ctx) -> ctx.next(requestSpec, responseSpec))
                        .spec(requestSpecification)
                        .when()
                        .delete()
                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode.getCode())
                        .contentType(expectedResponseContentType)
                        .and()
                        .extract()
                        .response();

        return this;
    }

    /**
     * Hits the Pre-Defined Request Specification as POST Request
     * <p>
     * On successful response, method validates:
     * -   Status Code against the Status Code provided in Request Specification
     * -   Content Type against the Content Type provided in Request Specification
     *
     * @return this
     */
    public RestUtil post() {
        requestSpecification = requestSpecBuilder.build();
        apiResponse =
                given()
                        .log().all()
                        .filter((requestSpec, responseSpec, ctx) -> ctx.next(requestSpec, responseSpec))
                        .spec(requestSpecification)
                        .when()
                        .post()
                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode.getCode())
                        .contentType(expectedResponseContentType)
                        .and()
                        .extract()
                        .response();

        return this;
    }

    /**
     * Hits the Pre-Defined Request Specification as GET Request
     * <p>
     * On successful response, method validates:
     * -   Status Code against the Status Code provided in Request Specification
     * -   Content Type against the Content Type provided in Request Specification
     *
     * @return this
     */
    public RestUtil get() {
        requestSpecification = requestSpecBuilder.build();
        apiResponse =
                given()
                        .log().all()
                        .filter((requestSpec, responseSpec, ctx) -> ctx.next(requestSpec, responseSpec))
                        .spec(requestSpecification)
                        .when()
                        .get()
                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode.getCode())
                        .contentType(expectedResponseContentType)
                        .and()
                        .extract()
                        .response();

        return this;
    }

    /**
     * Returns the apiResponse Object
     *
     * @return apiResponse
     */
    public Response response() {
        return apiResponse;
    }

    /**
     * Returns the apiResponse Object as String
     *
     * @return apiResponse
     */
    public String getApiResponseAsString() {
        return apiResponse.asString();
    }

    /**
     * Converts the Response Object into the provided Class Type
     */
    public <T> T responseToPojo(Class<T> type) throws AutomationException {
        try {
            return new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY).readValue(getApiResponseAsString(), type);
        } catch (Exception ex) {
            String message = "Response received did not match the expected response : " + getApiResponseAsString();
            Assertions.isFalse(message);
            throw new AutomationException(message);
        }
    }

    /**
     * Converts the Response Object into the provided Class Type
     *
     */
    public <T> T responseToPojo(TypeReference<T> type) throws AutomationException {
        try {
            return new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY).readValue(getApiResponseAsString(), type);
        } catch (IOException ex) {
            throw new AutomationException(ex);
        }
    }

}