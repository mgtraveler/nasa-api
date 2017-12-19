package gov.nasa.api.core.testng;

import com.fasterxml.jackson.databind.JsonNode;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static gov.nasa.api.core.logger.Logger.LOGGER;

public class CustomTestNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Start of Test: {}::{}", result.getMethod().getTestClass().getName(),
                result.getMethod().getMethodName());
        logTestData(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    private void logTestData(ITestResult testResult) {
        if (testResult.getParameters().length != 0) {
            JsonNode jsonNode = (JsonNode) testResult.getParameters()[0];
            LOGGER.info("Test data:\n {}", jsonNode.toString());
        }
    }
}