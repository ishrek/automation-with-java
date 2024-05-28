package webdriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import webdriver.common.BaseDriver;

public class Topic_17_Listener extends BaseDriver implements ITestListener {
    // Dung listener lien quan den report ( khi success/fail/ignore thi se lam gi

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure: Ham nay dung de capture");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
