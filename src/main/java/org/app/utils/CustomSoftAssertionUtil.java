package org.app.utils;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
public class CustomSoftAssertionUtil  extends SoftAssert{




    public static CustomSoftAssertionUtil softAssertion = new CustomSoftAssertionUtil();


    public static void customAssertAll(ITestResult result) {
        try {
            softAssertion.assertAll("Custom Soft Assertion");
        } catch (AssertionError e) {
            LogsUtil.error("Custom Soft Assertion Failed: " + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        } finally {
            reInitializeSoftAssert();
        }
    }

    private static void reInitializeSoftAssert() {
        softAssertion = new CustomSoftAssertionUtil();
    }

}