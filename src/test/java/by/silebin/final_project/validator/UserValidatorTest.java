package by.silebin.final_project.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {
    @DataProvider(name = "validLogin")
    public static Object[][] validLogin() {
        return  new Object[][]{{"user.user12345"}, {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}, {"aaa"}};
    }

    @Test(dataProvider = "validLogin")
    public void testValidateLoginValidNames(String login) {
        Assert.assertTrue(UserValidator.validateLogin(login));
    }

    @DataProvider(name = "invalidLogin")
    public static Object[][] invalidLogin() {
        return  new Object[][]{{"<user.user12345>"}, {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}, {"aa"}};
    }

    @Test(dataProvider = "invalidLogin")
    public void testValidateLoginInvalidNames(String login) {
        Assert.assertFalse(UserValidator.validateLogin(login));
    }

    @DataProvider(name = "validEmail")
    public static Object[][] validEmail() {
        return  new Object[][]{{"user@gmail.com"}, {"default@tut.by"}, {"kek123@mail.ru"}};
    }

    @Test(dataProvider = "validEmail")
    public void testValidateEmailValidEmails(String email) {
        Assert.assertTrue(UserValidator.validateEmail(email));
    }

    @DataProvider(name = "invalidEmail")
    public static Object[][] invalidEmail() {
        return  new Object[][]{{"usergmail.com"}, {"123"}, {null}};
    }

    @Test(dataProvider = "invalidEmail")
    public void testValidateEmailInvalidEmails(String email) {
        Assert.assertFalse(UserValidator.validateEmail(email));
    }
}
