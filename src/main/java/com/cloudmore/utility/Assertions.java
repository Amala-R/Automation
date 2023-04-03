package com.cloudmore.utility;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Assertions{
    private Assertions(){}

    public static final SoftAssert SOFT_ASSERT = new SoftAssert();
    public static void shouldBeTrue(boolean condition, String message, String expected, String actual) {
        if (condition) {
            Assert.assertTrue(true, "\n" + message + "\n");
            ReportUtility.getTest().log(Status.PASS, message);
        } else {
            SOFT_ASSERT.assertEquals("Actual Result : "+actual, "Expected Result : " + expected,"Validation failed");
            ReportUtility.getTest().log(Status.FAIL, "The actual result is " + actual + "and the expected result is " + expected);
        }
    }

    public static void shouldBeTrue(String message) {
        Assert.assertTrue(true, "\n" + message + "\n");
        ReportUtility.getTest().log(Status.PASS, message);
    }
}