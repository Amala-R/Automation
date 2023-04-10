import framework.common.Assertions;
import framework.exceptions.AutomationException;
import framework.propertiesManagement.TestProperties;
import framework.services.UserProfileService;
import org.testng.annotations.Test;

public class DeleteUserTestCase {

    @Test
    public static void deleteUserProfile() throws AutomationException {
        String getUserToDelete = TestProperties.init().getProperty("user.name");
        UserProfileService
                .init()
                .deleteUserProfiles(getUserToDelete);

        Assertions.shouldBeTrue("\nDeleted user is : "+getUserToDelete);
        Assertions.SOFT_ASSERT.assertAll();
    }
}
