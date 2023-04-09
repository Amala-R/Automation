import framework.common.Assertions;
import framework.exceptions.AutomationException;
import framework.model.User;
import framework.services.UserProfileService;
import org.testng.annotations.Test;

public class CreateUserTestCase {

    @Test
    public static void addUserProfile() throws AutomationException {
        User userToCreate = new User(1);
        UserProfileService
                .init()
                .addUserProfiles(userToCreate);
        Assertions.SOFT_ASSERT.assertAll();
    }
}
