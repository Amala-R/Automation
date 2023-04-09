package framework.services;

import framework.common.Assertions;
import framework.common.RestUtil;
import framework.exceptions.AutomationException;
import framework.globalConstants.APIEndPoint;
import framework.globalConstants.HttpStatus;
import framework.model.User;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import static framework.globalConstants.APIEndPoint.SERVICE_URI;
import static framework.globalConstants.JsonConstants.USER_NAME;

public class UserProfileService extends User {
    private boolean isNegativeTest = false;
    private HttpStatus httpStatus = HttpStatus.OK;
    private ContentType responseContentType = ContentType.JSON;

    public static UserProfileService init() {
        return new UserProfileService();
    }

    public UserProfileService isNegativeTest(HttpStatus httpStatus) {
        this.responseContentType = ContentType.JSON;
        this.isNegativeTest = true;
        this.httpStatus = httpStatus;
        return this;
    }


    public RestUtil getUserProfileForUpdating(String userName) throws AutomationException {
       return
                RestUtil.init()
                        .path(APIEndPoint.USER_PROFILES + SERVICE_URI)
                        .pathParam(USER_NAME, userName)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .get();
    }

    public void addUserProfiles(User user) throws AutomationException {
        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.USER_PROFILES)
                        .contentType(ContentType.JSON)
                        .body(user)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .post();

        if (!isNegativeTest) {
            int statusCode = restInstance.response().statusCode();
            Assertions.shouldBeTrue(
                    String.format("Created new user %s",user)+
                            "Response payload is : "+restInstance.getApiResponseAsString()+"\nStatus Code is : "+statusCode);
        } else {
            Assertions.shouldBeTrue("This is a negative test expected to fail");
        }

    }

    public void modifyUserProfiles(JSONObject user, String userName) throws AutomationException {
        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.USER_PROFILES + SERVICE_URI)
                        .pathParam(USER_NAME, userName)
                        .contentType(ContentType.JSON)
                        .body(user)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .put();
        int statusCode = restInstance.response().statusCode();

        if (!isNegativeTest) {
            Assertions.shouldBeTrue("Response payload is : "+restInstance.getApiResponseAsString()+"\nStatus Code is : "+statusCode);
        } else {
            Assertions.shouldBeTrue("This is a negative test expected to fail");
            Assertions.isFalse("Response payload is : "+restInstance.getApiResponseAsString()+"\nStatus Code is : "+statusCode);
        }

    }

    public void getUserProfileByName(String userName) throws AutomationException {
        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.USER_PROFILES + SERVICE_URI)
                        .pathParam(USER_NAME, userName)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .get();
        int statusCode = restInstance.response().statusCode();

        if (!isNegativeTest) {
            Assertions.shouldBeTrue("Response payload is : "+restInstance.getApiResponseAsString()+"\nStatus Code is : "+statusCode);
        } else {
            Assertions.shouldBeTrue("This is a negative test expected to fail");
            Assertions.isFalse("Response payload is : "+restInstance.getApiResponseAsString()+"\nStatus Code is : "+statusCode);
        }
    }

    public void deleteUserProfiles(String userName) throws AutomationException {
        RestUtil restInstance =
                RestUtil.init()
                        .path(APIEndPoint.USER_PROFILES + SERVICE_URI)
                        .pathParam(USER_NAME, userName)
                        .expectedStatusCode(httpStatus)
                        .expectedResponseContentType(responseContentType)
                        .delete();
        int statusCode = restInstance.response().statusCode();

        if (!isNegativeTest) {
            Assertions.shouldBeTrue("Response payload is : "+restInstance.getApiResponseAsString()+"\nStatus Code is : "+statusCode);
        } else {
            Assertions.shouldBeTrue("This is a negative test expected to fail");
            Assertions.isFalse("Response payload is : "+restInstance.getApiResponseAsString()+"\nStatus Code is : "+statusCode);
        }
    }
}