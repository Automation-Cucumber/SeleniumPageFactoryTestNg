package Listener;

import SpreeTest.BaseTests;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

import java.io.IOException;

public class CustomListner extends BaseTests implements ITestListener {


    @Override
    public void onFinish(ITestContext result) {
        // TODO Auto-generated method stub
        System.out.println("***** FINISH TEST *****:"+result.getName());
    }

    @Override
    public void onStart(ITestContext result) {
        // TODO Auto-generated method stub
        System.out.println("*****STARTED TEST*****:"+result.getName());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        /* TODO Auto-generated method stub */
        System.out.println("***** FAILED SUCCESS PERCENTAGE *****:"+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        System.out.println("***** FAILED TEST *****:"+result.getName());
        try {
           takeSnapShot(result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        System.out.println("***** SKIPPED TEST *****:"+result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        System.out.println("***** SUCCESS TEST*****:"+result.getName());

    }
}








