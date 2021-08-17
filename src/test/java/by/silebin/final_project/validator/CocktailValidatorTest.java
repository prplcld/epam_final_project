package by.silebin.final_project.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CocktailValidatorTest {
    @DataProvider(name = "validName")
    public static Object[][] validName() {
        return  new Object[][]{{"cocktail"}, {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}, {"aaa"}};
    }

    @Test(dataProvider = "validName")
    public void testValidateNameValidNames(String name) {
        Assert.assertTrue(CocktailValidator.validateName(name));
    }

    @DataProvider(name = "invalidName")
    public static Object[][] invalidName() {
        return  new Object[][]{{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}, {"aa"}};
    }

    @Test(dataProvider = "invalidName")
    public void testValidateNameInvalidNames(String name) {
        Assert.assertFalse(CocktailValidator.validateName(name));
    }

    @DataProvider(name = "validAmount")
    public static Object[][] validAmount() {
        return  new Object[][]{{"123"}, {"4444"}, {"1"}};
    }

    @Test(dataProvider = "validAmount")
    public void testValidateAmountValidAmounts(String amount) {
        Assert.assertTrue(CocktailValidator.validateAmount(amount));
    }

    @DataProvider(name = "invalidAmount")
    public static Object[][] invalidAmount() {
        return  new Object[][]{{"12.3"}, {"44444"}, {"-1"}};
    }

    @Test(dataProvider = "invalidAmount")
    public void testValidateAmountInvalidAmounts(String amount) {
        Assert.assertFalse(CocktailValidator.validateAmount(amount));
    }
}
