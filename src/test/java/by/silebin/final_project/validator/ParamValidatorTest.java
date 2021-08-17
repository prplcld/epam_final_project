package by.silebin.final_project.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParamValidatorTest {
    @DataProvider(name = "validIntParam")
    public static Object[][] validIntParam() {
        return  new Object[][]{{"1"}, {"44444"}, {"23"}};
    }

    @Test(dataProvider = "validIntParam")
    public void testValidateIntParamValidParams(String param) {
        Assert.assertTrue(ParamValidator.validateIntParam(param));
    }

    @DataProvider(name = "invalidIntParam")
    public static Object[][] invalidIntParam() {
        return  new Object[][]{{"-1"}, {"abc"}, {"1.0"}};
    }

    @Test(dataProvider = "invalidIntParam")
    public void testValidateIntParamInvalidParams(String param) {
        Assert.assertFalse(ParamValidator.validateIntParam(param));
    }

    @DataProvider(name = "validBooleanParam")
    public static Object[][] validBooleanParam() {
        return  new Object[][]{{"true"}, {"false"}};
    }

    @Test(dataProvider = "validBooleanParam")
    public void testValidateBooleanParamValidParams(String param) {
        Assert.assertTrue(ParamValidator.validateBooleanParam(param));
    }

    @DataProvider(name = "invalidBooleanParam")
    public static Object[][] invalidBooleanParam() {
        return  new Object[][]{{"true1"}, {"false2"}, {"1"}, {"abc"}};
    }

    @Test(dataProvider = "invalidBooleanParam")
    public void testValidateBooleanParamInvalidParams(String param) {
        Assert.assertFalse(ParamValidator.validateBooleanParam(param));
    }
}

