import framework.common.Assertions;
import framework.common.RestUtil;
import framework.exceptions.AutomationException;
import framework.model.User;
import framework.propertiesManagement.TestProperties;
import framework.services.UserProfileService;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static framework.globalConstants.JsonConstants.MODIFY_FIELD;
import static framework.globalConstants.JsonConstants.RESPONSE_STRING_NAME;

public class ModifyUserTestCase {

    @Test
    public static void updateUserProfile() throws AutomationException {
       RestUtil modifyUser =
               UserProfileService
                       .init()
                       .getUserProfileForUpdating(TestProperties.init().getProperty("user.name"));
        JSONObject jsonObject = new JSONObject(modifyUser);
        JSONObject newJsonToUpdate = new JSONObject(jsonObject.get(RESPONSE_STRING_NAME).toString());
        newJsonToUpdate.put(MODIFY_FIELD, User.faker.name().fullName());
        UserProfileService
                .init()
                .modifyUserProfiles(newJsonToUpdate,TestProperties.init().getProperty("user.name"));

        Assertions.shouldBeTrue("\nUpdated JSON is : "+newJsonToUpdate);
        Assertions.shouldBeTrue("\nJSON before updating is : "+jsonObject.get(RESPONSE_STRING_NAME).toString());
        Assertions.SOFT_ASSERT.assertAll();
    }
}
