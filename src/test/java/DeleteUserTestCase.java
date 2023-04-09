import framework.common.Assertions;
import framework.common.RestUtil;
import framework.exceptions.AutomationException;
import framework.propertiesManagement.TestProperties;
import framework.services.UserProfileService;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static framework.globalConstants.JsonConstants.RESPONSE_STRING_NAME;

public class DeleteUserTestCase {

    @Test
    public static void deleteUserProfile() throws AutomationException {
        RestUtil modifyUser =
                UserProfileService
                        .init()
                        .getUserProfileForUpdating(TestProperties.init().getProperty("user.name"));
        JSONObject jsonObject = new JSONObject(modifyUser);
        JSONObject newJsonToUpdate = new JSONObject(jsonObject.get(RESPONSE_STRING_NAME).toString());
        UserProfileService
                .init()
                .deleteUserProfiles(TestProperties.init().getProperty("user.name"));

        Assertions.shouldBeTrue("\nDeleted JSON is : "+newJsonToUpdate);
        Assertions.SOFT_ASSERT.assertAll();
    }
}
