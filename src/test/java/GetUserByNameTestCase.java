import framework.common.Assertions;
import framework.exceptions.AutomationException;
import framework.globalConstants.HttpStatus;
import framework.model.User;
import framework.propertiesManagement.TestProperties;
import framework.services.UserProfileService;
import org.testng.annotations.Test;

public class GetUserByNameTestCase {

    @Test
    public static void getUserByPassingValidName() throws AutomationException {
        UserProfileService
                .init()
                .getUserProfileByName(TestProperties.init().getProperty("user.name"));
        Assertions.SOFT_ASSERT.assertAll();
    }

    @Test
    public static void getUserByPassingInValidName() throws AutomationException {
        UserProfileService
                .init()
                .isNegativeTest(HttpStatus.NOT_FOUND)
                .getUserProfileByName(User.faker.name().fullName());
        Assertions.SOFT_ASSERT.assertAll();

    }
}