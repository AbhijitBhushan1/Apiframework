package Utils;

import org.testng.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.lang.annotation.Annotation;
import java.util.logging.Logger;

public class Common extends TestListenerAdapter {

    @BeforeSuite(alwaysRun = true)
    public void onTestFailure(ITestResult its)
    {
        log(its.getTestName()+"Test failed at");

    }
    @BeforeSuite(alwaysRun = true)
    public void onTestSuccess(ITestResult its)
    {
        log(its.getTestName()+"Test passed at");

    }
    @BeforeSuite(alwaysRun = true)
    public void onTestSkipped(ITestResult its)
    {

        log(its.getName()+"Test skipped");
    }

    public void log(String str)
    {
        System.out.println(str);
    }

    }
