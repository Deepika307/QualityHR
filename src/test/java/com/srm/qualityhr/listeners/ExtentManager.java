package com.srm.qualityhr.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.srm.qualityhr.constants.FrameworkConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ExtentManager {

    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            try {
                Files.createDirectories(FrameworkConstants.REPORT_DIR);
            } catch (IOException exception) {
                throw new IllegalStateException("Unable to create reports directory", exception);
            }
            Path reportPath = FrameworkConstants.REPORT_DIR.resolve(FrameworkConstants.REPORT_NAME);
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString());
            sparkReporter.config().setReportName("QualityHR Automation Report");
            sparkReporter.config().setDocumentTitle("OrangeHRM Test Execution");
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Framework", "Selenium TestNG POM");
        }
        return extentReports;
    }

    public static void setTest(ExtentTest extentTest) {
        TEST.set(extentTest);
    }

    public static ExtentTest getTest() {
        return TEST.get();
    }

    public static void unload() {
        TEST.remove();
    }
}
